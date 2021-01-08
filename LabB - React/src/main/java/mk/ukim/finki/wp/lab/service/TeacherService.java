package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

     List<Teacher> findAll();
     Optional<Teacher> findById(Long id);
     Teacher save(String name, String surname);
     void deleteById(long teacherId);
     List<Course> findTeacherCourses(Long teacherId);
     Grade setGrade(Long teacherId, Long courseId, String username, Character grade);
}
