package com.mycompany.tennis.core.dao;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDaoImpl {
    public void createMatchWithScore(Match match) {
        Connection conn = null;
        try {
            /*
            Instanciation de la classe BasicDataSource;
            Avec l'utilisation de dépendance Commons DBCP, on ne vas plus
            utiliser la classe MySQL DataSource, on va utiliser une DataSource
            générique, c'est la classe BasicDataSource de Commons DBCP
             */
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE,ID_VAINQUEUR,ID_FINALISTE) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */

            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getFinaliste().getId());
            preparedStatement.setLong(3, match.getVainqueur().getId());

            preparedStatement.executeUpdate();

            /*
            Cette méthode fournit un ResultSet;
            Il s'agit de toutes les valeurs autogénérées après insertion de l'enregistrement
             */
            ResultSet rs = preparedStatement.getGeneratedKeys();
            //on insert une seule ligne d'où l'utilisation du if
            if (rs.next()) {
                rs.getLong(1);
                match.setId(rs.getLong(1));
            }

            System.out.println("match créé");



            PreparedStatement preparedStatement2= conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */

            preparedStatement2.setLong(1,match.getScore().getMatch().getId());
            preparedStatement2.setByte(2,match.getScore().getSet1());
            preparedStatement2.setByte(3,match.getScore().getSet2());
            if(match.getScore().getSet3()==null){
                preparedStatement2.setNull(4,Types.TINYINT);
            }else{
                preparedStatement2.setByte(4,match.getScore().getSet3());}

            if (match.getScore().getSet4()==null){
                preparedStatement2.setNull(5,Types.TINYINT);
            }else{
                preparedStatement2.setByte(5,match.getScore().getSet4());}

            if(match.getScore().getSet5()==null){
                preparedStatement2.setInt(6,Types.TINYINT);
            }else {
                preparedStatement2.setByte(6,match.getScore().getSet5());}


            preparedStatement2.executeUpdate();

            /*
            Cette méthode fournit un ResultSet;
            Il s'agit de toutes les valeurs autogénérées après insertion de l'enregistrement
             */
            ResultSet rs2=preparedStatement.getGeneratedKeys();
            //on insert une seule ligne d'où l'utilisation du if
            if(rs2.next()){
                rs2.getLong(1);
                match.getScore().setId(rs2.getLong(1));
            }

            System.out.println("score créé");
            conn.commit(); // si tout se passe bien

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

    }

