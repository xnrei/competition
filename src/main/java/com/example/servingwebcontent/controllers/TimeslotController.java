package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.models.Genre;
import com.example.servingwebcontent.models.Timeslot;
import com.example.servingwebcontent.repos.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TimeslotController{

    @Autowired
    private TimeslotRepository timeslotRepository;

    @GetMapping("/timeslots")
    public String competitors(Model model){
        Iterable<Timeslot> timeslots = timeslotRepository.findAll();
        model.addAttribute("timeslots", timeslots);
        return "timeslots";
    }

    @PostMapping("/timeslots")
    public String act(@RequestParam String hours, @RequestParam String minutes, @RequestParam String half, Model model){
        Timeslot timeslot = new Timeslot();
        String time = hours + ":" + minutes + " " + half;
        timeslot.setTime(time);
        timeslotRepository.save(timeslot);
        Iterable<Timeslot> timeslots = timeslotRepository.findAll();
        model.addAttribute("timeslots", timeslots);
        return "timeslots";
    }

    @GetMapping("/timeslots/edit")
    public String edit(){
        return "timeslots-edit";
    }

    @PostMapping("/timeslots/edit")
    public String doEdit(@RequestParam Long id, @RequestParam String hours, @RequestParam String minutes, @RequestParam String half, Model model){
        Timeslot genre = timeslotRepository.findById(id).orElseThrow();
        String time = hours + ":" + minutes + " " + half;
        genre.setTime(time);
        timeslotRepository.save(genre);
        return "redirect:/timeslots";
    }

    @GetMapping("/timeslots/delete")
    public String delete(){
        return "timeslots-delete";
    }

    @PostMapping("/timeslots/delete")
    public String doDelete(@RequestParam Long id, Model model){
        Timeslot timeslot = timeslotRepository.findById(id).orElseThrow();
        timeslotRepository.delete(timeslot);
        return "redirect:/timeslots";
    }
}
