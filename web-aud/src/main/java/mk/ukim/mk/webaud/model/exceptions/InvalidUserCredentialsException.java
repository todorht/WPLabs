package mk.ukim.mk.webaud.model.exceptions;



public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException(){
        super("Invalid User");
    }
}
