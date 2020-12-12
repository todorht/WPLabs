package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;

    public BalloonController(BalloonService balloonService) {
        this.balloonService = balloonService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        model.addAttribute("balloons", balloonService.listAll());
        return "listBalloons";
    }

    
    @PostMapping
    public String chooseBalloon(HttpServletRequest request){
        try {
            String color = request.getParameter("color");
            request.getSession().setAttribute("balloonColor", color);
            return "redirect:/selectBalloonSize.";
        } catch (RuntimeException e){
            return "redirect:/listBalloons?error="+ e.getMessage();
        }
    }
}
