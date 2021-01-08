package mk.ukim.finki.wp.lab.model.exception;

public class StudentCantBeDeletedException extends RuntimeException {
    public StudentCantBeDeletedException(String username){
        super(String.format("Student %s cant be deleted", username));
    }
}
