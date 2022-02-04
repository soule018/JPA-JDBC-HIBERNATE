package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.List;

public class Exercice1 {
    public static void main(String... args){
        TournoiService tournoiService=new TournoiService();
        Tournoi bercy=new Tournoi();
        bercy.setNom("Paris-Bercy");
        bercy.setCode("PB");
        tournoiService.createTournoi(bercy);

        System.out.println("Le nouveau tournoi s'appelle "+bercy.getNom()+" et a pour code "+bercy.getCode());

       










    }
}


