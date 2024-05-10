package com.example.demo.Controllers;

//import ch.qos.logback.core.model.Model;
import com.example.demo.Models.PostModel;
import com.example.demo.Repo.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class BlogController {
    @Autowired
    private IPostRepository iPostRepository;
    @GetMapping("/blogmain")
    public String BlogMain(@CookieValue(value = "userLogin", defaultValue = "noname") String userLogin, Model model){

        Iterable<PostModel> posts = iPostRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("userLogin", userLogin);
//        це сторінка
        return "blogmain";
    }
    @GetMapping("/blog/new")
    public String BlogNew(@CookieValue(value = "userLogin") String userLogin, Model model){
        model.addAttribute("userLogin", userLogin);
        return "blognew";
    }


    //додавання нових даних
    @PostMapping("/blog/new")
    public String AddBlogNew(@RequestParam String title, @RequestParam String description, @CookieValue(value = "userLogin") String userLogin, Model model){
        PostModel postModel = new PostModel(title, description);
        model.addAttribute("userLogin", userLogin);
        iPostRepository.save(postModel);
        // шлях
        return "redirect:/blogmain";
    }

    // шлях
    @GetMapping("/blogmain/{id}")
    public String BlogById(@PathVariable(value = "id") long id, @CookieValue(value = "userLogin") String userLogin, Model model){
        if(!iPostRepository.existsById(id)){
            //це вью - сторінка
            return "redirect:/blogmain";
        }
        PostModel postModel = iPostRepository.findById(id).get();
        model.addAttribute("post", postModel);
        model.addAttribute("userLogin", userLogin);
                return "post";

    }

    //редагування
    @GetMapping("/blogmain/{id}/edit")
    public String BlogByIdEdit(@PathVariable(value = "id") long id, @CookieValue(value = "userLogin") String userLogin,  Model model) {
        model.addAttribute("userLogin", userLogin);
        // Перевірка, чи існує пост з таким id
        if(!iPostRepository.existsById(id)){
            return "redirect:/blogmain";
        }
        PostModel postModel = iPostRepository.findById(id).get();
        model.addAttribute("post", postModel);
        model.addAttribute("userLogin", userLogin);
        return "blogedit";
    }

    //зберігаю оновлену інформацію
    @PostMapping("/blogmain/{id}/edit")
    public String BlogByIdEditSave(@RequestParam String title, @RequestParam String description, @PathVariable(value = "id") long id, @CookieValue(value = "userLogin") String userLogin, Model model) {
        model.addAttribute("userLogin", userLogin);
        if(!iPostRepository.existsById(id)){
            return "redirect:/blogmain";
        }
        PostModel postModel = iPostRepository.findById(id).get();
        postModel.setTitle(title);
        postModel.setDescription(description);
        iPostRepository.save(postModel);
        // шлях
        return "redirect:/blogmain";
    }


    @GetMapping("/blogmain/{id}/remove")
    public String deleteBlogById(@PathVariable(value = "id") long id) {
        if(!iPostRepository.existsById(id)){
            return "redirect:/blogmain";
        }
        iPostRepository.deleteById(id);
        return "redirect:/blogmain";
    }


}
