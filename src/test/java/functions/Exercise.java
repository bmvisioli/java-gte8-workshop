package functions;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Exercise {

  @Test
  public void map_withFromIntToDouble_shouldReturnAListOfDoubles() {

    ImmutableArrayList initial = ImmutableArrayList.of(1,2,3);
    ImmutableArrayList expected = ImmutableArrayList.of(1d,2d,3d);

    ImmutableArrayList actual = initial.map(); //TODO add lambda

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void forEach_summingIntoACounter_shouldReturnSumOfElements() {
    ImmutableArrayList initial = ImmutableArrayList.of(1,2,3);
    int expected = 6;

    AtomicInteger actual = new AtomicInteger(0);
    initial.forEach(); //TODO add lambda to accumulate into the object above

    Assertions.assertEquals(expected, actual.intValue());
  }

}

/** A terribly inefficient Immutable List backed by an Array */
class ImmutableArrayList<E> {

  private E[] elementData;

  //Don't worry about the methods below, they're fine, trust me.
  private ImmutableArrayList(E[] elements) { this.elementData = elements; }
  public static <E> ImmutableArrayList<E> of(E... elements) { return new ImmutableArrayList<>(elements); }
  public E get(int index) { return elementData[index]; }
  public int size() { return elementData.length; }
  public String toString() { return Arrays.toString(elementData); }
  public ImmutableArrayList<E> append(E newElement) { E[] newArray = Arrays.copyOf(elementData, size()+1); newArray[size()] = newElement; return new ImmutableArrayList(newArray); }
  public boolean equals(Object obj) { return obj instanceof ImmutableArrayList && Arrays.equals(this.elementData, ((ImmutableArrayList)obj).elementData); }
  //Don't worry about the methods above, they're fine, trust me.

  public <T> ImmutableArrayList<T> map(
      //TODO Add argument SomeFunctionalInterface funcInt
  ) {
    Object[] result = new Object[size()];
    for(int index = 0; index < size(); index ++) {
      E oldElement = elementData[index];
      T newElement = null; //TODO call funcInt.???(oldElement)
      result[index] = newElement;
    }
    return new ImmutableArrayList<>((T[]) result);
  }

  public void forEach (
      //TODO Add argument SomeFunctionalInterface funcInt
  ) {
    for(E element : elementData) {
      //TODO call funcInt.???(element)
    }
  }
}
