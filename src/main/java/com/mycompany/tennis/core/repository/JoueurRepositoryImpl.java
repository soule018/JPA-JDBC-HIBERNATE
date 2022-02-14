package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class JoueurRepositoryImpl {


    public void create (Joueur joueur){
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(joueur);
        System.out.println("Joueur créé");
        }


    public void delete (Long id) {
        Joueur joueur=getById(id);
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
        System.out.println("Joueur supprimé");
    }

    public Joueur getById (Long id) {
        Joueur joueur=null;
        Session session = null;
        session= HibernateUtil.getSessionFactory().getCurrentSession();
        joueur=session.get(Joueur.class,id);

        System.out.println("Joueur lu");
        return joueur;
    }

    public List<Joueur> list (char sexe) {

        //Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager em= EntityManagerHolder.getCurrentEntityManager();

        /*
        La méthode createQuery prend deux paramètres,
        le 1èr étant la chaîne de caractère HQL que l'on veut faire exécuter,
        le 2ème paramètre est le type de classe que l'on veut retourner;
        la requête hql a une syntaxe très proche du sql, elle interroge les classes java
        et non les tables de la base de donnée,
        la requête passée en paramètre signifie qu'on veut réaliser une requête qui va permettre de
        "collecter toutes les propriétés de la classe Joueur qui sont décrites côté java",
        on a ajouté un alias à la classe Joueur (ici j),
        Cela va fonctionner car Hibernate connaît grâce aux annotations la classe associé Joueur et les colonnes
        associées aux propriétés de la classe Joueur, donc il va pouvoir facilement convertir cela en requête sql

         */
        TypedQuery<Joueur> query = em.createNamedQuery("given_sexe", Joueur.class);
        query.setParameter(0,sexe);
        // A partir de ce query, on va pouvoir retourner le résultat de l'exécution de la query via un query.getResultList
        List<Joueur> joueurs = query.getResultList();
        System.out.println("joueurs lus");
        return joueurs;

    }


}

