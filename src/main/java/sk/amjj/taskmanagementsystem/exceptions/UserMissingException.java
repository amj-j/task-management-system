package sk.amjj.taskmanagementsystem.exceptions;

public class UserMissingException extends RuntimeException {

    public UserMissingException() {
        super("No one is logged in!");
    }

}
