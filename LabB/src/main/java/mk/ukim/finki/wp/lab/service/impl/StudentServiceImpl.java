package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> filterStudents(Long courseId) {
        List<Student> courseStudents = courseRepository.findAllStudentsByCourse(courseId);
        List<Student> allStudents = new ArrayList<>(studentRepository.findAllStudents());
        for (Student student1 : courseStudents) {
            allStudents.removeIf(r->r.getUsername().equals(student1.getUsername()));
        }
        return allStudents;
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {

        return studentRepository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        return studentRepository.addStudent(username, password, name, surname);
    }

    @Override
    public List<Course> findCoursesList(String username) {
        return studentRepository.findCourses(username);
    }

    @Override
    public Student addCourse(String username, long id) {
        return studentRepository.addCourse(studentRepository.findByUsername(username),courseRepository.findById(id));
    }

    @Override
    public Student findBtUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public boolean deleteByUsername(String username) {
        return this.studentRepository.deleteByUsername(username);
    }
}
