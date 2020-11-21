package mk.ukim.finki.wp.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private Teacher teacher;
    private List<Student> students;
    @JsonIgnore
    final Random random = new Random();

    public Course(String name, String description, Teacher teacher) {
        this.courseId = random.nextLong();
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    public Course(String name, String description) {
        this.courseId = random.nextLong();
        this.name = name;
        this.description = description;
        this.teacher = null;
        this.students = new ArrayList<>();
    }
}
