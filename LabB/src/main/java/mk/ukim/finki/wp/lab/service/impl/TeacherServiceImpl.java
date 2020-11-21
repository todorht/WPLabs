package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return this.teacherRepository.findById(id);
    }

    @Override
    public Teacher save(String name, String surname) {
        return this.teacherRepository.save(name, surname);
    }

    @Override
    public Teacher addCourse(long teacherId, long courseId) {
        this.courseRepository.findById(courseId).setTeacher(this.teacherRepository.findById(teacherId));
        return this.teacherRepository.addCourse(teacherRepository.findById(teacherId),courseRepository.findById(courseId));
    }

    @Override
    public boolean deleteById(long teacherId) {
        return this.teacherRepository.deleteById(teacherId);
    }
}
