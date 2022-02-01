package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
        Joueur bartoli = joueurRepository.getById(21L);
        System.out.println(bartoli.getPrenom()+" "+bartoli.getNom());

        Joueur noah =joueurRepository.getById(70L);
        System.out.println(noah.getPrenom()+" "+noah.getNom());
        noah.setPrenom("Yannick");
        joueurRepository.update(noah);
        System.out.println(noah.getPrenom()+" "+noah.getNom());




    }
}

