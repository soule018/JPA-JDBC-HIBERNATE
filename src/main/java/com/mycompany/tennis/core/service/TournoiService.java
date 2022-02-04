package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;


public class TournoiService {

    /*
   lorsqu'un servce intéragit avec un seul repository,une bonne pratique sera de
    créer une propriété pour stocker le repository ou le DAO
    qu'on utilise
    */
    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository=new TournoiRepositoryImpl();
    }

    /*
    lorsqu'on a un service qui ne fait rien de plus que de passer les informations de
    la couche qui est au dessus à la couche qui en dessous (le repository),
    on appelle cela un service passe-plat
     */
    public void createTournoi(Tournoi tournoi){
        tournoiRepository.create(tournoi);

    }

    public Tournoi getTournoi (Long id) {
        Tournoi tournoi=tournoiRepository.getById(id);
        return tournoi;
    }
}
