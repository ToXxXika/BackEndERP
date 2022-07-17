package com.example.backerp.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Utilisateur {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "prenom")
    private String prenom;
    @Basic
    @Column(name = "cin")
    private String cin;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "password")
    private String password;
    private Collection<Logparticipation> logparticipationsById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(cin, that.cin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, cin);
    }

    @OneToMany(mappedBy = "utilisateurByIdutilisateur")
    public Collection<Logparticipation> getLogparticipationsById() {
        return logparticipationsById;
    }

    public void setLogparticipationsById(Collection<Logparticipation> logparticipationsById) {
        this.logparticipationsById = logparticipationsById;
    }
}
