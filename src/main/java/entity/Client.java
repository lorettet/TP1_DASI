/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.google.common.hash.Hashing;
import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import util.AstroApi;

/**
 *
 * @author tlorettefr
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int clientId;
    private String nom;
    private String prenom;
    private char civilite;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private String tel;
    @Column(unique=true)
    private String mail;
    private String password;
    private String adresse;
    private String signeZodiaque;
    private String signeChinois;
    private String couleur;
    private String animal;
    
    public Client() {
    }

    public Client(String nom, String prenom, char civilite, Date dateNaissance, String tel, String mail, String password, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
        this.mail = mail;
        this.adresse = adresse;
        this.password = Hashing.sha256().hashString(password,StandardCharsets.UTF_8).toString();
        AstroApi astro = new AstroApi(AstroApi.MA_CLÉ_ASTRO_API);
        try {
            List<String> profileAstro = astro.getProfil(prenom,dateNaissance);
            this.signeZodiaque = profileAstro.get(0);
            this.signeChinois = profileAstro.get(1);
            this.couleur = profileAstro.get(2);
            this.animal = profileAstro.get(3);
            
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }

    public int getClientId() {
        return clientId;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public char getCivilite() {
        return civilite;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCivilite(char civilite) {
        this.civilite = civilite;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) clientId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if (this.clientId != other.clientId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "clientId=" + clientId + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + '}';
    }
    
}
