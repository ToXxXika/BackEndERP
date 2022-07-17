package com.example.backerp.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Associations {
    private int idAssoc;
    private String libelle;
    private Integer reference;
    private Collection<Conventions> conventionsByIdAssoc;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAssoc")
    public int getIdAssoc() {
        return idAssoc;
    }

    public void setIdAssoc(int idAssoc) {
        this.idAssoc = idAssoc;
    }

    @Basic
    @Column(name = "Libelle")
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Basic
    @Column(name = "reference")
    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Associations that = (Associations) o;
        return idAssoc == that.idAssoc && Objects.equals(libelle, that.libelle) && Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAssoc, libelle, reference);
    }

    @OneToMany(mappedBy = "associationsByIdassoc")
    public Collection<Conventions> getConventionsByIdAssoc() {
        return conventionsByIdAssoc;
    }

    public void setConventionsByIdAssoc(Collection<Conventions> conventionsByIdAssoc) {
        this.conventionsByIdAssoc = conventionsByIdAssoc;
    }
}
