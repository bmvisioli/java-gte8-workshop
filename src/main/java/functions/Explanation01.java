package functions;

public class Explanation01 {

  public static void main(String args[]) {

    //Passing behaviour as parameter before Java 8 -> Inner Class
    new Thread(
        new Runnable() {
          @Override
          public void run() {
            System.out.println("I just felt like running (as an inner class)!");
          }
        }
    ).start();

      //Passing behaviour as parameter after Java 8 -> Lambda
    new Thread(
        () -> System.out.println("I just felt like running (as a lambda)!")
    ).start();
  }
}