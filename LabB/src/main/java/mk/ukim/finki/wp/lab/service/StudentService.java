package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAll();
    List<Student> searchByNameOrSurname(String text);
    Student save(String username, String password, String name, String surname);
    List<Student> filterStudents(Long courseId);
    List<Course> findCoursesList(String username);
    Student addCourse(String username,long courseId);
    Student findBtUsername(String username);
    boolean deleteByUsername(String username);
}
