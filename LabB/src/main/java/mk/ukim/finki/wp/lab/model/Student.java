package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    private String username;
    private String password;
    private String name;
    private String surname;

    private Character grade;

    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    private List<Course> courseList;

    public Student(){}

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.grade = null;
        this.courseList = new ArrayList<>();
    }

    public Student(Character grade){
        this.grade = grade;
    }
}
