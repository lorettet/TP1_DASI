/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Employe;
import entity.Medium;
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
        employes.add(new Employe("Lorette-Froidevaux", "Théo", "lorettet", "azerty12"));
        employes.add(new Employe("Martin", "Pierre", "martinp", "azerty13"));
        employes.add(new Employe("Dupont", "lepremier", "dupontl", "azerty14"));
        employes.add(new Employe("Durez", "Albert", "dureza", "azerty15"));
        employes.add(new Employe("Brioche", "Roger", "briocher", "azerty16"));
        employes.add(new Employe("Leprunier", "Mathieu", "leprunierm", "azerty17"));
        employes.add(new Employe("Labrute", "Michael", "labrutem", "azerty18"));
        employes.add(new Employe("durant", "Thérèse", "durantt", "azerty19"));
    }
}
