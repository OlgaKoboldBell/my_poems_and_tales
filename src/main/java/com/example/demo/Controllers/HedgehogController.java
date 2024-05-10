package com.example.demo.Controllers;

import com.example.demo.Models.HamsterModel;
import com.example.demo.Models.HedgehogModel;
import com.example.demo.Repo.IHamsterRepository;
import com.example.demo.Repo.IHedgehogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HedgehogController {
    @Autowired
    private IHedgehogRepository iHedgehogRepository;



    @GetMapping("/tales_about_hedgehog")
    public String tales_about_hedgehog(@CookieValue(value = "userLogin", defaultValue = "noname") String userLogin, Model model ){
        List<HedgehogModel> talesAboutHedgehog = (List<HedgehogModel>) iHedgehogRepository.findAll();
        model.addAttribute("talesAboutHedgehog", talesAboutHedgehog);
        model.addAttribute("userLogin", userLogin);
        return "tales_All/tales_about_hedgehog";
    }



    //дії з таблицею
    @PostMapping("/tales_about_hedgehog/new")
    public String AddHedgehogNew(@RequestParam String title, @RequestParam String link, @RequestParam String content, Model model) {
        HedgehogModel hedgehogModel = new HedgehogModel(title, link, content);
        iHedgehogRepository.save(hedgehogModel);
        return "redirect:/tales_about_hedgehog";
    }

    @GetMapping("/tales_about_hedgehog/{id}")
    public String HedgehogById(@PathVariable(value = "id") long id, Model model){
        if(!iHedgehogRepository.existsById(id)){
            return "redirect:/tales_about_hedgehog";
        }
        HedgehogModel hedgehogModel = iHedgehogRepository.findById(id).get();
        model.addAttribute("hedgehog", hedgehogModel);
        return "post";
    }

    @GetMapping("/tales_about_hedgehog/{id}/remove")
    public String deleteHedgehogById(@PathVariable(value = "id") long id) {
        if(!iHedgehogRepository.existsById(id)){
            return "redirect:/tales_about_hedgehog";
        }
        iHedgehogRepository.deleteById(id);
        return "redirect:/tales_about_hedgehog";
    }

    //редагування
    @GetMapping("/tales_about_hedgehog/{id}/edit")
    public String HedgehogByIdEdit(@PathVariable(value = "id") long id, Model model) {
        // Перевірка, чи існує пост з таким id
        if(!iHedgehogRepository.existsById(id)){
            return "redirect:/tales_about_hedgehog";
        }
        HedgehogModel hedgehogModel = iHedgehogRepository.findById(id).get();
        model.addAttribute("post", hedgehogModel);
        return "tales_All/hedgehogededit";
    }

    @PostMapping("/tales_about_hedgehog/{id}/edit")
    public String TaleByIdEditSave(@RequestParam String title, @RequestParam String link, @RequestParam String content, @PathVariable(value = "id") long id, Model model) {

        if(!iHedgehogRepository.existsById(id)){
            return "redirect:/tales_about_hedgehog";
        }
        HedgehogModel taleModel = iHedgehogRepository.findById(id).get();
        taleModel.setTitle(title);
        taleModel.setLink(link);
        taleModel.setContent(content);
        iHedgehogRepository.save(taleModel);
        // перенаправлення на сторінку списку казок
        return "redirect:/tales_about_hedgehog";
    }
}


