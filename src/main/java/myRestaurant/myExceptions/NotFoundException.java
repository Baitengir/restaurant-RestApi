package myRestaurant.myExceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("not found"+ message);
    }
}
