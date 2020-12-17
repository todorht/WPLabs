package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.model.exception.CourseAlreadyExistsException;
import mk.ukim.finki.wp.lab.model.exception.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.exception.StudentNotFoundException;
import mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundedException;
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
        if(this.courseRepositoryJpa.findById(courseId).isPresent()) return courseRepositoryJpa.findById(courseId).get().getStudents();
        else throw new CourseNotFoundException(courseId);
    }

    @Override
    @Transactional
    public void addStudentInCourse(String username, Long course) {
        if(this.courseRepositoryJpa.findById(course).isPresent()){
            if(studentRepositoryJpa.findById(username).isPresent()) {
                studentRepositoryJpa.findById(username).get().getCourseList().add(courseRepositoryJpa.findById(course).get());
                courseRepositoryJpa.findById(course).get().getStudents()
                        .add(studentRepositoryJpa.findById(username).orElseThrow(() -> new StudentNotFoundException(username)));
                this.courseRepositoryJpa.save(courseRepositoryJpa.findById(course).get());
            }
        } else throw new CourseNotFoundException(course);
    }

    @Override
    public List<Course> listAll(){
        return this.courseRepositoryJpa.findAll();
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepositoryJpa.findById(courseId).orElseThrow(()-> new CourseNotFoundException(courseId));
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepositoryJpa.deleteById(id);
    }

    @Override
    public Course save(String name, String description, long teacher, Type type) {
        if(this.courseRepositoryJpa.findAll().stream().noneMatch(course ->
                course.getName().equals(name))) {
            Teacher teacher1 = this.teacherRepositoryJpa.findById(teacher)
                    .orElseThrow(() -> new TeacherNotFoundedException(teacher));
            Course course = new Course(name,description, teacher1, type);
            teacher1.getCourses().add(course);
            return this.courseRepositoryJpa.save(course);
        } else throw new CourseAlreadyExistsException(name);
    }

    @Override
    public Course save(String name, String description, Type type) {
        if(this.courseRepositoryJpa.findAll().stream().noneMatch(course -> course.getName().equalsIgnoreCase(name))) {
            return this.courseRepositoryJpa.save(new Course(name, description, type));
        }else throw new CourseAlreadyExistsException(name);
    }

    @Override
    public Course setTeacherC(long courseId, long teacherId) {
        if(this.courseRepositoryJpa.findById(courseId).isPresent())
            this.courseRepositoryJpa.findById(courseId).get().setTeacher(teacherRepositoryJpa.findById(teacherId).get());
        return this.courseRepositoryJpa.findById(courseId).get();
    }

    @Override
    public Course editCourse(long courseId, String name, String description, long teacherId, Type type) {
        if(courseRepositoryJpa.findAll().stream()
               .noneMatch(c->c.getName().toLowerCase()
                       .equals(name.toLowerCase()) && !c.getCourseId()
                       .equals(courseId))) {
            this.courseRepositoryJpa.deleteById(courseId);
        }
        return courseRepositoryJpa.save(new Course(name,description,teacherRepositoryJpa.findById(teacherId).get(), type));
    }

}
