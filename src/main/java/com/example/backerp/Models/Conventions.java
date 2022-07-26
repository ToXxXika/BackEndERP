package com.example.backerp.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Conventions {

    private String referenceconv;
    private String idassoc;
    private Associations associationsByIdassoc;
    private Collection<Evenement> evenementsByReferenceconv;
    private Collection<Paie> paiesByReferenceconv;

    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @MapsId("reference")
    @JoinColumn(name = "idassoc", referencedColumnName = "reference")
    public Associations getAssociationsByIdassoc() {
        return associationsByIdassoc;
    }

    public void setAssociationsByIdassoc(Associations associationsByIdassoc) {
        this.associationsByIdassoc = associationsByIdassoc;
    }

    public Conventions(String referenceconv, String idassoc) {
        this.referenceconv = referenceconv;
        this.idassoc = idassoc;
    }
    public  Conventions(){

    }

    public Conventions(String referenceconv, String idassoc, Associations associationsByIdassoc) {
        this.referenceconv = referenceconv;
        this.idassoc = idassoc;
        this.associationsByIdassoc = associationsByIdassoc;

    }

    @Id
    @Basic
    @Column(name = "referenceconv")
    public String getReferenceconv() {
        return referenceconv;
    }

    public void setReferenceconv(String referenceconv) {
        this.referenceconv = referenceconv;
    }

    @Basic
    @Column(name = "idassoc")
    public String getIdassoc() {
        return idassoc;
    }

    public void setIdassoc(String idassoc) {
        this.idassoc = idassoc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceconv, idassoc);
    }

    @OneToMany(mappedBy = "conventionsByRefconv")
    public Collection<Evenement> getEvenementsByReferenceconv() {
        return evenementsByReferenceconv;
    }

    public void setEvenementsByReferenceconv(Collection<Evenement> evenementsByReferenceconv) {
        this.evenementsByReferenceconv = evenementsByReferenceconv;
    }

    @OneToMany(mappedBy = "conventionsByRefconv")
    public Collection<Paie> getPaiesByReferenceconv() {
        return paiesByReferenceconv;
    }

    public void setPaiesByReferenceconv(Collection<Paie> paiesByReferenceconv) {
        this.paiesByReferenceconv = paiesByReferenceconv;
    }
}




