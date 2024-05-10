package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoemController {
    @GetMapping("/poems_about_animals")
    public String poems_about_animals(Model model ){
        model.addAttribute("poems_about_animals");
        return "poems_All/poems_about_animals";
    }

    @GetMapping("/poems_about_cats")
    public String poems_about_cats(Model model ) {
        model.addAttribute("poems_about_cats");
        return "poems_All/poems_about_cats";
    }
    @GetMapping("/poems_about_nature")
    public String poems_about_nature(Model model ){
        model.addAttribute("poems_about_nature");
        return "poems_All/poems_about_nature";
    }
    @GetMapping("/poems_about_spring")
    public String poems_about_spring(Model model ){
        model.addAttribute("poems_about_spring");
        return "poems_All/poems_about_spring";
    }
    @GetMapping("/poems_about_summer")
    public String poems_about_summer(Model model ){
        model.addAttribute("poems_about_summer");
        return "poems_All/poems_about_summer";
    }
    @GetMapping("/poems_about_autumn")
    public String poems_about_autumn(Model model ){
        model.addAttribute("poems_about_autumn");
        return "poems_All/poems_about_autumn";
    }
    @GetMapping("/poems_about_winter")
    public String poems_about_winter(Model model ){
        model.addAttribute("poems_about_winter");
        return "poems_All/poems_about_winter";
    }
    @GetMapping("/poems_about_dragons")
    public String poems_about_dragons(Model model ){
        model.addAttribute("poems_about_dragons");
        return "poems_All/poems_about_dragons";
    }
    @GetMapping("/poems_counters")
    public String poems_counters(Model model ){
        model.addAttribute("poems_counters");
        return "poems_All/poems_counters";
    }
}
