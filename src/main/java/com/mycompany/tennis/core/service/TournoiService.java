package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;


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
        Session session=null;
        Transaction tx=null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            tournoiRepository.create(tournoi);
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
    }

    public Tournoi getTournoi (Long id) {
        Session session=null;
        Transaction tx=null;
        Tournoi tournoi=null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            tournoi=tournoiRepository.getById(id);
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

        return tournoi;
    }

    /*
   Cette méthode permet de renommer un tournoi dejà existant;
   Pour ce faire, on recupère d'abord le joueur qu'on veut modifier via son id,
   on utilise donc la méthode getById(), puis on modifie le nom du joueur via un setNom;
    */
    public void update(Long id, String nouveauNom){
        Tournoi tournoi=getTournoi(id); // joueur qui sert de modèle
        Session session=null;
        Transaction tx=null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            tournoi.setNom(nouveauNom);
            /*
            La méthode merge() va d'abord procéder à un select des informations qui se trouvent en base de donnée relative au joueur ayant le même id,
            l'id. Cela va conduire à la création d'un nouvel objet qui sera mis en session, qui est persistant,
            et enfin la méthode merge va modifier (update) toutes les propriétés de l'objet persistant en utilisant les propriété de l'objet
            non persistant, ce qui va conduire à la fin à un update,
            On a donc deux objets joueur à ce stade avec le même id, celui qui a servi de modèle et qui est détaché et celui qui est persistant
             */
            Tournoi TournoiPersistant=(Tournoi) session.merge(tournoi); // joueur persistant
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
    }
   /*
    Cette méthode permet de renommer le code d'un tournoi dejà existant;
    Pour ce faire, on recupère d'abord le joueur qu'on veut modifier via son id,
    on utilise donc la méthode getById(), puis on modifie le sexe du joueur via un setSexe;
    */
    public void updateByCode(Long id, String code) {
        Tournoi tournoi = getTournoi(id); // joueur qui sert de modèle
        Session session = null;
        Transaction tx = null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoi.setCode(code);
            Tournoi tournoiPersistant = (Tournoi) session.merge(tournoi); // joueur persistant
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }

    public  void deleteTournoi (Long id){
        Session session = null;
        Transaction tx = null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoiRepository.delete(id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }
}
