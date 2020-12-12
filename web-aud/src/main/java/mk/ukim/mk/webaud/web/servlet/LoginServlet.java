package mk.ukim.mk.webaud.web.servlet;

import mk.ukim.mk.webaud.model.User;
import mk.ukim.mk.webaud.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.mk.webaud.service.AuthService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthService authService;

    public LoginServlet(SpringTemplateEngine springTemplateEngine, AuthService authService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authService = authService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        springTemplateEngine.process("login.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = null;
        try{
            user = authService.login(username,password);

        }catch (InvalidUserCredentialsException ex){
            WebContext context = new WebContext(req,resp,req.getServletContext());
            context.setVariable("hasErrors",true);
            context.setVariable("errors",ex.getMessage());
            springTemplateEngine.process("login.html",context,resp.getWriter());
        }
        req.getSession().setAttribute("user",user);
        resp.sendRedirect("/servlet/thymeleaf/category");
    }
}
