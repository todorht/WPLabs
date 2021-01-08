package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> listAll();
    Student save(String username, String password, String name, String surname);
    List<Student> filterStudents(Long courseId);
    List<Course> findCoursesList(String username);
    Student findBtUsername(String username);
    void deleteByUsername(String username);
}
