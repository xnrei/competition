package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.models.Competitor;
import com.example.servingwebcontent.models.Genre;
import com.example.servingwebcontent.models.Work;
import com.example.servingwebcontent.repos.CompetitorRepository;
import com.example.servingwebcontent.repos.GenreRepository;
import com.example.servingwebcontent.repos.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkController{

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CompetitorRepository competitorRepository;

    @GetMapping("/works")
    public String works(Model model){
        Iterable<Work> works = workRepository.findAll();
        model.addAttribute("works", works);
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        Iterable<Competitor> competitors = competitorRepository.findAll();
        model.addAttribute("competitors", competitors);
        model.addAttribute("space", " ");
        return "works";
    }

    @PostMapping("/works")
    public String act(@RequestParam String name, @RequestParam Genre genre, @RequestParam Competitor competitor, Model model){
        Work work = new Work();
        work.setName(name);
        work.setGenre(genre);
        work.setCompetitor(competitor);
        workRepository.save(work);
        Iterable<Work> works = workRepository.findAll();
        model.addAttribute("works", works);
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        Iterable<Competitor> competitors = competitorRepository.findAll();
        model.addAttribute("competitors", competitors);
        model.addAttribute("space", " ");
        return "works";
    }

    @GetMapping("/works/edit")
    public String edit(Model model){
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        Iterable<Competitor> competitors = competitorRepository.findAll();
        model.addAttribute("competitors", competitors);
        model.addAttribute("space", " ");
        return "works-edit";
    }

    @PostMapping("/works/edit")
    public String doEdit(@RequestParam Long id, @RequestParam String name, @RequestParam Genre genre, @RequestParam Competitor competitor, Model model){
        Work work = workRepository.findById(id).orElseThrow();
        work.setName(name);
        work.setGenre(genre);
        work.setCompetitor(competitor);
        workRepository.save(work);
        return "redirect:/works";
    }

    @GetMapping("/works/delete")
    public String delete(){
        return "works-delete";
    }

    @PostMapping("/works/delete")
    public String doDelete(@RequestParam Long id, Model model){
        Work work = workRepository.findById(id).orElseThrow();
        workRepository.delete(work);
        return "redirect:/works";
    }
}
