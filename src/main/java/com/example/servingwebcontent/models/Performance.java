package com.example.servingwebcontent.models;

import javax.persistence.*;

@Entity
public class Performance{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Competitor competitor;

    @OneToOne
    private Timeslot timeslot;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Competitor getCompetitor(){
        return competitor;
    }

    public void setCompetitor(Competitor competitor){
        this.competitor = competitor;
    }

    public Timeslot getTimeslot(){
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot){
        this.timeslot = timeslot;
    }
}
