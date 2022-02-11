package com.mycompany.tennis.core.repository;


import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Match;
import org.hibernate.Session;




public class MatchRepositoryImpl {

    public void create (Match match){

        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(match);
        System.out.println("Match ajouté");
    }

    public Match getById (Long id) {
        Match match=null;
        Session session = null;
        session= HibernateUtil.getSessionFactory().getCurrentSession();
        match=session.get(Match.class,id);

        System.out.println("Match lu");
        return match;
    }



    }




