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

             */
            Statement statement = conn.createStatement();
            ResultSet rs=statement.executeQuery("SELECT NOM,PRENOM,ID FROM JOUEUR WHERE ID=12 ");

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
                System.out.println("Il n'y a pas d'enregositrement d'ID 12");
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

