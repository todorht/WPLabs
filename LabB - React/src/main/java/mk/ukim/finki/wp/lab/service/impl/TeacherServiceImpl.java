package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.exception.TeacherAlreadyExists;
import mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundedException;
import mk.ukim.finki.wp.lab.repository.jpa.CoursesRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepositoryJpa;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryJpa teacherRepository;
    private final CoursesRepositoryJpa courseRepository;
    private final GradeRepositoryJpa gradeRepository;
    private final StudentRepositoryJpa studentRepository;

    public TeacherServiceImpl(TeacherRepositoryJpa teacherRepository,
                              CoursesRepositoryJpa courseRepository,
                              GradeRepositoryJpa gradeRepository,
                              StudentRepositoryJpa studentRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return Optional.of(this.teacherRepository.findById(id).orElseThrow(()-> new TeacherNotFoundedException(id)));
    }

    @Override
    public Teacher save(String name, String surname) {
            if(this.teacherRepository.findAll().stream().noneMatch(t->t.getName().equals(name))){
                return this.teacherRepository.save(new Teacher(name,surname));
            }else throw new TeacherAlreadyExists(name);
    }

    @Override
    @Transactional
    public void deleteById(long teacherId) {
        if(this.teacherRepository.findById(teacherId).isPresent()) {
            this.teacherRepository.deleteById(teacherId);
        }else throw new TeacherNotFoundedException(teacherId);
    }

    @Override
    public List<Course> findTeacherCourses(Long teacherId) {
        return this.courseRepository.findAll().stream()
                .filter(course -> {
                    if(course.getTeacher()!=null) {
                        course.getTeacher().getId().equals(teacherId);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Grade setGrade(Long teacherId, Long courseId, String username, Character grade) {
        if(this.courseRepository.findById(courseId).isPresent()) {
            if (this.courseRepository.getOne(courseId).getTeacher().getId().equals(teacherId)) {
                if (gradeRepository.findAll().stream()
                        .noneMatch(grade1 -> grade1.getCourse().getCourseId().equals(courseId)
                                && grade1.getStudent().getUsername().equals(username))) {
                    return this.gradeRepository.save(new Grade(grade
                            , this.studentRepository.getOne(username)
                            , this.courseRepository.getOne(courseId)));
                } else {
                    Grade grade1 = this.gradeRepository.findAll().stream()
                            .filter(grade2 -> grade2.getCourse().getCourseId().equals(courseId)
                            && grade2.getStudent().getUsername().equals(username)).findFirst().get();
                    grade1.setGrade(grade);
                    return this.gradeRepository.save(grade1);
                }
            } return null;
        } else throw new CourseNotFoundException(courseId);
    }
}
