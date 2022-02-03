package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.List;

public class TestDeConnection {
    public static void main(String... args){
        TournoiRepositoryImpl tournoiRepository=new TournoiRepositoryImpl();

        Tournoi tournoi =new Tournoi();
        tournoi.setNom("Paris-Bercy");
        tournoi.setCode("PB");
        tournoiRepository.create(tournoi);

        Tournoi paris = tournoiRepository.getById(17L);
        System.out.println(paris.getNom()+" "+paris.getCode());

       /*
        List<Joueur>listJoueur =joueurRepository.list();
        Joueur noah=new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannik");
        noah.setSexe('H');
        joueurRepository.create(noah);

        System.out.println("L'identifiant du joueur crée est "+noah.getId());


        for(Joueur joueur : listJoueur){
            System.out.println(joueur.getPrenom()+" "+joueur.getNom());
        }*/





    }
}

