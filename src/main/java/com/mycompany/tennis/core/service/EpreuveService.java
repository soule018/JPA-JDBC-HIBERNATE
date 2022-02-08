package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    /*
    lorsqu'un servce intéragit avec un seul repository,une bonne pratique sera de
     créer une propriété pour stocker le repository ou le DAO
     qu'on utilise
     */
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public Epreuve getEpreuve (Long id) {
        Session session=null;
        Transaction tx=null;
        Epreuve epreuve=null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            epreuve=epreuveRepository.getById(id);
            System.out.println("La classe de la propriété tournoi est "+epreuve.getTournoi().getClass().getName());
            System.out.println("L'dentifiant du tournoi est "+epreuve.getTournoi().getId()); // retourne la valeur de id_tournoi dans la table EPREUVE et non de la valeur id de la table TOURNOI
            System.out.println("L'épreuve sélectionnée se déroule en "+epreuve.getAnnee()+" et il s'agit du tournoi "+epreuve.getTournoi().getNom());
            tx.commit();
        }
        catch (Exception e){
            if(tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }

        }

        return epreuve;
    }

}
