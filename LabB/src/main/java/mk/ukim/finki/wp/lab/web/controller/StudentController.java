package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student-create")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getStudentCreate(Model model){
        model.addAttribute("create",true);
        model.addAttribute("list",false);
        return "listStudents";
    }

    @PostMapping
    public String createStudent(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String name,
                                @RequestParam String surname, Model model){
        model.addAttribute("list", true);
        model.addAttribute("create", false);
        if(studentService.listAll().stream().anyMatch(student -> student.getUsername().toLowerCase().equals(username)))
               return "redirect:/courses/add-student?error=UsernameAlreadyExists";
        else {
            studentService.save(username, password, name, surname);
            return "redirect:/courses/add-student";
        }
    }

}
