/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;


/**
 *
 * @author tlorettefr
 */
@Entity
public class Astrologue extends Medium implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ecole;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date promotion;

    public Astrologue() {
    }

    public Astrologue(String nom, String bio, String ecole, Date promotion) {
        super(nom, bio);
        this.ecole = ecole;
        this.promotion = promotion;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public Date getPromotion() {
        return promotion;
    }

    public void setPromotion(Date promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "Astrologue{" + "ecole=" + ecole + ", promotion=" + promotion + " super=" + super.toString() + '}';
    }
    
    
    
}