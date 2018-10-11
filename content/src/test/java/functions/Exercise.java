package functions;

import static boilerplate.Boilerplate.__;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Exercise {

  // Finish the implementation of the terribly inefficient ImmutableArrayList;

  @Test
  @DisplayName("Map from collection of Integer to collection of Double")
  public void mapTest() {

    var initial = ImmutableArrayList.of(1,2,3);
    var expected = ImmutableArrayList.of(1d,2d,3d);

    var actual = __; //TODO add lambda

    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("ForEach accumulating to AtomicInteger")
  public void forEachTest() {
    var initial = ImmutableArrayList.of(1,2,3);
    var expected = 6;

    var actual = new AtomicInteger(0);
    initial.forEach(); //TODO add lambda to accumulate into the object above

    assertEquals(expected, actual.intValue());
  }

  @Test
  @DisplayName("Filter only even")
  public void filterTest() {
    var initial = ImmutableArrayList.of(1,2,3,4,5,6);
    var expected = ImmutableArrayList.of(2,4,6);

    var actual = __; //TODO add lambda

    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("FoldLeft with times function")
  public void foldLeft() {
    var initial = ImmutableArrayList.of(1,2,3,4,5,6);
    var expected = Double.valueOf(720);

    var actual = __; //TODO add lambda and initial arg

    assertEquals(expected, actual);
  }

}

/** A terribly inefficient Immutable List backed by an Array */
class ImmutableArrayList<E> {

  private E[] elementData;

  public <T> ImmutableArrayList<T> map() { //TODO Add argument SomeFunctionalInterface funcInt
    var result = new Object[size()];
    for(int index = 0; index < size(); index ++) {
      E oldElement = elementData[index];

      T newElement = null; //TODO call funcInt.???(oldElement)

      result[index] = newElement;
    }
    return new ImmutableArrayList<>((T[]) result);
  }

  public void forEach () {  //TODO Add argument SomeFunctionalInterface funcInt
    for(E element : elementData) {
      //TODO call funcInt.???(element)
    }
  }

  public ImmutableArrayList<E> filter() { //TODO Add argument SomeFunctionalInterface funcInt
    var result = new Object[size()];
    var currentResultIndex = 0;
    for(int index = 0; index < size(); index ++) {
      if(true) { //TODO call funcInt.???(element)
        result[currentResultIndex] = elementData[index];
        currentResultIndex++;
      }
    }
    result = Arrays.copyOf(result, currentResultIndex);
    return new ImmutableArrayList<>((E[]) result);
  }

  public <D> D foldLeft(D initial) { //TODO Add argument SomeFunctionalInterface funcInt
    D result = initial;
    for(E element : elementData) {
      result = null;  //TODO call funcInt.???(???)
    }
    return result;
  }

  // Don't worry about the methods below, they're fine, trust me.
  private ImmutableArrayList(E[] elements) { this.elementData = elements; }
  public static <E> ImmutableArrayList<E> of(E... elements) { return new ImmutableArrayList<>(elements); }
  public int size() { return elementData.length; }
  public String toString() { return Arrays.toString(elementData); }
  public boolean equals(Object obj) { return obj instanceof ImmutableArrayList && Arrays.equals(this.elementData, ((ImmutableArrayList)obj).elementData); }
  // Don't worry about the methods above, they're fine, trust me.
}
