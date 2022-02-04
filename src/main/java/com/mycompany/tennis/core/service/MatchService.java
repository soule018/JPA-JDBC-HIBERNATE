package com.mycompany.tennis.core.service;


import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {

    /*
     Ce service va dépendre de deux repository, donc on va créer deux propriété de type ScoreRepositoryImpl
     et une propriété de type MatchRepositortImpl
      */
    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;


    public MatchService() {
        this.scoreRepository=new ScoreRepositoryImpl();
        this.matchRepository=new MatchRepositoryImpl();

    }

    public void enregisterNouveauMatch (Match match){
     matchRepository.create(match);
     scoreRepository.create(match.getScore());
    }
}
