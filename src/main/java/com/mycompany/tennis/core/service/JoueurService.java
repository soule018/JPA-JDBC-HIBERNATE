package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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
        Session session=null;
        Transaction tx=null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueurRepository.create(joueur);
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

    public Joueur getJoueur (Long id) {
        Session session=null;
        Transaction tx=null;
        Joueur joueur=null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueur=joueurRepository.getById(id);
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

        return joueur;
    }

    /*
   Cette méthode permet de renommer un joueur dejà existant;
   Pour ce faire, on recupère d'abord le joueur qu'on veut modifier via son id,
   on utilise donc la méthode getById(), puis on modifie le nom du joueur via un setNom;
    */
    public void renomme(Long id, String nouveauNom){
        Joueur joueur=getJoueur(id); // joueur qui sert de modèle
        Session session=null;
        Transaction tx=null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueur.setNom(nouveauNom);
            /*
            La méthode merge() va d'abord procéder à un select des informations qui se trouvent en base de donnée relative au joueur ayant le même id,
            l'id. Cela va conduire à la création d'un nouvel objet qui sera mis en session, qui est persistant,
            et enfin la méthode merge va modifier (update) toutes les propriétés de l'objet persistant en utilisant les propriété de l'objet
            non persistant, ce qui va conduire à la fin à un update,
            On a donc deux objets joueur à ce stade avec le même id, celui qui a servi de modèle et qui est détaché et celui qui est persistant
             */
            Joueur joueurPersistant=(Joueur) session.merge(joueur); // joueur persistant
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
   Cette méthode permet de renommer le sexe d'un joueur dejà existant;
   Pour ce faire, on recupère d'abord le joueur qu'on veut modifier via son id,
   on utilise donc la méthode getById(), puis on modifie le sexe du joueur via un setSexe;
    */
    public void renomme(Long id, Character sexe) {
        Joueur joueur = getJoueur(id); // joueur qui sert de modèle
        Session session = null;
        Transaction tx = null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur.setSexe(sexe);
            Joueur joueurPersistant = (Joueur) session.merge(joueur); // joueur persistant
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

    public  void deleteJoueur (Long id){
        Session session = null;
        Transaction tx = null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.delete(id);
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

    public List<JoueurDto>getListJoueurs(char sexe){
        Session session = null;
        Transaction tx = null;
        List <JoueurDto> dtos=new ArrayList<>();
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            List<Joueur> joueurs=joueurRepository.list(sexe);
            for(Joueur joueur : joueurs){
                final JoueurDto dto=new JoueurDto();
                dto.setNom(joueur.getNom());
                dto.setPrenom(joueur.getPrenom());
                dto.setId(joueur.getId());
                dto.setSexe(joueur.getSexe());
                dtos.add(dto);
            }
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
return dtos;
    }


}
