package com.mycompany.tennis.core.repository;


import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import org.hibernate.Session;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EpreuveRepositoryImpl {


    public Epreuve getById (Long id) {
        Epreuve epreuve=null;
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager em= EntityManagerHolder.getCurrentEntityManager();
        epreuve=em.find(Epreuve.class,id);

        System.out.println("Epreuve lue");
        return epreuve;
    }

    public List<Epreuve> list (String codeTournoi) {

        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager em= EntityManagerHolder.getCurrentEntityManager();

        //join fetch fonctionne avec tous type d'association
        //Query<Epreuve> query = session.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?0", Epreuve.class);
        TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?0", Epreuve.class);
        query.setParameter(0,codeTournoi);
        // A partir de ce query, on va pouvoir retourner le résultat de l'exécution de la query via un query.getResultList
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Epreuve lus");
        return epreuves;

    }




}

