package fr.maboite.secureapp.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AccessDeniedController {
   
    @GetMapping("/denied.html")
    public String showAccessDenied() {
        return "denied";
    }
    
}