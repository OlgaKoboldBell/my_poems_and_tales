package com.example.demo.Controllers;

import com.example.demo.Models.HamsterModel;
import com.example.demo.Models.HedgehogModel;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.TaleModel;
import com.example.demo.Repo.IHedgehogRepository;
import com.example.demo.Repo.ITaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class TaleController {
    @Autowired
    private ITaleRepository iTaleRepository;


    @GetMapping("/tales_about_kittens")
    public String tales_about_kittens(Model model ){
        model.addAttribute("tales_about_kittens");
        return "tales_All/tales_about_kittens";
    }

    @GetMapping("/counters")
    public String counters(Model model ){
        model.addAttribute("counters");
        return "tales_All/counters";
    }

//    @GetMapping("/tales_about_hedgehog")
//    public String tales_about_hedgehog(Model model ){
//        model.addAttribute("tales_about_hedgehog");
//        return "tales_All/tales_about_hedgehog";
//    }

    @GetMapping("/tales_about_grasshopper")
    public String tales_about_grasshopper(Model model ){
        model.addAttribute("tales_about_grasshopper");
        return "tales_All/tales_about_grasshopper";
    }

//    @GetMapping("/tales_about_hamster")
//    public String tales_about_hamster(Model model ){
//        model.addAttribute("tales_about_hamster");
//        return "tales_All/tales_about_hamster";
//    }

//    @GetMapping("/tales_about_hamster")
//    public String tales_about_hamster(@CookieValue(value = "userLogin") String userLogin, Model model ){
//        List<HamsterModel> talesAboutHamster = (List<HamsterModel>) iHamsterRepository.findAll();
//        model.addAttribute("talesAboutHamster", talesAboutHamster);
//        model.addAttribute("userLogin", userLogin);
//        return "tales_All/tales_about_hamster";
//    }


//    @GetMapping("/tales_about_hedgehog")
//    public String tales_about_hedgehog(@CookieValue(value = "userLogin") String userLogin, Model model ){
//        List<HedgehogModel> talesAboutHedgehog = (List<HedgehogModel>) iHedgehogRepository.findAll();
//        model.addAttribute("talesAboutHedgehog", talesAboutHedgehog);
//        model.addAttribute("userLogin", userLogin);
//        return "tales_All/tales_about_hedgehog";
//    }

    @GetMapping("/tales_about_duckling")
    public String tales_about_duckling(@CookieValue(value = "userLogin", defaultValue = "noname") String userLogin, Model model ){
        List<TaleModel> talesAboutDuckling = (List<TaleModel>) iTaleRepository.findAll();

        model.addAttribute("talesAboutDuckling", talesAboutDuckling);
        model.addAttribute("userLogin", userLogin);
        return "tales_All/tales_about_duckling";
    }



    //дії з таблицею
    @PostMapping("/tales_about_duckling/new")
    public String AddTaleNew(@RequestParam String title, @RequestParam String link, @RequestParam String content, Model model) {
        TaleModel taleModel = new TaleModel(title, link, content);
        iTaleRepository.save(taleModel);
        return "redirect:/tales_about_duckling";
    }

    @GetMapping("/tales_about_duckling/{id}")
    public String TaleById(@PathVariable(value = "id") long id, Model model){

        TaleModel taleModel = iTaleRepository.findById(id).get();
        model.addAttribute("tale", taleModel);
        return "post";
    }

    @GetMapping("/tales_about_duckling/{id}/remove")
    public String deleteTaleById(@PathVariable(value = "id") long id) {
        if(!iTaleRepository.existsById(id)){
            return "redirect:/tales_about_duckling";
        }
        iTaleRepository.deleteById(id);
        return "redirect:/tales_about_duckling";
    }

    //редагування
    @GetMapping("/tales_about_duckling/{id}/edit")
    public String TaleByIdEdit(@PathVariable(value = "id") long id, Model model) {
        // Перевірка, чи існує пост з таким id
        if(!iTaleRepository.existsById(id)){
            return "redirect:/tales_about_duckling";
        }
        TaleModel taleModel = iTaleRepository.findById(id).get();
        model.addAttribute("post", taleModel);
        return "tales_All/taleedit";
    }

    @PostMapping("/tales_about_duckling/{id}/edit")
    public String TaleByIdEditSave(@RequestParam String title, @RequestParam String link, @RequestParam String content, @PathVariable(value = "id") long id, Model model) {

        if(!iTaleRepository.existsById(id)){
            return "redirect:/tales_about_duckling";
        }
        TaleModel taleModel = iTaleRepository.findById(id).get();
        taleModel.setTitle(title);
        taleModel.setLink(link);
        taleModel.setContent(content);
        iTaleRepository.save(taleModel);
        // перенаправлення на сторінку списку казок
        return "redirect:/tales_about_duckling";
    }
}
