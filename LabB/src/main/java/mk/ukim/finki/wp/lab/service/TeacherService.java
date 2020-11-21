package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Teacher;

import java.util.List;

public interface TeacherService {

    public List<Teacher> findAll();
    public Teacher findById(Long id);
    public Teacher save(String name, String surname);
    public Teacher addCourse(long teacherId, long courseId);
    public boolean deleteById(long teacherId);
}
