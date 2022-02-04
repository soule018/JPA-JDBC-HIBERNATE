package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {
    /*
    lorsqu'un servce intéragit avec un seul repository,une bonne pratique sera de
     créer une propriété pour stocker le repository ou le DAO
     qu'on utilise
     */
    private JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    /*
    lorsqu'on a un service qui ne fait rien de plus que de passer les informations de
    la couche qui est au dessus à la couche qui en dessous (le repository),
    on appelle cela un service passe-plat
     */
    public void createJoueur(Joueur joueur){
        joueurRepository.create(joueur);

    }
}
