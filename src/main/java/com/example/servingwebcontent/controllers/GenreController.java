package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.models.Genre;
import com.example.servingwebcontent.repos.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GenreController{

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genres")
    public String competitors(Model model){
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "genres";
    }

    @PostMapping("/genres")
    public String act(@RequestParam String name, Model model){
        Genre genre = new Genre();
        genre.setName(name);
        genreRepository.save(genre);
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "genres";
    }

    @GetMapping("/genres/edit")
    public String edit(){
        return "genres-edit";
    }

    @PostMapping("/genres/edit")
    public String doEdit(@RequestParam Long id, @RequestParam String name, Model model){
        Genre genre = genreRepository.findById(id).orElseThrow();
        genre.setName(name);
        genreRepository.save(genre);
        return "redirect:/genres";
    }

    @GetMapping("/genres/delete")
    public String delete(){
        return "genres-delete";
    }

    @PostMapping("/genres/delete")
    public String doDelete(@RequestParam Long id, Model model){
        Genre genre = genreRepository.findById(id).orElseThrow();
        genreRepository.delete(genre);
        return "redirect:/genres";
    }
}
