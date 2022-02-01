package com.mycompany.tennis.core;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TENNIS?useSSL=false","root","my-secret-pw");

            /*
            Toutes les modifications que nous allons faire vont être mise
            en attente de validation/de commit et c'est nous qui allons
            réaliser la validation en écrivant conn.commit() si tout est bon;
            Si tout ne se passe pas bien, on aura une execption, qui va nous
            emmener dans le bloc catch(SQLException e), donc on va demander
            d'annuler tout en écrivant coll.rollback(); qui veut dire revient en arrière
            Le rollback peut lui-même rejeter une exception, il faudrait encadrer cette instruction d'un try catch également
             */
            conn.setAutoCommit(false);
            /*
            Etape 1 : création d'un objet de type statement
            on passe la requête immédiatement dans la création du prepareStatement
            car il a l'avantage de précompiler la requête
             */

            /*
            on veut modifier les paramètres d'un joueur via UPDATE
             */

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES (?,?,?)");
            /*
            les méthodes set de preparedStatement prennent en 1er paramètre
            l'index du ? dans la requête sql,
            ici, on a un seul paramètre,
            le 2ème paramètre est la valeur qu'on veut donner à ce ?
             */

            String nom="Capriati";
            String prenom="Jennifer";
            String sexe="F";
            preparedStatement.setString(1,nom);
            preparedStatement.setString(2,prenom);
            preparedStatement.setString(3,sexe);

            preparedStatement.executeUpdate();

             nom="Johannson";
             prenom="Thomas";
             sexe="H";
            preparedStatement.setString(1,nom);
            preparedStatement.setString(2,prenom);
            preparedStatement.setString(3,sexe);

            preparedStatement.executeUpdate();

            /*
            C'est à l'issu seulement de la 2ème éxécution, si tout
            s'est bien passé, que je vais tenter de faire la validation
            à savoir conn.commit()
            Si la 1ère instruction se passe mal,j'irai dans le bloc
            try catch;
            Si la 1ère a fonctionné et que la 2ème échoue, je vais tomber
            dans le bloc SQLException e qui devrait annuler la 1ère instruction également
             */
            conn.commit();


            System.out.println("success");
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

