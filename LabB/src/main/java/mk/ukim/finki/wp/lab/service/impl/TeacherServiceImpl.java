package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.TeacherAlreadyExists;
import mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundedException;
import mk.ukim.finki.wp.lab.repository.jpa.CoursesRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepositoryJpa;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryJpa teacherRepository;
    private final CoursesRepositoryJpa courseRepository;

    public TeacherServiceImpl(TeacherRepositoryJpa teacherRepository, CoursesRepositoryJpa courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
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
    public Optional<Teacher> addCourse(long teacherId, long courseId) {
        if(this.teacherRepository.findById(teacherId).isPresent()){
            if(this.courseRepository.findById(courseId).isPresent()){
                this.teacherRepository.findById(teacherId).get().getCourses()
                        .add(this.courseRepository.findById(courseId).get());
            }
        }
        return this.teacherRepository.findById(teacherId);
    }

    @Override
    public void deleteById(long teacherId) {
        if(this.teacherRepository.findById(teacherId).isPresent()) {
            this.teacherRepository.deleteById(teacherId);
        }else throw new TeacherNotFoundedException(teacherId);
    }
}
