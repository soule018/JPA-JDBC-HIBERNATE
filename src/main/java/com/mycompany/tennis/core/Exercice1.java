package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.List;

public class Exercice1 {
    public static void main(String... args){
        TournoiService tournoiService=new TournoiService();
        Tournoi bercy=tournoiService.getTournoi(20L);
        System.out.println("IL s'agit du tournoi n° "+bercy.getId()+" s'appelant "+bercy.getNom()+" ayant comme code "+bercy.getCode());











    }
}


