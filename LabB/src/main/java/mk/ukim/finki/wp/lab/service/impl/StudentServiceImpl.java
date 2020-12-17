package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exception.StudentNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.CoursesRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryJpa studentRepository;
    private final CoursesRepositoryJpa courseRepository;
    private final GradeRepositoryJpa gradeRepository;

    public StudentServiceImpl(StudentRepositoryJpa studentRepository, CoursesRepositoryJpa courseRepository, GradeRepositoryJpa gradeRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> filterStudents(Long courseId) {
        List<Student> courseStudents = courseRepository.findById(courseId).get().getStudents();
        List<Student> allStudents = new ArrayList<>(studentRepository.findAll());
        for (Student student1 : courseStudents) {
            allStudents.removeIf(r->r.getUsername().equals(student1.getUsername()));
        }
        return allStudents;
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findAllByNameOrSurname(text, text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        Student student = new Student(username, password, name, surname);
        studentRepository.findAll().add(student);
        return studentRepository.save(student);
    }

    @Override
    public Student findBtUsername(String username) {
        return studentRepository.findById(username).orElseThrow(()->new StudentNotFoundException(username));
    }

    @Override
    public boolean deleteByUsername(String username) {
        return this.studentRepository.deleteByUsername(username);
    }

}
