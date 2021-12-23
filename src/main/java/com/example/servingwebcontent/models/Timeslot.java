package com.example.servingwebcontent.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Timeslot{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String time;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }
}
