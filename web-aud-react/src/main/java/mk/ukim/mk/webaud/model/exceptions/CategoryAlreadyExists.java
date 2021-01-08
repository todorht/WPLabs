package mk.ukim.mk.webaud.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.IM_USED)
public class CategoryAlreadyExists extends RuntimeException{
    private String name;
    public CategoryAlreadyExists(String name){
        this.name = name;
    }

    @Override
    public String getMessage() {
        return String.format("Category with name: %s already exists",this.name);
    }
}
