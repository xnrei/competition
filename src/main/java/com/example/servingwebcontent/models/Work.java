package com.example.servingwebcontent.models;

import javax.persistence.*;

@Entity
public class Work{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Competitor competitor;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Genre getGenre(){
        return genre;
    }

    public void setGenre(Genre genre){
        this.genre = genre;
    }

    public Competitor getCompetitor(){
        return competitor;
    }

    public void setCompetitor(Competitor competitor){
        this.competitor = competitor;
    }
}
