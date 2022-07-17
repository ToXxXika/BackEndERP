package com.example.backerp.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Conventions {
    private int id;
    private String referenceconv;
    private Integer idassoc;
    private Integer prixtotal;
    private Paie paiesById;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public Integer getIdassoc() {
        return idassoc;
    }

    public void setIdassoc(Integer idassoc) {
        this.idassoc = idassoc;
    }

    @Basic
    @Column(name = "prixtotal")
    public Integer getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(Integer prixtotal) {
        this.prixtotal = prixtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conventions that = (Conventions) o;
        return id == that.id && Objects.equals(referenceconv, that.referenceconv) && Objects.equals(idassoc, that.idassoc) && Objects.equals(prixtotal, that.prixtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, referenceconv, idassoc, prixtotal);
    }

    @OneToOne(mappedBy = "conventionsByRefconv")
    public Paie getPaiesById() {
        return paiesById;
    }

    public void setPaiesById(Paie paiesById) {
        this.paiesById = paiesById;
    }
}
