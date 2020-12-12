package mk.ukim.finki.wp.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    public Course(){}

    public Course(String name, String description, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
        this.teacher = null;
        this.students = new ArrayList<>();
    }

}
