package mk.ukim.finki.wp.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TeacherNotFoundedException extends RuntimeException{
    public TeacherNotFoundedException(Long id){
        super(String.format("Teacher with id %d was not found.", id));
    }
}
