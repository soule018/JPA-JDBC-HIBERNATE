package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import java.util.List;

public class Exercice1 {
    public static void main(String... args){
        TournoiRepositoryImpl tournoiRepository=new TournoiRepositoryImpl();

        List<Tournoi>listTournoi=tournoiRepository.list();

        for(Tournoi list : listTournoi){
            System.out.println("Tournoi numéro : "+list.getId()+" ; nom : "+list.getNom()+" ; code : "+list.getCode());
        }








    }
}


