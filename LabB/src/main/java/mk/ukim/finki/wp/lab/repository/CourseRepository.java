package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.util.SerializationUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository implements Serializable {

    List<Course> courses = new ArrayList<>();

    public final StudentRepository studentRepository;

    public CourseRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void init(){
        courses.add(new Course("Web programiranje","Web programiranje", null));
        courses.add(new Course("Napredno programiranje","Napredno programiranje", null));
        courses.add(new Course("Mobilni platformi i programiranje","Mobilni platformi i programiranje", null));
        courses.add(new Course("Algoritmi i podatochni strukturi","Algoritmi i podatochni strukturi", null));
        courses.add(new Course("Optichki mrezi i tehnologii","Optichki mrezi i tehnologii", null));
    }

    public List<Course> findAllCourses(){
        return courses;
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
        return courses.stream().filter(r->r.getCourseId().equals(id)).findFirst().get();
    }
}
