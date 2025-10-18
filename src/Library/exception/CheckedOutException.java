package Library.exception;
public class CheckedOutException extends Exception {
    public CheckedOutException(String id, String title) {
        super("Item '" + title + "' with id:" + id + " is already checked out.");
    }
    public CheckedOutException(String message) {
        super(message);
    }
}
