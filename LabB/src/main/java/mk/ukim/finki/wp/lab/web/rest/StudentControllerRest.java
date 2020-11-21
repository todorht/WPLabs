package mk.ukim.finki.wp.lab.web.rest;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentControllerRest {

    private final StudentService studentService;

    public StudentControllerRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll(){
        return this.studentService.listAll();
    }

    @GetMapping("/{username}")
    public Student findByUsername(@PathVariable String username){
        return this.studentService.findBtUsername(username);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Course> deleteById(@PathVariable String username){
        if(this.studentService.deleteByUsername(username)) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public Student addStudent(@RequestParam String username, @RequestParam String password,@RequestParam String name,@RequestParam String surname){
        return this.studentService.save(username, password, name, surname);
    }

    @PostMapping("/add-course")
    public Student addStudent(@RequestParam String username, @RequestParam long courseId){
        return this.studentService.addCourse(username,courseId);
    }
}