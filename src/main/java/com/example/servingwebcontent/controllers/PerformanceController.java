package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.models.*;
import com.example.servingwebcontent.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PerformanceController{

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;

    @GetMapping("/performances")
    public String works(Model model){
        Iterable<Performance> performances = performanceRepository.findAll();
        model.addAttribute("performances", performances);
        Iterable<Competitor> competitors = competitorRepository.findAll();
        model.addAttribute("competitors", competitors);
        Iterable<Timeslot> timeslots = timeslotRepository.findAll();
        model.addAttribute("timeslots", timeslots);
        model.addAttribute("space", " ");
        return "performances";
    }

    @PostMapping("/performances")
    public String act(@RequestParam Competitor competitor, @RequestParam Timeslot timeslot, Model model){
        Performance performance = new Performance();
        performance.setCompetitor(competitor);
        performance.setTimeslot(timeslot);
        performanceRepository.save(performance);
        Iterable<Performance> performances = performanceRepository.findAll();
        model.addAttribute("performances", performances);
        Iterable<Competitor> competitors = competitorRepository.findAll();
        model.addAttribute("competitors", competitors);
        Iterable<Timeslot> timeslots = timeslotRepository.findAll();
        model.addAttribute("timeslots", timeslots);
        model.addAttribute("space", " ");
        return "performances";
    }

    @GetMapping("/performances/edit")
    public String edit(Model model){
        Iterable<Competitor> competitors = competitorRepository.findAll();
        model.addAttribute("competitors", competitors);
        Iterable<Timeslot> timeslots = timeslotRepository.findAll();
        model.addAttribute("timeslots", timeslots);
        model.addAttribute("space", " ");
        return "performances-edit";
    }

    @PostMapping("/performances/edit")
    public String doEdit(@RequestParam Long id, @RequestParam Competitor competitor, @RequestParam Timeslot timeslot, Model model){
        Performance performance = performanceRepository.findById(id).orElseThrow();
        performance.setCompetitor(competitor);
        performance.setTimeslot(timeslot);
        performanceRepository.save(performance);
        return "redirect:/performances";
    }

    @GetMapping("/performances/delete")
    public String delete(){
        return "performances-delete";
    }

    @PostMapping("/performances/delete")
    public String doDelete(@RequestParam Long id, Model model){
        Performance performance = performanceRepository.findById(id).orElseThrow();
        performanceRepository.delete(performance);
        return "redirect:/performances";
    }
}
