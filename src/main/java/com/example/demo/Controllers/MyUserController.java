package com.example.demo.Controllers;

import com.example.demo.Models.MyUserModel;
import com.example.demo.Repo.IMyUserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyUserController {
    @Autowired
    private IMyUserRepository iMyUserRepository;

    @GetMapping("/my-home")
    public String showHomePage(Model model) {
        model.addAttribute("login", "");
        return "index";
    }

    @PostMapping("/home/new")
    public String AddMyUserNew(@RequestParam String login, @RequestParam String password, Model model, HttpServletResponse response) {
        MyUserModel myUserModel = new MyUserModel(login, password);
        iMyUserRepository.save(myUserModel);
        if (response != null) {
            Cookie loginCookie = new Cookie("userLogin", login);
            loginCookie.setPath("/");
            loginCookie.setMaxAge(30 * 24 * 60 * 60); // Кука буде зберігатися протягом 30 днів
            response.addCookie(loginCookie);

        }
        model.addAttribute("welcomeMessage", "Вітаю, " + login + "!");
        model.addAttribute("login", login);
        return "home";
    }

    @GetMapping(value = "/logout")
    public String readCookie(HttpServletRequest request, HttpServletResponse response, @CookieValue(value = "userLogin", required = false) String userLogin) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userLogin")) {
                    cookie.setMaxAge(0); // Встановлюємо час життя куки на нуль, щоб вона видалювалася
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String MyUsers(Model model) {
        Iterable<MyUserModel> users = iMyUserRepository.findAll();
        model.addAttribute("users", users);
        return "home";
    }

    @GetMapping("/myusers")
    public String myusers(Model model) {
        Iterable<MyUserModel> users = iMyUserRepository.findAll();
        model.addAttribute("users", users);
        return "myusers";
    }

    @GetMapping("/home/{id}")
    public String MyUserById(@PathVariable(value = "id") long id, Model model) {
        if (!iMyUserRepository.existsById(id)) {
            return "redirect:/home";
        }
        MyUserModel myUserModel = iMyUserRepository.findById(id).get();
        model.addAttribute("myUser", myUserModel);
        return "post";
    }


}
