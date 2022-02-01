package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.List;

public class TestDeConnection {
    public static void main(String... args){
        JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
        List<Joueur>listJoueur =joueurRepository.list();

        for(Joueur joueur : listJoueur){
            System.out.println(joueur.getPrenom()+" "+joueur.getNom());
        }





    }
}

