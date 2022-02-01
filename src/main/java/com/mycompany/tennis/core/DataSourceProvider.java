package com.mycompany.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static BasicDataSource singleDataSource;


    /*
     A chaque fois qu'on a besoin d'un dataSource, on passe par cette méthode statique;
      La 1ère fois, cette dataSource n'existe pas, on va la créer
     */
    public static DataSource getSingleDataSourceInstance(){

        if (singleDataSource==null){

            singleDataSource = new BasicDataSource();

            singleDataSource.setInitialSize(5);

            //Pour obtenir une connexion de cette dataSource
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/TENNIS?useSSL=false");
            singleDataSource.setUsername("root");
            singleDataSource.setPassword("my-secret-pw");

        }
        return singleDataSource;
    }


}
