package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;



public class TestDeConnection {
    public static void main(String... args){
        JoueurService joueurService=new JoueurService();
        Joueur noah=new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannick");
        noah.setSexe('H');
        joueurService.createJoueur(noah);

        System.out.println("L'identifiant du joueur créé est "+noah.getId());






    }
}