package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId){
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student student = studentRepository.findByUsername(username);
        return courseRepository.addStudentToCourse(student, courseId);
    }

    @Override
    public List<Course> listAll(){
        return courseRepository.findAllCourses();
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.courseRepository.deleteById(id);
    }

    @Override
    public Course save(String name, String description, long teacher) {
              return courseRepository.save(name,description,this.teacherRepository.findById(teacher));
    }

    @Override
    public Course save(String name, String description) {
        return courseRepository.save(name,description);
    }

    @Override
    public Course setTeacher(long teacherId, long courseId) {
        this.teacherRepository.addCourse(this.teacherRepository.findById(teacherId),this.courseRepository.findById(courseId));
        return  this.courseRepository.addTeacher(this.teacherRepository.findById(teacherId),this.courseRepository.findById(courseId));
    }

}
