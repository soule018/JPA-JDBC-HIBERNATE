package com.mycompany.tennis.core.repository;


import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveRepositoryImpl {


    public Epreuve getById (Long id) {
        Epreuve epreuve=null;
        Session session = null;
        session= HibernateUtil.getSessionFactory().getCurrentSession();
        epreuve=session.get(Epreuve.class,id);

        System.out.println("Epreuve lue");
        return epreuve;
    }




}

