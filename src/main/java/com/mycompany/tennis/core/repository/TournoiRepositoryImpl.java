package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create (Tournoi tournoi){
            //Session session= HibernateUtil.getSessionFactory().getCurrentSession();
            EntityManager em= EntityManagerHolder.getCurrentEntityManager();
            em.persist(tournoi);
            System.out.println("Tournoi créé");
        }


    public void delete (Long id) {

        Tournoi tournoi=getById(id);
        //Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager em= EntityManagerHolder.getCurrentEntityManager();
        em.remove(tournoi);
        System.out.println("Tournoi supprimé");
    }

    /*
    retourne un tournoi en fonction de son identifiant
     */
    public Tournoi getById (Long id) {
        EntityManager em= EntityManagerHolder.getCurrentEntityManager();
        Tournoi tournoi=null;
        //Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        tournoi=em.find(Tournoi.class,id);

        System.out.println("Tournoi lu");
        return tournoi;
    }

    public List<Tournoi> list () {

        Connection conn = null;
        List<Tournoi> tournois=new ArrayList<>();
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID,NOM,CODE FROM TOURNOI");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */


            ResultSet rs =preparedStatement.executeQuery();

            while(rs.next()){
                Tournoi tournoi=new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);
            }


            System.out.println("tournoi lus");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tournois;
    }
}
