/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Astrologue;
import entity.Employe;
import entity.Medium;
import entity.Tarologue;
import entity.Voyant;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tlorettefr
 */
public class BDDTest {
    
    
    public final static List<Employe> employes = new ArrayList<Employe>();
    
    public final static List<Medium> mediums = new ArrayList<Medium>();
    
    static
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        
        employes.add(new Employe("Lorette-Froidevaux", "Théo", "lorettet", "azerty12"));
        employes.add(new Employe("Martin", "Pierre", "martinp", "azerty13"));
        employes.add(new Employe("Dupont", "lepremier", "dupontl", "azerty14"));
        employes.add(new Employe("Durez", "Albert", "dureza", "azerty15"));
        employes.add(new Employe("Brioche", "Roger", "briocher", "azerty16"));
        employes.add(new Employe("Leprunier", "Mathieu", "leprunierm", "azerty17"));
        employes.add(new Employe("Labrute", "Michael", "labrutem", "azerty18"));
        employes.add(new Employe("durant", "Thérèse", "durantt", "azerty19"));
        
        mediums.add(new Voyant("Gwenaël", "Spécialiste des grandes conversation au-delà de TOUTES frontières.", "Boule de Cristal"));
        mediums.add(new Voyant("J.Dalmarre", "Votre avenir est devant vous: regardons-le ensemble!", "Marc de Café"));
        mediums.add(new Tarologue("Mme Irma", "Comprenez votre entourage grâce à mes cartes! Résultats rapides.", "Tarot de Marseille"));
        mediums.add(new Tarologue("Mme Lisa Maria NGUYINIA", "Mes cartes spécialisées pout la région Bretagne répondront à toutes vos questions personnelles.", "Tarot de Brocéliande"));
        mediums.add(new Astrologue("Mme Sara", "Basée à Champigny-sur-Marne, Mme Sara vous révlèlera votre avenir pour éclairer vvotre passé.", "École Normale Supérieure d'Astrologie (ENS-Astro)", sdf.parse("2006")));
    }
}
