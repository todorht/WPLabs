//package mk.ukim.finki.wp.lab.web.servlet;
//
//import mk.ukim.finki.wp.lab.model.Grade;
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.service.CourseService;
//import mk.ukim.finki.wp.lab.service.GradeService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet(name = "studentEnrollmentSummary",urlPatterns = "/StudentEnrollmentSummary")
//public class StudentEnrollmentSummary extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final CourseService courseService;
//    private final GradeService gradeService;
//
//    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService, GradeService gradeService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.courseService = courseService;
//        this.gradeService = gradeService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context = new WebContext(req,resp,req.getServletContext());
//        String courseId = (String) req.getSession().getAttribute("courseId");
//        List<Student> studentList = courseService.listStudentsByCourse(Long.valueOf(courseId));
//
//        for(Student student:studentList){
//            student.setGrade(gradeService.findGrade(Long.valueOf(courseId),student.getUsername()));
//        }
//        context.setVariable("students", studentList);
//        context.setVariable("course", courseService.findById(Long.parseLong(courseId)));
//        req.getSession().invalidate();
//        springTemplateEngine.process("studentsInCourse.html",context,resp.getWriter());
//    }
//}
