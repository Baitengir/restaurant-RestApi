package myRestaurant.myExceptions;

public class TestException extends RuntimeException {
    public TestException(String message) {
        super("TestException  :  "+ message);
    }
}
