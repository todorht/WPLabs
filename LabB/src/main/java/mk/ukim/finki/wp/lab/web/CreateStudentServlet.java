package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createStudentServlet", urlPatterns = "/CreateStudent")
public class CreateStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;

    public CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("create", true);
        context.setVariable("list",false);
        springTemplateEngine.process("listStudents.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("create", false);
        context.setVariable("list",true);
        String username = req.getParameter("username");
        req.getSession().setAttribute("username", username);
        String password = req.getParameter("password");
        req.getSession().setAttribute("password", password);
        String name = req.getParameter("name");
        req.getSession().setAttribute("name", name);
        String surname = req.getParameter("surname");
        req.getSession().setAttribute("surname", surname);
        studentService.save(username,password,name,surname);
        resp.sendRedirect("/AddStudent");
    }
}
