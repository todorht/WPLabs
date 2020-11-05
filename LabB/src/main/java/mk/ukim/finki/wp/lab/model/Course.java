package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;

    public Course(String name, String description, List<Student> students) {
        Random random = new Random();
        this.courseId = random.nextLong();
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
    }
}
