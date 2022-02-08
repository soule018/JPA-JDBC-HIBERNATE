package com.mycompany.tennis.core.entity;

import javax.persistence.*;

@Entity
@Table(name="MATCH_TENNIS")
public class Match {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_VAINQUEUR")
    private Joueur vainqueur;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_FINALISTE")
    private Joueur finaliste;
    @Transient
    private Epreuve epreuve;
    /*
    Comment faire le lien entre match et Score ?
    Est ce que je crée une variable de type match dans la classe score ?
    ou est ce que je crée une variable de type score dans la classe match ?
    PLus concrètement,est ce qu'en connaissant le match, je dois pouvoir connaître le score ?
    ou est ce qu'en connaissant le score, je dois pouvoir connaître le match ?
    je peux même m'autoriser les deux relations si je le souhaite (appelée relation bi-directionnelle)
     */
    @Transient
    private Score score;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        this.vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(Joueur finaliste) {
        this.finaliste = finaliste;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }
}
