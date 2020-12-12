package mk.ukim.mk.webaud.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.IModel;

@Controller
@RequestMapping("/")
public class HomeController {



    @GetMapping("home")
    public String getHomePage(Model model){
        model.addAttribute("bodyContent","home");
        return "master-template";
    }

    @GetMapping("access_denied")
    public String accessDeniedPage(Model model){
        model.addAttribute("bodyContent","access_denied");
        return "master-template";
    }
}
