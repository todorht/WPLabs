package mk.ukim.finki.wp.lab.web.rest;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseControllerRest {

    private final CourseService courseService;
    private final StudentService studentService;

    public CourseControllerRest(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<Course> findAll(){
        return this.courseService.listAll();
    }

    @GetMapping("/{id}")
    public Course findById(@PathVariable long id){
        return courseService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteById(@PathVariable long id){
        this.courseService.deleteById(id);
        if(this.courseService.findById(id)==null) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public Course addCourse(@RequestParam String name, @RequestParam String description, @RequestParam Type type){
        return this.courseService.save(name, description, type);
    }

    @PostMapping("/add-student")
    public Course addStudent(@RequestParam long id, @RequestParam String username){
        courseService.addStudentInCourse(username,id);
           return courseService.findById(id);

    }

    @PostMapping("/add-teacher")
    public Course addTeacher(@RequestParam long courseId, @RequestParam long teacherId){
        return courseService.setTeacherC(courseId,teacherId);
    }
}
