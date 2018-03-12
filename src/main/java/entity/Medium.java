/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author tlorettefr
 */
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Medium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String nom;
    private String bio;
    @OneToMany
    private List<Employe> employes;

    public Medium() {
    }

    public Medium(String nom, String bio) {
        this.nom = nom;
        this.bio = bio;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + "\nBio: " + bio + "\n";
    }

    
}
