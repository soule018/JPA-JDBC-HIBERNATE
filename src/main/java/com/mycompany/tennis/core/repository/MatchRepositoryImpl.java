package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;


public class MatchRepositoryImpl {

    public void create (Match match){

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

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE,ID_VAINQUEUR,ID_FINALISTE) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */

            preparedStatement.setLong(1,match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getFinaliste().getId());
            preparedStatement.setLong(3,match.getVainqueur().getId());

            preparedStatement.executeUpdate();

            /*
            Cette méthode fournit un ResultSet;
            Il s'agit de toutes les valeurs autogénérées après insertion de l'enregistrement
             */
             ResultSet rs=preparedStatement.getGeneratedKeys();
             //on insert une seule ligne d'où l'utilisation du if
             if(rs.next()){
                 rs.getLong(1);
                 match.setId(rs.getLong(1));
             }



            System.out.println("match créé");
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



    }




