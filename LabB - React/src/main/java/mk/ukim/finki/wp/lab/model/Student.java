package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Student {

    @Id
    private String username;
    private String password;
    private String name;
    private String surname;

    @Transient
    private Character grade;

    public Student(){}

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.grade = null;
    }

    public Student(Character grade){
        this.grade = grade;
    }
}
