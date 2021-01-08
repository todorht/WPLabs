package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Grade;

import java.util.List;

public interface GradeService {
    Grade save(Long courseId, String student, Character character);
    Character findGrade(Long courseId, String student);
    List<Grade> findAll();
    List<Long> findCourseGrades(Long courseId);
    void deleteById(List<Long> gradeId);
}
