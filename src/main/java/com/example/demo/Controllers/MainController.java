package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MainController {
@GetMapping("/")
    public String home(@RequestParam (name = "name", required = false, defaultValue = "Olga") String name, Model model ){
        model.addAttribute("namehtml", name);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model ){
        model.addAttribute("about");
        return "about";
    }
//    @GetMapping("/contacts")
//    public String contacts(@CookieValue(value = "userLogin") String userLogin, Model model ){
//        model.addAttribute("contacts");
//        model.addAttribute("userLogin", userLogin);
//        return "contacts";
//    }

    @GetMapping("/contacts")
    public String contacts( Model model ){
        model.addAttribute("contacts");
        return "contacts";
    }

    @GetMapping("/poems")
    public String poems(Model model ){
        model.addAttribute("poems");
        return "poems_All/poems";
    }
    @GetMapping("/tales")
    public String tales(Model model ){
        model.addAttribute("tale");
        return "tales_All/tale";
    }

}
