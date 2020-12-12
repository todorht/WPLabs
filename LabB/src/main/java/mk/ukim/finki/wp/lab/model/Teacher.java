package mk.ukim.finki.wp.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.EAGER)
    private List<Course> courses;

    public Teacher(){}

    public Teacher(String name, String surname) {
                this.name = name;
        this.surname = surname;
        this.courses = new ArrayList<>();
    }
}
