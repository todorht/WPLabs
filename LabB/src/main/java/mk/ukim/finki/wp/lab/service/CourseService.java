package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;

import java.util.List;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Course findById(Long courseId);
    boolean deleteById(Long id);
    Course save(String name, String description, long teacher);
    Course save(String name, String description);
    Course setTeacher(long teacherId, long courseId);
}

