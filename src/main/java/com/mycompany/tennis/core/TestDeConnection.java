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
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM,PRENOM,ID FROM JOUEUR WHERE ID=?");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */
            long identifiant=39L;
            preparedStatement.setLong(1,identifiant);


            ResultSet rs=preparedStatement.executeQuery();

            /*
            On veut récupérer la colonne nom, prenom, d'où l'utilisation du while
             */

            if(rs.next()){
                final String nom=rs.getString("NOM");
                final String prenom=rs.getString("PRENOM");
                final Long id=rs.getLong("ID");
                System.out.println("Le joueur / la joueuse représenté(e) par le numéro "+id+" est "+prenom+" "+nom);
            }
            else {
                System.out.println("Il n'y a pas d'enregistrement d'ID 39");
            }


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

