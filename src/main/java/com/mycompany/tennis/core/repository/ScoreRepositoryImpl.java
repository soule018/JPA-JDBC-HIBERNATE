package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;


public class ScoreRepositoryImpl {

    public void create (Score score){

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

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */

            preparedStatement.setLong(1,score.getMatch().getId());
            preparedStatement.setByte(2,score.getSet1());
            preparedStatement.setByte(3,score.getSet2());
            if(score.getSet3()==null){
                preparedStatement.setNull(4,Types.TINYINT);
            }else{
            preparedStatement.setByte(4,score.getSet3());}

            if (score.getSet4()==null){
                preparedStatement.setNull(5,Types.TINYINT);
            }else{
            preparedStatement.setByte(5,score.getSet4());}

            if(score.getSet5()==null){
                preparedStatement.setInt(6,Types.TINYINT);
            }else {
            preparedStatement.setByte(6,score.getSet5());}


            preparedStatement.executeUpdate();

            /*
            Cette méthode fournit un ResultSet;
            Il s'agit de toutes les valeurs autogénérées après insertion de l'enregistrement
             */
             ResultSet rs=preparedStatement.getGeneratedKeys();
             //on insert une seule ligne d'où l'utilisation du if
             if(rs.next()){
                 rs.getLong(1);
                 score.setId(rs.getLong(1));
             }



            System.out.println("score créé");
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

