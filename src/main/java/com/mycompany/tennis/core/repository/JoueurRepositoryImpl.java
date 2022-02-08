package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;

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

        Joueur joueur=null;
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
            joueur=session.get(Joueur.class,id);



            System.out.println("Joueur lu");
        }
        catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
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

