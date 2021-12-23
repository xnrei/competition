package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.models.Competitor;
import com.example.servingwebcontent.repos.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompetitorController{

    @Autowired
    private CompetitorRepository competitorRepository;

    @GetMapping("/competitors")
    public String competitors(Model model){
        Iterable<Competitor> competitors = competitorRepository.findAll();
        model.addAttribute("competitors", competitors);
        return "competitors";
    }

    @PostMapping("/competitors")
    public String act(@RequestParam String name, @RequestParam String surname, Model model){
        Competitor competitor = new Competitor();
        competitor.setName(name);
        competitor.setSurname(surname);
        competitorRepository.save(competitor);
        Iterable<Competitor> competitors = competitorRepository.findAll();
        model.addAttribute("competitors", competitors);
        return "competitors";
    }

    @GetMapping("/competitors/edit")
    public String edit(){
        return "competitors-edit";
    }

    @PostMapping("/competitors/edit")
    public String doEdit(@RequestParam Long id, @RequestParam String name, @RequestParam String surname, Model model){
        Competitor competitor = competitorRepository.findById(id).orElseThrow();
        competitor.setName(name);
        competitor.setSurname(surname);
        competitorRepository.save(competitor);
        return "redirect:/competitors";
    }

    @GetMapping("/competitors/delete")
    public String delete(){
        return "competitors-delete";
    }

    @PostMapping("/competitors/delete")
    public String doDelete(@RequestParam Long id, Model model){
        Competitor competitor = competitorRepository.findById(id).orElseThrow();
        competitorRepository.delete(competitor);
        return "redirect:/competitors";
    }
}
