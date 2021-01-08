package mk.ukim.mk.webaud.web.controller;

import mk.ukim.mk.webaud.model.Role;
import mk.ukim.mk.webaud.model.exceptions.InvalidArgumentsExceptions;
import mk.ukim.mk.webaud.model.exceptions.PasswordDoNotMatchException;
import mk.ukim.mk.webaud.service.AuthService;
import mk.ukim.mk.webaud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final AuthService authService;

    public RegisterController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }


    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role){
        try {
            this.userService.register(username, password, repeatedPassword, name, surname, role);
        }catch (PasswordDoNotMatchException | InvalidArgumentsExceptions exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
        return "redirect:/login";
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }
}
