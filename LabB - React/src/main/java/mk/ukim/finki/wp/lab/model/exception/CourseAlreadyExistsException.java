package mk.ukim.finki.wp.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(String name){
        super(String.format("Course with this name: %s, already exists!", name));
    }
}
