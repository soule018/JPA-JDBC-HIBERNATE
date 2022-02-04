package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

import java.util.List;

public class TestDeConnection {
    public static void main(String... args){
        JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
        List<Joueur>listJoueur =joueurRepository.list();

        for(Joueur joueur : listJoueur){
            System.out.println("Joueur numéro : "+joueur.getId()+" ; nom : "+joueur.getNom()+" ; prénom : "+joueur.getPrenom()+" ; sexe : "+joueur.getSexe());
        }





    }
}