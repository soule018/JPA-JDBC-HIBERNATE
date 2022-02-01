package com.mycompany.tennis.core;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TENNIS?useSSL=false","root","my-secret-pw");
            /*
            Etape 1 : création d'un objet de type statement
            on passe la requête immédiatement dans la création du prepareStatement
            car il a l'avantage de précompiler la requête
             */

            /*
            on veut modifier les paramètres d'un joueur via UPDATE
             */

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?,PRENOM=? WHERE ID=?");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */
            long identifiant=24L;
            String nom="Errani";
            String prenom="Sara";
            preparedStatement.setString(1,nom);
            preparedStatement.setString(2,prenom);
            preparedStatement.setLong(3,identifiant);


            int nbEnregistrementsModifies=preparedStatement.executeUpdate();
            System.out.println("le nombre d'enregistrement modifié est "+nbEnregistrementsModifies);
            
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
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

