package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;



public class TestDeConnection {
    public static void main(String... args){
        JoueurService joueurService=new JoueurService();

        Joueur noah=joueurService.getById(72L);
        System.out.println("IL s'agit du joueur "+noah.getPrenom()+" "+noah.getNom());






    }
}