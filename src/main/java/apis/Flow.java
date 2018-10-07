package apis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;

public class Flow {

  /*
      ______    __
     / ____/   / /  ____  _      __
    / /_      / /  / __ \| | /| / /
   / __/     / /  / /_/ /| |/ |/ /
  /_/       /_/   \____/ |__/|__/

    Flow API is a specification for Reactive Streams and is available since Java 9

    It's a reaction to well-established Reactive Streams implementations for Java:
    * RxJava
    * Vert.x
    * Project Reactor
    * ...

    Terminology:
    - Publisher - the source of messages;
    - Subscriber - the destination of messages;
    - Subscription - the link between publisher and subscriber;
    - Processor - an intermediary subscriber and publisher (for map, filter...);
   */

  public static void main(String[] args) {

    // This is a default implementation of a Flow.Publisher
    SubmissionPublisher<String> publisher = new SubmissionPublisher<>(ForkJoinPool.commonPool(), 1000);

    Accumulator<String> subscriber1 = new Accumulator<>("1", 1000);
    Accumulator<String> subscriber2 = new Accumulator<>("2", 3000);
    publisher.subscribe(subscriber1);
    publisher.subscribe(subscriber2);

    var listOfItems = List.of("Item 1", "Item 2", "Item 3");
    // Submit called below will
    listOfItems.forEach(publisher::submit);


    // This will make the onComplete method in all subscribers to be called
    publisher.close();

    // We're blocking the thread here to give time to the subscribers to complete
    while(!subscriber1.isComplete() || !subscriber2.isComplete()) { }

    assert(subscriber1.getReceivedItems() == listOfItems);
    assert(subscriber2.getReceivedItems() == listOfItems);
  }

}
class Accumulator<T> implements Subscriber<T> {

  private final String name;
  private final int sleepTime;
  private volatile boolean complete = false;

  private List<T> receivedItems = new ArrayList<>();
  private Subscription subscription;

  public Accumulator(String name, int sleepTime) {
    this.name = name;
    this.sleepTime = sleepTime;
  }

  @Override
  public void onSubscribe(Subscription subscription) {
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public void onNext(T item) {
    System.out.println("Accumulator "+ name + ": Item Received = " + item);
    slowlyAddToList(item);
    subscription.request(1);
  }

  private void slowlyAddToList(T item) {
    try {
      Thread.sleep(sleepTime);
      receivedItems.add(item);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onError(Throwable throwable) { System.err.println(throwable.getMessage()); }

  @Override
  public void onComplete() {
    System.out.println("Accumulator "+ name + ": All Items Received = " + receivedItems);
    complete = true;
  }

  public List<T> getReceivedItems() {
    return receivedItems;
  }

  public boolean isComplete() {
    return complete;
  }
}