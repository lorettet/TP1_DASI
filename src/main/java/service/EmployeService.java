/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.common.hash.Hashing;
import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import dao.VoyanceDAO;
import entity.Client;
import entity.Employe;
import entity.Medium;
import entity.Voyance;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import util.AstroApi;

/**
 *
 * @author tlorettefr
 */
public class EmployeService {
    
    private final VoyanceDAO voyanceDAO;
    private final EmployeDAO employeDAO;
    private final MediumDAO mediumDAO;
    
    public EmployeService()
    {
        voyanceDAO = new VoyanceDAO();
        employeDAO = new EmployeDAO();
        mediumDAO = new MediumDAO();
    }
    
    /**
     * Renvoie l'employé qui veut se connecter
     * @param username nom d'utilisateur de l'employé
     * @param password son mot de passe
     * @return l'employé connecté ou null si les informations ne sont pas correctes
     */
    public Employe connect(String username, String password)
    {
        JpaUtil.ouvrirTransaction();
        password = Hashing.sha256().hashString(password,StandardCharsets.UTF_8).toString();
        Employe e = employeDAO.getConnexion(username, password);
        JpaUtil.validerTransaction();
        return e;
    }
    
    /**
     * Lance le chat. Rentre l'heure de début dans la voyance.
     * La voyance doit exister dans la base
     * @param v la voyance
     */
    public void lancerChat(Voyance v){
        JpaUtil.ouvrirTransaction();
        Date date = new Date();
        v.setHeureDebut(date);
        voyanceDAO.modify(v);
        JpaUtil.validerTransaction();
    }
 
    /**
     * Termine le chat. Rentre une heure de fin dans la voyance.
     * La voyance doit exister dans la base
     * @param v la voyance
     */
    public void terminerChat(Voyance v){
        JpaUtil.ouvrirTransaction();
        Date date = new Date();
        v.setHeureFin(date);
        voyanceDAO.modify(v);
        
        Employe emp = v.getEmploye();
        emp.setAvailable(true);
        employeDAO.modify(emp);
        
        JpaUtil.validerTransaction();
    }
    
    /**
     * Termine le chat. Rentre une heure de fin et ajoute un commentaire.
     * La voyance doit exister dans la base
     * @param v la voyance
     * @param commentaire le commentaire pour la voyance
     */
    public void terminerChat(Voyance v, String commentaire){
        v.setCommentaire(commentaire);
        terminerChat(v);
    }
    
    /**
     * Renvoie la voyance sur laquelle travaille (déjà en cours) ou doit 
     * travailler (pas encore en cours) l'employé.
     * @param emp l'employé
     * @return La voyance ou null si aucune voyance n'est attribuée.
     */
    public Voyance getCurrentVoyance(Employe emp){
        JpaUtil.ouvrirTransaction();
        Voyance v = employeDAO.getCurrentVoyance(emp);
        JpaUtil.validerTransaction();
        return v;
    }
    
    /**
     * Renvoie l'historique des voyances d'un client
     * @param c le client
     * @return la liste des voyances
     */
     public List<Voyance> getListVoyance(Client c)
    {
        JpaUtil.ouvrirTransaction();
        List<Voyance> lv = voyanceDAO.getVoyancesClient(c);
        JpaUtil.validerTransaction();
        return lv;
    }
     
     /**
      * Premier diagramme : renvoie une liste de couples Medium/entier 
      * correspondant au nombre de demandes par medium.
      * @return 
      */
    public List<Pair<Medium, Integer>> getNbDemandesParMedium(){
        JpaUtil.ouvrirTransaction();
        List<Medium> ml = mediumDAO.getAllMedium();
        List<Pair<Medium, Integer>> res  = new ArrayList();
        
        for(Medium m : ml){
            res.add(new Pair(m, voyanceDAO.getNbVoyancesMedium(m)));
        }
        
        JpaUtil.validerTransaction();

        return res;
        
    }
    
    /**
     * Deuxième diagramme : renvoie une liste de couples Employe/entier 
     * correspondant au nombre de voyances terminées par Employé. 
     * @return 
     */
    public List<Pair<Employe, Integer>> getNbVoyancesRealiseesParEmploye(){
        
        JpaUtil.ouvrirTransaction();
        List<Employe> el = employeDAO.getAllEmployes();
        
        List<Pair<Employe, Integer>> res  = new ArrayList();
        
        for(Employe e : el){
            res.add(new Pair(e, employeDAO.getNbVoyancesRealisees(e)));
        }
        JpaUtil.validerTransaction();
        return res;
    }
    
    /**
     * Troisème diagramme : renvoie une liste de couples Employe/entier 
     * correspondant à la répartition (%) des voyances terminées par Employé. 
     * @return 
     */
    public List<Pair<Employe, Integer>> getRepartitionVoyancesRealiseesParEmploye(){
        
        JpaUtil.ouvrirTransaction();
        List<Employe> el = employeDAO.getAllEmployes();
        int total = voyanceDAO.getNbVoyances();
        JpaUtil.validerTransaction();

        List<Pair<Employe, Integer>> res  = new ArrayList();
         if(total == 0)
             return res;
        for(Employe e : el){
            res.add(new Pair(e, employeDAO.getNbVoyancesRealisees(e)*100/total));
        }
        return res;
    }
    
    /**
     * Renvoie l'employé en fonction de son id
     * @param id l'identifiant de l'employé
     * @return l'employé ou null s'il ne figure pas dans la base
     */
    public Employe getEmploye(int id)
    {
        JpaUtil.ouvrirTransaction();
        Employe emp = employeDAO.getSingleEmploye(id);
        JpaUtil.validerTransaction();
        return emp;
    }
    
    /**
     * Renvoie la voyance en fonction de son id
     * @param id l'identifiant de la voyance
     * @return la voyance ou null si elle ne figure pas dans la base
     */
    public Voyance getVoyance(int id)
    {
        JpaUtil.ouvrirTransaction();
        Voyance v = voyanceDAO.getVoyance(id);
        JpaUtil.validerTransaction();
        return v;
    }
    
    /**
     * Met à jour un employé en fonction des données de la base
     * L'employé doit exister dans la base
     * @param emp l'employé
     * @return l'employé mis à jour
     */
    public Employe updateEmploye(Employe emp)
    {
        return getEmploye(emp.getId());
    }
    
     /**
     * Met à jour une voyance en fonction des données de la base
     * @param v la voyance
     * @return la voyance mise à jour
     */
    public Voyance updateVoyance(Voyance v)
    {
        return getVoyance(v.getId());
    }
    
    /**
     * Demander une prediction à partir d'un profil astrologique et de 3 notes
     * pour l'amour, la santé et le travail
     * @param c le client
     * @param amour la note pour l'amour
     * @param sante la note pour la santé
     * @param travail la note pour le travail
     * @return la prediction
     */
    public List<String> demanderPrediction(Client c, int amour, int sante, int travail)
    {
    	AstroApi astro = new AstroApi(AstroApi.MA_CLÉ_ASTRO_API);
    	try {
			return astro.getPredictions(c.getCouleur(), c.getAnimal(), amour, sante, travail);
		} catch (IOException e) {
			return null;
		}
    }
}
