package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    void addStudentInCourse(String username, String courseId);
    List<Course> listAll();
    Course findById(Long courseId);
    void deleteById(Long id);
    Course save(String name, String description, long teacher);
    Course save(String name, String description);
    Course setTeacherC(long courseId, long teacherId);
    Course editCourse(long courseId, String name, String description, long teacherId);
}

