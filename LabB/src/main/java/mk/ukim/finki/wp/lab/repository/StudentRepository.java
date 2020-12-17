//package mk.ukim.finki.wp.lab.repository;
//
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.model.Student;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class StudentRepository {
//    final List<Student> students = new ArrayList<>();
//
//    @PostConstruct
//    public void init(){
//        students.add(new Student("todorht", "tode", "Todor", "Todorovski"));
//        students.add(new Student("pero", "tode", "Petar", "Stojkov"));
//        students.add(new Student("igor", "tode", "Igor", "Maksimov"));
//        students.add(new Student("joco", "tode", "Jordan", "Ivanov"));
//        students.add(new Student("temel", "tode", "Kristijan", "Temelkovski"));
//    }
//
//    public List<Student> findAllStudents(){
//        return students;
//    }
//
//    public List<Student> findAllByNameOrSurname(String text){
//        return students.stream().filter(r->r.getName().equals(text) || r.getSurname().equals(text)).collect(Collectors.toList());
//    }
//
//    public Student findByUsername(String username){
//        return students.stream().filter(r->r.getUsername().equals(username)).findFirst().get();
//    }
//
//    public Student addStudent(String username, String password,String name, String surname){
//        Student student = new Student(username, password, name, surname);
//        students.add(student);
//        return student;
//
//    }
//
////    public Student addCourse(Student student, Course course){
////        student.getCourseList().add(course);
////        return student;
////    }
//
////    public List<Course> findCourses(String username){
////        return this.findByUsername(username).getCourseList();
////    }
//
//    public boolean deleteByUsername(String username){
//        return students.removeIf(s->s.getUsername().equals(username));
//    }
//}
