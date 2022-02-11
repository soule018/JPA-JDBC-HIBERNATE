package com.mycompany.tennis.core.repository;


import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Score;
import org.hibernate.Session;




public class ScoreRepositoryImpl {

    public void create (Score score) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(score);
        System.out.println("Score ajouté");
    }

    public Score getById (Long id) {
        Score score=null;
        Session session = null;
        session= HibernateUtil.getSessionFactory().getCurrentSession();
        score=session.get(Score.class,id);

        System.out.println("Score lu");
        return score;
    }





}

