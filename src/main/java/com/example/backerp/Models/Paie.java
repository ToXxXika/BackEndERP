package com.example.backerp.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Paie {
    private int idpaie;

    private Integer montanttotal;
    private String methodepaiement;
    private Conventions conventionsByRefconv;
    private String refconv;

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

    @ManyToOne
    @JoinColumn(name = "refconv", referencedColumnName = "referenceconv",updatable = false,insertable = false)
    public Conventions getConventionsByRefconv() {
        return conventionsByRefconv;
    }

    public void setConventionsByRefconv(Conventions conventionsByRefconv) {
        this.conventionsByRefconv = conventionsByRefconv;
    }

    @Basic
    @Column(name = "refconv")
    public String getRefconv() {
        return refconv;
    }

    public void setRefconv(String refconv) {
        this.refconv = refconv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paie paie = (Paie) o;
        return Objects.equals(refconv, paie.refconv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refconv);
    }
}
