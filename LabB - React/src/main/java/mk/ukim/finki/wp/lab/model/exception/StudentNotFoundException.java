package mk.ukim.finki.wp.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String username){
        super(String.format("Student with %s was not found", username));
    }
}
