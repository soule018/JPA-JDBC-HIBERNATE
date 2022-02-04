package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.MatchService;


public class Cours {
    public static void main(String... args){
        MatchService matchService=new MatchService();
        Joueur vainqueur=new Joueur();
        vainqueur.setId(32L);
        Joueur finaliste=new Joueur();
        finaliste.setId(34L);
        Match match = new Match();
        Score score=new Score();
        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);
        match.setScore(score);
        score.setMatch(match); // on a une relation bidirectionnelle
        Epreuve epreuve=new Epreuve();
        epreuve.setId(10L);



        match.setVainqueur(vainqueur);
        match.setFinaliste(finaliste);
        match.setEpreuve(epreuve);
        matchService.enregisterNouveauMatch(match);

        /*JoueurService joueurService=new JoueurService();

        Joueur noah=joueurService.getById(72L);
        System.out.println("IL s'agit du joueur "+noah.getPrenom()+" "+noah.getNom());*/






    }
}