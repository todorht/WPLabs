package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@WebServlet(name = "ListStudentServlet", urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {


    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    @Transactional
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String student = req.getParameter("student");
        String courseId = (String) req.getSession().getAttribute("courseId");
        if(student==null || student.isEmpty()){
            resp.sendRedirect("/AddStudent");
        }else {
            courseService.addStudentInCourse(student,Long.parseLong(courseId));
            resp.sendRedirect("/StudentEnrollmentSummary");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("list", true);
        String courseId = (String)req.getSession().getAttribute("courseId");
        context.setVariable("students", studentService.filterStudents(Long.parseLong(courseId)));
        springTemplateEngine.process("listStudents.html",context,resp.getWriter());
    }
}
