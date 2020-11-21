package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.CourseAlreadyExistsException;
import mk.ukim.finki.wp.lab.model.exception.CourseNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.SerializationUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseRepository implements Serializable {

    List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init(){
        courses.add(new Course("Web programiranje","Web programiranje"));
        courses.add(new Course("Napredno programiranje","Napredno programiranje"));
        courses.add(new Course("Mobilni platformi i programiranje","Mobilni platformi i programiranje"));
        courses.add(new Course("Algoritmi i podatochni strukturi","Algoritmi i podatochni strukturi"));
        courses.add(new Course("Optichki mrezi i tehnologii","Optichki mrezi i tehnologii"));
    }

    public List<Course> findAllCourses(){
        return courses.stream().sorted(Comparator.comparing(course -> course.getName().toLowerCase())).collect(Collectors.toList());
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        return courses.stream().filter(r->r.getCourseId().equals(courseId)).findFirst().get().getStudents();
    }

    public Course addStudentToCourse(Student student, Long courseId){
        Course course = findById(courseId);
        course.getStudents().add(student);
        return course;
    }

    public Course findById(Long id){
        return courses.stream().filter(r->r.getCourseId().equals(id)).findFirst().orElseThrow(()->new CourseNotFoundException(id));
    }

    public boolean deleteById(Long id){
        return courses.removeIf(c->c.getCourseId().equals(id));
    }

    public Course addTeacher(Teacher teacher, Course course){
        course.setTeacher(teacher);
        return course;
    }

    public Course save(String name, String description, Teacher teacher){
            if(courses.stream().anyMatch(c->c.getName().equals(name))){
                throw new CourseAlreadyExistsException(name);
            }else {
                Course course = new Course(name, description, teacher);
                courses.add(course);
                return course;
            }
        }

    public Course save(String name, String description){
        if(courses.stream().anyMatch(c->c.getName().equals(name))){
            return null;
        }else {
            Course course = new Course(name, description);
            courses.add(course);
            return course;
        }
    }

}
