package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exception.*;
import mk.ukim.finki.wp.lab.repository.jpa.CoursesRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryJpa studentRepository;
    private final CoursesRepositoryJpa courseRepository;

    public StudentServiceImpl(StudentRepositoryJpa studentRepository, CoursesRepositoryJpa courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> filterStudents(Long courseId) {
        if(courseRepository.findById(courseId).isEmpty())
            throw new CourseNotFoundException(courseId);
        List<Student> courseStudents = courseRepository.findById(courseId).get().getStudents();
        List<Student> allStudents = new ArrayList<>(studentRepository.findAll());
        for (Student student1 : courseStudents) {
            allStudents.removeIf(r->r.getUsername().equals(student1.getUsername()));
        }
        return allStudents;
    }

    @Override
    public List<Course> findCoursesList(String username) {
        if(this.studentRepository.findById(username).isEmpty())
            throw new StudentNotFoundException(username);

        return this.courseRepository.findAll().stream()
                .filter(course -> course.getStudents()
                        .contains(this.studentRepository.getOne(username)))
                .collect(Collectors.toList());
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(name==null || name.isEmpty() || surname == null || surname.isEmpty())
            throw new InvalidNameAndSurnameException();
        if(this.studentRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        Student student = new Student(username, password, name, surname);
        studentRepository.findAll().add(student);
        return studentRepository.save(student);
    }

    @Override
    public Student findBtUsername(String username) {
        return studentRepository.findById(username).orElseThrow(()->new StudentNotFoundException(username));
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        try {
            this.studentRepository.deleteById(username);
        }catch (StudentCantBeDeletedException e){
            throw new StudentCantBeDeletedException(username);
        }

    }

}
