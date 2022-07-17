package com.example.backerp.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Paie {
    private int idpaie;
    private Integer refconv;
    private Integer montanttotal;
    private String methodepaiement;
    private Conventions conventionsByRefconv;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idpaie")
    public int getIdpaie() {
        return idpaie;
    }

    public void setIdpaie(int idpaie) {
        this.idpaie = idpaie;
    }

    @Basic
    @Column(name = "refconv")
    public Integer getRefconv() {
        return refconv;
    }

    public void setRefconv(Integer refconv) {
        this.refconv = refconv;
    }

    @Basic
    @Column(name = "montanttotal")
    public Integer getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(Integer montanttotal) {
        this.montanttotal = montanttotal;
    }

    @Basic
    @Column(name = "methodepaiement")
    public String getMethodepaiement() {
        return methodepaiement;
    }

    public void setMethodepaiement(String methodepaiement) {
        this.methodepaiement = methodepaiement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paie paie = (Paie) o;
        return idpaie == paie.idpaie && Objects.equals(refconv, paie.refconv) && Objects.equals(montanttotal, paie.montanttotal) && Objects.equals(methodepaiement, paie.methodepaiement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpaie, refconv, montanttotal, methodepaiement);
    }

    @OneToOne
    @JoinColumn(name = "refconv", referencedColumnName = "id")
    public Conventions getConventionsByRefconv() {
        return conventionsByRefconv;
    }

    public void setConventionsByRefconv(Conventions conventionsByRefconv) {
        this.conventionsByRefconv = conventionsByRefconv;
    }
}
