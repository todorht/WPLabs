package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {
    List<Teacher> teachers = new ArrayList<>();

    @PostConstruct
    public void init(){
        teachers.add(new Teacher("Riste", "Stojanov"));
        teachers.add(new Teacher("Kostadin", "Mishev"));
        teachers.add(new Teacher("Dimitar", "Trajanov"));
        teachers.add(new Teacher("Ana", "Todorovska"));
        teachers.add(new Teacher("Igor", "Mishkovski"));
    }

    public List<Teacher> findAll(){
        return teachers;
    }

    public Teacher findById(Long id){
        return teachers.stream().filter(t->t.getId().equals(id)).findFirst().orElse(null);
    }

    public Teacher save(String name, String surname){
        Teacher teacher = new Teacher(name,surname);
        teachers.add(teacher);
        return teacher;
    }

    public Teacher addCourse(Teacher teacher, Course course){
       teacher.getCourses().add(course);
       return teacher;
    }

    public boolean deleteById(long id){
        return teachers.removeIf(t->t.getId().equals(id));
    }
}
