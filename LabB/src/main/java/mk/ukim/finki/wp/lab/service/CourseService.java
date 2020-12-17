package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Type;

import java.util.List;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    void addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Course findById(Long courseId);
    void deleteById(Long id);
    Course save(String name, String description, long teacher, Type type);
    Course save(String name, String description, Type type);
    Course setTeacherC(long courseId, long teacherId);
    Course editCourse(long courseId, String name, String description, long teacherId, Type type);
}

