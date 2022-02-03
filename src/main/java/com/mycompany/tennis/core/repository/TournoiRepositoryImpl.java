package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create (Tournoi tournoi){

        Connection conn = null;
        try {
            /*
            Instanciation de la classe BasicDataSource;
            Avec l'utilisation de dépendance Commons DBCP, on ne vas plus
            utiliser la classe MySQL DataSource, on va utiliser une DataSource
            générique, c'est la classe BasicDataSource de Commons DBCP
             */
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn=dataSource.getConnection();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO TOURNOI (NOM,CODE) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */

            preparedStatement.setString(1,tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());

            preparedStatement.executeUpdate();

            /*
            Cette méthode fournit un ResultSet;
            Il s'agit de toutes les valeurs autogénérées après insertion de l'enregistrement
             */
            ResultSet rs=preparedStatement.getGeneratedKeys();
            //on insert une seule ligne d'où l'utilisation du if
            if(rs.next()){
                rs.getLong(1);
                tournoi.setId(rs.getLong(1));
            }



            System.out.println("Tournoi créé");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if(conn!=null) conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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

        Connection conn = null;
        Tournoi tournoi =null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM,CODE FROM TOURNOI WHERE ID=?");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */
            preparedStatement.setLong(1,id);

            ResultSet rs =preparedStatement.executeQuery();

            if(rs.next()){
                tournoi=new Tournoi();
                tournoi.setId(id);
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));

            }


            System.out.println("Tournoi lu");
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
