package mk.ukim.finki.wp.lab.web.rest;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseControllerRest {

    private final CourseService courseService;


    public CourseControllerRest(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> findAll(){
        return this.courseService.listAll();
    }

    @GetMapping("/{id}")
    public Course findById(@PathVariable long id){
        return courseService.listAll().stream().filter(c->c.getCourseId().equals(id)).findFirst().orElse(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteById(@PathVariable long id){
        if(this.courseService.deleteById(id)) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public Course addCourse(@RequestParam String name, @RequestParam String description){
        return this.courseService.save(name, description);
    }

    @PostMapping("/add-student")
    public Course addStudent(@RequestParam long id, @RequestParam String username){
       return courseService.addStudentInCourse(username,id);
    }

    @PostMapping("/add-teacher")
    public Course addTeacher(@RequestParam long courseId, @RequestParam long teacherId){
        return courseService.setTeacher(courseId,teacherId);
    }
}
