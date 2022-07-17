package com.example.backerp.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Detailevenement {
    private Integer prix;
    private Integer places;
    private int idetail;
    private String localisation;
    private Integer promotion;
    private Evenement evenementsByIdetail;

    @Basic
    @Column(name = "prix")
    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    @Basic
    @Column(name = "places")
    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idetail")
    public int getIdetail() {
        return idetail;
    }

    public void setIdetail(int idetail) {
        this.idetail = idetail;
    }

    @Basic
    @Column(name = "localisation")
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Basic
    @Column(name = "promotion")
    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detailevenement that = (Detailevenement) o;
        return idetail == that.idetail && Objects.equals(prix, that.prix) && Objects.equals(places, that.places) && Objects.equals(localisation, that.localisation) && Objects.equals(promotion, that.promotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prix, places, idetail, localisation, promotion);
    }

    @OneToOne(mappedBy = "detailevenementByDetails")
    public Evenement getEvenementsByIdetail() {
        return evenementsByIdetail;
    }

    public void setEvenementsByIdetail(Evenement evenementsByIdetail) {
        this.evenementsByIdetail = evenementsByIdetail;
    }
}
