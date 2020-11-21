package mk.ukim.finki.wp.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Teacher {
    private Long id;
    private String name;
    private String surname;
    private List<Course> courses;
    @JsonIgnore
    final Random random = new Random();

    public Teacher(String name, String surname) {

        this.id = random.nextLong();
        this.name = name;
        this.surname = surname;
        this.courses = new ArrayList<>();
    }
}
