package Library.exception;
public class InvalidMemberException extends Exception {
    public InvalidMemberException(String id) {
        super("Member with id:" + id + " is not a valid member.");
    }
    public InvalidMemberException() {
        super("Invalid member.");
    }
}