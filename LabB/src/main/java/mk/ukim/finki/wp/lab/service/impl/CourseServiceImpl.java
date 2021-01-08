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
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepositoryJpa;
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
    private final GradeRepositoryJpa gradeRepository;

    public CourseServiceImpl(StudentRepositoryJpa studentRepository,
                             CoursesRepositoryJpa courseRepositoryJpa,
                             TeacherRepositoryJpa teacherRepository,
                             GradeRepositoryJpa gradeRepository) {
        this.studentRepositoryJpa = studentRepository;
        this.courseRepositoryJpa = courseRepositoryJpa;
        this.teacherRepositoryJpa = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId){
        if(this.courseRepositoryJpa.findById(courseId).isPresent())
            return courseRepositoryJpa.findById(courseId).get().getStudents();
        else throw new CourseNotFoundException(courseId);
    }

    @Override
    @Transactional
    public void addStudentInCourse(String username, Long course) {
        if(this.courseRepositoryJpa.findById(course).isPresent()){
            if(studentRepositoryJpa.findById(username).isPresent()) {
                courseRepositoryJpa.getOne(course).getStudents()
                        .add(studentRepositoryJpa.findById(username)
                                .orElseThrow(() -> new StudentNotFoundException(username)));
                this.courseRepositoryJpa.save(courseRepositoryJpa.getOne(course));
            }
        } else throw new CourseNotFoundException(course);
    }

    @Override
    public List<Course> listAll(){
        return this.courseRepositoryJpa.findAll();
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepositoryJpa.findById(courseId)
                .orElseThrow(()-> new CourseNotFoundException(courseId));
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
            return this.courseRepositoryJpa.save(course);
        } else throw new CourseAlreadyExistsException(name);
    }

    @Override
    public Course save(String name, String description, Type type) {
        if(this.courseRepositoryJpa.findAll().stream()
                .noneMatch(course -> course.getName().equalsIgnoreCase(name))) {
            return this.courseRepositoryJpa.save(new Course(name, description, type));
        }else throw new CourseAlreadyExistsException(name);
    }

    @Override
    public Course setTeacherC(long courseId, long teacherId) {
        if(this.teacherRepositoryJpa.findById(teacherId).isEmpty())
            throw new TeacherNotFoundedException(teacherId);
        if(this.courseRepositoryJpa.findById(courseId).isPresent()) {
            this.courseRepositoryJpa.findById(courseId).get()
                    .setTeacher(teacherRepositoryJpa.getOne(teacherId));
            return this.courseRepositoryJpa.save(this.courseRepositoryJpa.findById(courseId).get());
        } else throw new CourseNotFoundException(courseId);
    }

    @Override
    public Course editCourse(long courseId, String name, String description, long teacherId, Type type) {

        if(courseRepositoryJpa.findAll().stream()
               .noneMatch(c->c.getName().equalsIgnoreCase(name)
                       && !c.getCourseId().equals(courseId))) {
            Course course = courseRepositoryJpa.findById(courseId)
                    .orElseThrow(()-> new CourseNotFoundException(courseId));
            course.setName(name);
            course.setDescription(description);
            if(this.teacherRepositoryJpa.findById(teacherId).isPresent()){
                course.setTeacher(teacherRepositoryJpa.getOne(teacherId));
            }
            course.setType(type);
            return this.courseRepositoryJpa.save(course);
        } else throw new CourseAlreadyExistsException(name);
    }

    @Override
    public void deleteStudent(long courseId, String username) {
         this.courseRepositoryJpa.findById(courseId).get().getStudents()
                 .removeIf(student -> student.getUsername().equals(username));
    }

}
