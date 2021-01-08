package mk.ukim.finki.wp.lab.web.rest;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundedException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
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

    @GetMapping("/{id}/courses")
    public List<Course> getTeacherCourses(@PathVariable long id){
        return this.teacherService.findTeacherCourses(id);
    }

    @PostMapping("/add")
    public Teacher addStudent(@RequestParam String name,@RequestParam String surname){
        return this.teacherService.save(name, surname);
    }

    @PostMapping("/{id}/grade")
    public Grade setGrade(@PathVariable Long id,@RequestParam String username,
                          @RequestParam Long courseId, @RequestParam Character grade){
       return this.teacherService.setGrade(id,courseId,username,grade);
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
