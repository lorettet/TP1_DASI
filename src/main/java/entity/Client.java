/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.Color;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

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
    private String adresse;
    private Zodiaque signeZodiaque;
    private AstroChinois signeChinois;
    private Color couleur;
    
    public Client() {
    }

    public Client(String nom, String prenom, char civilite, Date dateNaissance, String tel, String mail, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
        this.mail = mail;
        this.adresse = adresse;
    }
    
    
    
    public int getId() {
        return clientId;
    }

    public void setId(int id) {
        this.clientId = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public char getCivilite() {
        return civilite;
    }

    public void setCivilite(char civilite) {
        this.civilite = civilite;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Zodiaque getZodiaque() {
        return signeZodiaque;
    }

    public void setZodiaque(Zodiaque zodiaque) {
        this.signeZodiaque = zodiaque;
    }

    public AstroChinois getAstroChinois() {
        return signeChinois;
    }

    public void setAstroChinois(AstroChinois astroChinois) {
        this.signeChinois = astroChinois;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
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
