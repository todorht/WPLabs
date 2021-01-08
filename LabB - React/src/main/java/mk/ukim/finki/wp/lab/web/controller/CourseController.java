package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.model.exception.CourseAlreadyExistsException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final GradeService gradeService;

    public CourseController(CourseService courseService, StudentService studentService, TeacherService teacherService, GradeService gradeService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request){
        if(error!=null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        request.getSession().invalidate();
        model.addAttribute("courses", this.courseService.listAll());
        model.addAttribute("students",this.studentService.listAll());
        return "listCourses";
    }

    @PostMapping
    public String selectCourse(HttpServletRequest request){
        String courseId = request.getParameter("course");
        request.getSession().setAttribute("courseId", courseId);
        return "redirect:/AddStudent";
    }

    @GetMapping("/edit/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model, HttpServletRequest request){
        if(courseService.findById(id)!=null) {
            Course course = courseService.findById(id);
            model.addAttribute("course", course);
            model.addAttribute("teachers", teacherService.findAll());
            request.getSession().setAttribute("deleteId", course.getCourseId());
            return "add-course";
        }
        return "redirect:/courses?error=CourseNotFound";
    }

    @GetMapping("/add-form")
    public String saveCourse(Model model){
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "add-course";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam(required = false) Long teachers,
                             @RequestParam Type type,
                             HttpServletRequest request){
        Long id = (Long) request.getSession().getAttribute("deleteId");
        if(id!=null){
            if(courseService.editCourse(id, name, description, teachers, type)!=null) {
                request.getSession().setAttribute("deleteId", null);
                return "redirect:/courses";
            }else{
                request.getSession().setAttribute("deleteId", null);
                return "redirect:/courses?error=Course already exists (EDIT)";
            }
        }
        try {
            List<Course> courses = courseService.listAll();
            if (teachers != null) courses.add(courseService.save(name, description, teachers, type));
            else courses.add(courseService.save(name, description, type));
            return "redirect:/courses";
        }catch (CourseAlreadyExistsException e) {
            return "redirect:/courses?error=Course already exists (ADD)";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        if(courseService.findById(id)!=null) {
            this.gradeService.deleteById(this.gradeService.findCourseGrades(id));
            courseService.deleteById(id);
            return "redirect:/courses";
        }else return "redirect:/courses?error=CourseNotFound";
    }
}
