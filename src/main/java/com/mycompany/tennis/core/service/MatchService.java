package com.mycompany.tennis.core.service;


import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
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

            EpreuveFullDto epreuveDto=new EpreuveFullDto();
            epreuveDto.setId(match.getEpreuve().getId());
            epreuveDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            epreuveDto.setTournoi(tournoiDto);

            dto.setEpreuve(epreuveDto);

            ScoreFullDto scoreDto=new ScoreFullDto();
            scoreDto.setId(match.getScore().getId());
            scoreDto.setSet1(match.getScore().getSet1());
            scoreDto.setSet2(match.getScore().getSet2());
            scoreDto.setSet3(match.getScore().getSet3());
            scoreDto.setSet4(match.getScore().getSet4());
            scoreDto.setSet5(match.getScore().getSet5());
            dto.setScore(scoreDto);
            scoreDto.setMatch(dto); // la relation est bidirectionnelle entre score et match
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
