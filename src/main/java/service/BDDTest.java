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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static service.BDDTest.employes;

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
        

        
        Medium m = new Voyant("Gwenaël", "Spécialiste des grandes conversation au-delà de TOUTES frontières.", "Boule de Cristal");
        List<Employe> lemp = new ArrayList<>();
        lemp.add(employes.get(0));
        lemp.add(employes.get(1));
        mediums.add(m);
        
        m = new Voyant("J.Dalmarre", "Votre avenir est devant vous: regardons-le ensemble!", "Marc de Café");
        lemp = new ArrayList<>();
        lemp.add(employes.get(2));
        lemp.add(employes.get(3));
        mediums.add(m);
        
        m = new Tarologue("Mme Irma", "Comprenez votre entourage grâce à mes cartes! Résultats rapides.", "Tarot de Marseille");
        lemp = new ArrayList<>();
        lemp.add(employes.get(4));
        lemp.add(employes.get(5));
        mediums.add(m);
        
        m = new Tarologue("Mme Lisa Maria NGUYINIA", "Mes cartes spécialisées pout la région Bretagne répondront à toutes vos questions personnelles.", "Tarot de Brocéliande");
        lemp = new ArrayList<>();
        lemp.add(employes.get(6));
        lemp.add(employes.get(7));
        mediums.add(m);
        
        try{
        m = new Astrologue("Mme Sara", "Basée à Champigny-sur-Marne, Mme Sara vous révlèlera votre avenir pour éclairer vvotre passé.", "École Normale Supérieure d'Astrologie (ENS-Astro)", sdf.parse("2006"));
        lemp = new ArrayList<>();
        lemp.add(employes.get(0));
        lemp.add(employes.get(1));
        mediums.add(m);
        
        m = new Astrologue("Mme Mounia Mounia", "Avenir, avenir, que nous réserves-tu? N'attendez plus, demandez à me consulter!", "Institut des Nouveaux Savoirs Astrologiques", sdf.parse("2010"));
        lemp = new ArrayList<>();
        lemp.add(employes.get(2));
        lemp.add(employes.get(3));
        mediums.add(m);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
    }
}
