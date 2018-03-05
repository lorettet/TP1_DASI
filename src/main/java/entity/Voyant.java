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
public class Voyant extends Medium implements Serializable {

    private static final long serialVersionUID = 1L;
    private String support;

    public Voyant() {
    }
    
    

    public Voyant(String nom, String bio, String support) {
        super(nom, bio);
        this.support = support;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "Voyant{" + "support=" + support + " super="+ super.toString() + '}';
    }

    
    
    

   
    
}
