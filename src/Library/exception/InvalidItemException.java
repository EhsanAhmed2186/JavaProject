package Library.exception;
public class InvalidItemException extends Exception {
    public InvalidItemException(String id) {
        super("Item with id:" + id + " is not a valid item.");
    }
}
