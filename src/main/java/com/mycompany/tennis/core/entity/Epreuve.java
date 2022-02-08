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

    @Transient //permet d'exclure cette propriété du mapping Hibernate
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
