package com.mycompany.tennis.core.service;


import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    /*
     Ce service va dépendre de deux repository, donc on va créer deux propriété de type ScoreRepositoryImpl
     et une propriété de type MatchRepositortImpl
      */
    private MatchRepositoryImpl matchRepository;


    public MatchService() {
        this.matchRepository=new MatchRepositoryImpl();

    }



    public MatchDto getMatch (Long id) {
        Session session=null;
        Transaction tx=null;
        Match match=null;
        MatchDto dto=null;
        try {
            /*
            getCurrentSession() va permettre de réutiliser une session qui sera
            stocker quelque part ici en l'occurence dans le ThreadLocal
             */
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            match=matchRepository.getById(id);
            dto=new MatchDto();
            JoueurDto finalisteDto=new JoueurDto();
            finalisteDto.setNom(match.getFinaliste().getNom());
            finalisteDto.setPrenom(match.getFinaliste().getPrenom());
            finalisteDto.setId(match.getFinaliste().getId());
            finalisteDto.setSexe(match.getFinaliste().getSexe());
            dto.setId(match.getId());
            dto.setFinaliste(finalisteDto);
            JoueurDto vainqueurDto=new JoueurDto();
            vainqueurDto.setId(match.getVainqueur().getId());
            vainqueurDto.setNom(match.getVainqueur().getNom());
            vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
            vainqueurDto.setSexe(match.getVainqueur().getSexe());
            dto.setVainqueur(vainqueurDto);
            tx.commit();
        }
        catch (Exception e){
            if(tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session!=null){
                session.close();
            }

        }

        return dto;
    }
}
