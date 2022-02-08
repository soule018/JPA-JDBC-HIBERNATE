package com.mycompany.tennis.core.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="EPREUVE")
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Type(type="short")  //permet d'indiquer le type de la variable dans la base de donnée
    private Short annee;

    /*
   Plusieurs épreuves peuvent être associées à un seul tournoi,
   donc on a une relation plusieurs à un : many to one;
   En effet, dans notre base de donnée pour chaque tournoi, nous avions deux epreuves par an :
   une epreuve homme et une epreuve femme.
   La valeur par défaut de ManyToOne est FetchType.EAGER
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_TOURNOI") // car Hibernate trouve comme clé étrangère TOURNOI_ID
    private Tournoi tournoi;

    @Column(name ="TYPE_EPREUVE")
    private Character typeEpreuve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }
}
