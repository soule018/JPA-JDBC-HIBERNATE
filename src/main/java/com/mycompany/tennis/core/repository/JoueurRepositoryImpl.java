package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create (Joueur joueur){

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

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */

            preparedStatement.setString(1,joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3,joueur.getSexe().toString());

            preparedStatement.executeUpdate();

            /*
            Cette méthode fournit un ResultSet;
            Il s'agit de toutes les valeurs autogénérées après insertion de l'enregistrement
             */
             ResultSet rs=preparedStatement.getGeneratedKeys();
             //on insert une seule ligne d'où l'utilisation du if
             if(rs.next()){
                 rs.getLong(1);
                 joueur.setId(rs.getLong(1));
             }



            System.out.println("Joueur créé");
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


    public void update (Joueur joueur) {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?,PRENOM=?,SEXE=? WHERE ID=?");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4,joueur.getId());

            preparedStatement.executeUpdate();


            System.out.println("Joueur modifié");
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

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();


            System.out.println("Joueur supprimé");
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

    public Joueur getById (Long id) {

        Connection conn = null;
        Joueur joueur=null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM,PRENOM,SEXE FROM JOUEUR WHERE ID=?");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */
            preparedStatement.setLong(1,id);

            ResultSet rs =preparedStatement.executeQuery();

            if(rs.next()){
                joueur=new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }


            System.out.println("Joueur lu");
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
        return joueur;
    }

    public List<Joueur> list () {

        Connection conn = null;
        List<Joueur> joueurs=new ArrayList<>();
        try {

          DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID,NOM,PRENOM,SEXE FROM JOUEUR");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */


            ResultSet rs =preparedStatement.executeQuery();

            while(rs.next()){
                Joueur joueur=new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurs.add(joueur);
            }


            System.out.println("joueurs lus");
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
        return joueurs;
    }


}

