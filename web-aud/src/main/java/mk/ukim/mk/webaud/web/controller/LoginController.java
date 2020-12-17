package mk.ukim.mk.webaud.web.controller;

import mk.ukim.mk.webaud.model.User;
import mk.ukim.mk.webaud.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.mk.webaud.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user;
        try {
            user = this.authService.login(request.getParameter("username")
                    ,request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        } catch (InvalidUserCredentialsException exception){
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("bodyContent","login");
            return "master-template";
        }
    }
}
