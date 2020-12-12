package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Teacher;
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
        return this.teacherRepository.findById(id);
    }

    @Override
    public Teacher save(String name, String surname) {
            if(this.teacherRepository.findAll().stream().noneMatch(t->t.getName().equals(name))){
                return new Teacher(name,surname);
            }
        return null;
    }

    @Override
    public Teacher addCourse(long teacherId, long courseId) {
        this.courseRepository.findById(courseId).get().setTeacher(this.teacherRepository.findById(teacherId));
        this.teacherRepository.findById(teacherId).getCourses()
                .add(this.courseRepository.findById(courseId).get());
        return this.teacherRepository.findById(teacherId);
    }

    @Override
    public void deleteById(long teacherId) {
        this.teacherRepository.deleteById(teacherId);
    }
}
