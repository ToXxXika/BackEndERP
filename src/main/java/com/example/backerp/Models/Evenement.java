package com.example.backerp.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Evenement {
    private int idevent;
    private Integer association;
    private String description;
    private Integer details;
    private Collection<Logparticipation> logparticipationsByIdevent;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idevent")
    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    @Basic
    @Column(name = "association")
    public Integer getAssociation() {
        return association;
    }

    public void setAssociation(Integer association) {
        this.association = association;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "details")
    public Integer getDetails() {
        return details;
    }

    public void setDetails(Integer details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evenement evenement = (Evenement) o;
        return idevent == evenement.idevent && Objects.equals(association, evenement.association) && Objects.equals(description, evenement.description) && Objects.equals(details, evenement.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idevent, association, description, details);
    }

    @OneToMany(mappedBy = "evenementByIdevent")
    public Collection<Logparticipation> getLogparticipationsByIdevent() {
        return logparticipationsByIdevent;
    }

    public void setLogparticipationsByIdevent(Collection<Logparticipation> logparticipationsByIdevent) {
        this.logparticipationsByIdevent = logparticipationsByIdevent;
    }
}
