package com.example.backerp.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Logparticipation {
    private int idparticipation;
    private Integer idutilisateur;
    private Integer idevent;
    private Integer montantpayé;
    private Utilisateur utilisateurByIdutilisateur;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idparticipation")
    public int getIdparticipation() {
        return idparticipation;
    }

    public void setIdparticipation(int idparticipation) {
        this.idparticipation = idparticipation;
    }

    @Basic
    @Column(name = "idutilisateur")
    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Basic
    @Column(name = "idevent")
    public Integer getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
    }

    @Basic
    @Column(name = "montantpayé")
    public Integer getMontantpayé() {
        return montantpayé;
    }

    public void setMontantpayé(Integer montantpayé) {
        this.montantpayé = montantpayé;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logparticipation that = (Logparticipation) o;
        return idparticipation == that.idparticipation && Objects.equals(idutilisateur, that.idutilisateur) && Objects.equals(idevent, that.idevent) && Objects.equals(montantpayé, that.montantpayé);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idparticipation, idutilisateur, idevent, montantpayé);
    }

    @ManyToOne
    @JoinColumn(name = "idutilisateur", referencedColumnName = "id")
    public Utilisateur getUtilisateurByIdutilisateur() {
        return utilisateurByIdutilisateur;
    }

    public void setUtilisateurByIdutilisateur(Utilisateur utilisateurByIdutilisateur) {
        this.utilisateurByIdutilisateur = utilisateurByIdutilisateur;
    }
}
