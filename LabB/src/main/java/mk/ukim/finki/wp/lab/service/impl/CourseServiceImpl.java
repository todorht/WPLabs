package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.jpa.CoursesRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepositoryJpa;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final StudentRepositoryJpa studentRepositoryJpa;
    private final CoursesRepositoryJpa courseRepositoryJpa;
    private final TeacherRepositoryJpa teacherRepositoryJpa;

    public CourseServiceImpl(StudentRepositoryJpa studentRepository,
                             CoursesRepositoryJpa courseRepositoryJpa,
                             TeacherRepositoryJpa teacherRepository) {
        this.studentRepositoryJpa = studentRepository;
        this.courseRepositoryJpa = courseRepositoryJpa;
        this.teacherRepositoryJpa = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId){
        return courseRepositoryJpa.findById(courseId).get().getStudents();
    }

    @Override
    @Transactional
    public void addStudentInCourse(String username, String course) {
        courseRepositoryJpa.getOne(Long.parseLong(course)).getStudents()
                .add(studentRepositoryJpa.getOne(username));
        this.courseRepositoryJpa.save(courseRepositoryJpa.getOne(Long.parseLong(course)));
    }

    @Override
    public List<Course> listAll(){

        return this.courseRepositoryJpa.findAll();
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepositoryJpa.getOne(courseId);
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepositoryJpa.deleteById(id);
    }

    @Override
    public Course save(String name, String description, long teacher) {
        Teacher teacher1 =  this.teacherRepositoryJpa.findById(teacher);
        return this.courseRepositoryJpa.save(new Course(name,description,teacher1));
    }

    @Override
    public Course save(String name, String description) {
        return this.courseRepositoryJpa.save(new Course(name,description));
    }

    @Override
    public Course setTeacherC(long courseId, long teacherId) {
        this.courseRepositoryJpa.findById(courseId).get().setTeacher(teacherRepositoryJpa.getOne(teacherId));
        return this.courseRepositoryJpa.findById(courseId).get();
    }

    @Override
    public Course editCourse(long courseId, String name, String description, long teacherId) {
        if(courseRepositoryJpa.findAll().stream()
               .noneMatch(c->c.getName().toLowerCase()
                       .equals(name.toLowerCase()) && !c.getCourseId()
                       .equals(courseId))) {
            this.courseRepositoryJpa.deleteById(courseId);
        }else return null;
        return courseRepositoryJpa.save(new Course(name,description,teacherRepositoryJpa.findById(teacherId)));
    }

}
