package com.example.demo.Controllers;

import com.example.demo.Models.HamsterModel;
import com.example.demo.Models.TaleModel;
import com.example.demo.Repo.IHamsterRepository;
import com.example.demo.Repo.ITaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HamsterController {
    @Autowired
    private IHamsterRepository iHamsterRepository;


    @GetMapping("/tales_about_hamster")
    public String tales_about_hamster(@CookieValue(value = "userLogin", defaultValue = "noname") String userLogin, Model model ){
        List<HamsterModel> talesAboutHamster = (List<HamsterModel>) iHamsterRepository.findAll();
        model.addAttribute("talesAboutHamster", talesAboutHamster);
        model.addAttribute("userLogin", userLogin);
        return "tales_All/tales_about_hamster";
    }



    //дії з таблицею
    @PostMapping("/tales_about_hamster/new")
    public String AddHamsterNew(@RequestParam String title, @RequestParam String link, @RequestParam String content, Model model) {
        HamsterModel hamsterModel = new HamsterModel(title, link, content);
        iHamsterRepository.save(hamsterModel);
        return "redirect:/tales_about_hamster";
    }

    @GetMapping("/tales_about_hamster/{id}")
    public String HamsterById(@PathVariable(value = "id") long id, Model model){
        if(!iHamsterRepository.existsById(id)){
            return "redirect:/tales_about_hamster";
        }
        HamsterModel hamsterModel = iHamsterRepository.findById(id).get();
        model.addAttribute("hamster", hamsterModel);
        return "post";
    }

    @GetMapping("/tales_about_hamster/{id}/remove")
    public String deleteHamsterById(@PathVariable(value = "id") long id) {
        if(!iHamsterRepository.existsById(id)){
            return "redirect:/tales_about_hamster";
        }
        iHamsterRepository.deleteById(id);
        return "redirect:/tales_about_hamster";
    }

    //редагування
    @GetMapping("/tales_about_hamster/{id}/edit")
    public String HamsterByIdEdit(@PathVariable(value = "id") long id, Model model) {
        // Перевірка, чи існує пост з таким id
        if(!iHamsterRepository.existsById(id)){
            return "redirect:/tales_about_hamster";
        }
        HamsterModel hamsterModel = iHamsterRepository.findById(id).get();
        model.addAttribute("post", hamsterModel);
        return "tales_All/hamsteredit";
    }

    @PostMapping("/tales_about_hamster/{id}/edit")
    public String TaleByIdEditSave(@RequestParam String title, @RequestParam String link, @RequestParam String content, @PathVariable(value = "id") long id, Model model) {

        if(!iHamsterRepository.existsById(id)){
            return "redirect:/tales_about_hamster";
        }
        HamsterModel taleModel = iHamsterRepository.findById(id).get();
        taleModel.setTitle(title);
        taleModel.setLink(link);
        taleModel.setContent(content);
        iHamsterRepository.save(taleModel);
        // перенаправлення на сторінку списку казок
        return "redirect:/tales_about_hamster";
    }

}

