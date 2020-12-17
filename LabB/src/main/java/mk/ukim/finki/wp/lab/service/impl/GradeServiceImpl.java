package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.exception.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.exception.StudentNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.CoursesRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final StudentRepositoryJpa studentRepository;
    private final CoursesRepositoryJpa coursesRepository;
    private final GradeRepositoryJpa gradeRepository;

    public GradeServiceImpl(StudentRepositoryJpa studentRepository,
                            CoursesRepositoryJpa coursesRepository,
                            GradeRepositoryJpa gradeRepository) {
        this.studentRepository = studentRepository;
        this.coursesRepository = coursesRepository;
        this.gradeRepository = gradeRepository;
    }


    @Override
    public Grade save(Long courseId, String student, Character character) {
        try {
            return gradeRepository.save(new Grade(character, studentRepository.findById(student).get(), coursesRepository.findById(courseId).get()));
        }catch (StudentNotFoundException e) {
            throw new StudentNotFoundException(student);
        } catch (CourseNotFoundException e){
            throw new CourseNotFoundException(courseId);
        }

    }

    @Override
    public Character findGrade(Long courseId, String student) {
        Grade grade = gradeRepository.findAll().stream()
                .filter(g -> g.getStudent().getUsername().equals(student))
                .filter(g -> g.getCourse().getCourseId().equals(courseId))
                .findFirst().orElse(null);
       if(grade!=null) return grade.getGrade();
       else return '/';
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }
}
