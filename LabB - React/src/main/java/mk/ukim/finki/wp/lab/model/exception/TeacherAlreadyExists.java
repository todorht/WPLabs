package mk.ukim.finki.wp.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class TeacherAlreadyExists extends RuntimeException{
    public TeacherAlreadyExists(String name){
        super(String.format("Teacher with name %s already exists", name));
    }
}
