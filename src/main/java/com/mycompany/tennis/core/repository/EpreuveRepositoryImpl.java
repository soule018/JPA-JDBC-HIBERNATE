package com.mycompany.tennis.core.repository;


import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveRepositoryImpl {


    public Epreuve getById (Long id) {
        Epreuve epreuve=null;
        Session session = null;
        session= HibernateUtil.getSessionFactory().getCurrentSession();
        epreuve=session.get(Epreuve.class,id);

        System.out.println("Epreuve lue");
        return epreuve;
    }

    public List<Epreuve> list (String codeTournoi) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        //join fetch fonctionne avec tous type d'association 
        Query<Epreuve> query = session.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?0", Epreuve.class);
        query.setParameter(0,codeTournoi);
        // A partir de ce query, on va pouvoir retourner le résultat de l'exécution de la query via un query.getResultList
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Epreuve lus");
        return epreuves;

    }




}

