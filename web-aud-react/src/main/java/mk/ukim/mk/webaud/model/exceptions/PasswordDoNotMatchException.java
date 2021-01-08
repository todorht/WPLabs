package mk.ukim.mk.webaud.model.exceptions;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException(){
        super("Passwords do not match");
    }
}
