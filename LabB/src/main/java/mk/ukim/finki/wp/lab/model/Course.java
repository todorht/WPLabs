package mk.ukim.finki.wp.lab.model;

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

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    public Course(){}

    public Course(String name, String description, Teacher teacher, Type type) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.type = type;
        this.students = new ArrayList<>();
    }

    public Course(String name, String description,Type type) {
        this.name = name;
        this.description = description;
        this.teacher = null;
        this.type = type;
        this.students = new ArrayList<>();
    }

}
