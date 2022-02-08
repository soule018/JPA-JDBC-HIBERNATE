package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create (Tournoi tournoi){

        Session session = null;
        Transaction tx=null;

        try {
            // opensession va nous permettre de recupérer une session fraîche
            session= HibernateUtil.getSessionFactory().openSession();
            tx= session.beginTransaction();

             /*
        Comment demander à Hibernate de créer cet enregistrement ?
        A l'intérieur de la méthode persist(), on inscrit l'objet qu'on veut créer,
        ici joueur;
        La méthode persist() signifie que l'objet passé en paramètre doit être ajouté
        en session hibernate. Le fait de l'ajouer fait que son état est désormais persistent,
        tout simplement, on dit qu'il est passé de l'état Transient(càd inconu de la session)
        à l'état persistent(càd dont la gestion de la persistance est confiée à hibernate.
        Mais l'action de générer la requête en insert vers la base de donnée ne va pas se faire automatiquement
        à chaque fois qu'on ajoute un objet en session.Il y a plusieurs mécanismes possibles
        mais de base, on va pouvoir déclencher manuellement l'insertion grâce à un session.fluch
                 */
            session.persist(tournoi);
            /*
             il s'agit de synchroniser l'état de la session avec la base de donnée, il invoque fluch de façon indirecte;
             lorsqu'on fait un commit, il faut penser au rollback lorsque tous ne se passe pas bien
             */
            tx.commit();
            System.out.println("Tournoi créé");
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


    public void update (Tournoi tournoi) {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE TOURNOI SET NOM=?,CODE=? WHERE ID=?");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */

            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());

            preparedStatement.setLong(3,tournoi.getId());

            preparedStatement.executeUpdate();


            System.out.println("Tournoi modifié");
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
    }

    public void delete (Long id) {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM TOURNOI WHERE ID=?");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();


            System.out.println("Tournoi supprimé");
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
    }

    /*
    retourne un tournoi en fonction de son identifiant
     */
    public Tournoi getById (Long id) {

        Tournoi tournoi =null;
        Session session = null;
        try {
            // opensession va nous permettre de recupérer une session fraîche
            session= HibernateUtil.getSessionFactory().openSession();

             /*
             C'est à partir de l'objet session qu'on va pouvoir faire du create,update,delete,read
             Pour récupérer une ligne particulière dans la base de donnée et en faire
             un objet,on utlise la méthode get de session,
             Il prendra en argument d'abord le type d'objet qu'on veut fabriquer et en deuxième argument
             l'identifiant unique de l'objet en particulier qu'on veut créer, sous-entendu de la ligne
             qu'on veut recupérer à partir de la table associé à cette table joueur;
             On aura pas à écrire la requête sql qui lui va bien, on aura pas à récupérer du resulSet,
             On aura pas à instancier nous-même la classe joueur et à associer à toutes ses propriétés les valeurs
             issues des colonnes des tables, non, hibernate fait cela pour nous
              */
            tournoi=session.get(Tournoi.class,id);
            System.out.println("Tournoi lu");
        }
        catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }

        }
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
