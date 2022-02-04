package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.dao.MatchDaoImpl;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {

    /*
    Ce service va dépendre d'un dao, donc on va créer une propriété de type MatchDaoImpl
     */
    //private ScoreRepositoryImpl scoreRepository;
    //private MatchRepositoryImpl matchRepository;
    private MatchDaoImpl daoImpl;

    public MatchService() {
        //this.scoreRepository=new ScoreRepositoryImpl();
        //this.matchRepository=new MatchRepositoryImpl();
        this.daoImpl=new MatchDaoImpl();
    }

    public void enregisterNouveauMatch (Match match){
        daoImpl.createMatchWithScore(match);
     //matchRepository.create(match);
     //scoreRepository.create(match.getScore());
    }
}
