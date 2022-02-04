package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import java.util.List;

public class TestDeConnection {
    public static void main(String... args){
        TournoiRepositoryImpl tournoiRepository=new TournoiRepositoryImpl();

        List<Tournoi>listTournoi=tournoiRepository.list();

        for(Tournoi list : listTournoi){
            System.out.println(list.getNom()+" "+list.getCode());
        }








    }
}

