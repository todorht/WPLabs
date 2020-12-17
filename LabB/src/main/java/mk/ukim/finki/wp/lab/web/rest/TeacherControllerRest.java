package mk.ukim.finki.wp.lab.web.rest;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundedException;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teacher")
public class TeacherControllerRest {

    private final TeacherService teacherService;

    public TeacherControllerRest(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> findAll(){
        return this.teacherService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Teacher> findByUsername(@PathVariable long id){
        return this.teacherService.findById(id);
    }

    @PostMapping("/add")
    public Teacher addStudent(@RequestParam String name,@RequestParam String surname){
        return this.teacherService.save(name, surname);
    }

    @PostMapping("/add-course")
    public Optional<Teacher> addStudent(@RequestParam long id, @RequestParam long courseId){
        return this.teacherService.addCourse(id,courseId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteById(@PathVariable long id){
        try {
            this.teacherService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (TeacherNotFoundedException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
