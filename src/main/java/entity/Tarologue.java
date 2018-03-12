/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;


/**
 *
 * @author tlorettefr
 */
@Entity
public class Tarologue extends Medium implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cartes;

    public Tarologue() {
    }
    
    

    public Tarologue(String nom, String bio, String cartes) {
        super(nom, bio);
        this.cartes = cartes;
    }

    public String getCartes() {
        return cartes;
    }

    public void setCartes(String cartes) {
        this.cartes = cartes;
    }

    @Override
    public String toString() {
        return super.toString() + "Talent: Tarologue\n" + "Cartes: " + cartes + "\n";
    }
    
   
    
}
