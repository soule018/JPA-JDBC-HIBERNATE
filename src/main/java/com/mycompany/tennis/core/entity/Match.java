package com.mycompany.tennis.core.entity;

public class Match {

    private Long id;
    private Joueur vainqueur;
    private Joueur finaliste;
    private Epreuve epreuve;
    /*
    Comment faire le lien entre match et Score ?
    Est ce que je crée une variable de type match dans la classe score ?
    ou est ce que je crée une variable de type score dans la classe match ?
    PLus concrètement,est ce qu'en connaissant le match, je dois pouvoir connaître le score ?
    ou est ce qu'en connaissant le score, je dois pouvoir connaître le match ?
    je peux même m'autoriser les deux relations si je le souhaite (appelée relation bi-directionnelle)
     */
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
