/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.common.hash.Hashing;
import dao.ClientDAO;
import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import dao.VoyanceDAO;
import entity.Client;
import entity.Employe;
import entity.Medium;
import entity.Voyance;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.persistence.RollbackException;

/**
 *
 * @author tlorettefr
 */
public class ClientService {
    
    private final ClientDAO clientDAO;
    private final VoyanceDAO voyanceDAO;
    private final EmployeDAO employeDAO;
    private final MediumDAO mediumDAO;
    
    public ClientService()
    {
        clientDAO = new ClientDAO();
        voyanceDAO = new VoyanceDAO();
        employeDAO = new EmployeDAO();
        mediumDAO = new MediumDAO();
    }

    /**
     * Connecte un client
     * @param mail le mail du client
     * @param password son mot de passe
     * @return le client connecté, ou null si la connexion à échoué
     */
    public Client connect(String mail, String password)
    {
        JpaUtil.ouvrirTransaction();
        password = Hashing.sha256().hashString(password,StandardCharsets.UTF_8).toString();
        Client client = clientDAO.getConnexion(mail, password);
        JpaUtil.validerTransaction();
        return client;
    }
    
    /**
     * Ajoute un nouveau client dans la base de données
     * @param client le client
     * @return true si le client à bien été ajouté, false si une erreur s'est produite.
     */
    public boolean CreerClient(Client client)
    {
        JpaUtil.ouvrirTransaction();
        try{
            clientDAO.insertClient(client);
            JpaUtil.validerTransaction();
        } catch (RollbackException e)
        {
            JpaUtil.annulerTransaction();
            return false;
        }
        return true;
    }
    
    /**
     * Écrit un mail de confirmation ou d'infirmation d'inscription.
     * @param client le client
     * @param success true si le client a bien été inscrit à positif
     * @return le mail à envoyer
     */
    public String mailInscription(Client client, boolean success){
        String res = "Expediteur: contact@posit.if\n" + "Pour: " + client.getMail() + "\nSujet: Bienvenue chez POSIT'IF\n\nBonjour " + client.getPrenom()+",\n";
        
        if(success){
            res += "Nous vous confirmons votre inscription au service POSIT'IF. Votre numéro de client est: " + client.getClientId()+"\n";
        }else{
            res += "Votre inscription au service POSIT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.\n";
        }
        return res;
    }
    
    /**
     * Demande une voyance. Le client à selectionné un médium et demande une voyance.
     * @param medium le medium
     * @param client le client
     * @return true si un employé a été assigné, false si personne n'a été trouvé
     */
    public boolean demanderVoyance(Medium medium, Client client)
    {
        JpaUtil.ouvrirTransaction();
        Voyance v = new Voyance(medium,client);
        voyanceDAO.insertVoyance(v);
        JpaUtil.validerTransaction();
        
        JpaUtil.ouvrirTransaction();
        List<Employe> empMed = medium.getEmployes();
        
        // On cherche un employé disponible
        Employe theEmploye = null;
        for(Employe emp : empMed)
        {
            if(emp.isAvailable())
            {
                if(theEmploye == null)
                {
                    theEmploye = emp;
                }
                else if(employeDAO.getNbVoyance(theEmploye) > employeDAO.getNbVoyance(emp))
                {
                    theEmploye = emp;
                }
            }
        }
        JpaUtil.validerTransaction();
        
        if(theEmploye == null)
        {
            return false;   
        }
        
        JpaUtil.ouvrirTransaction();
        v.setEmploye(theEmploye);
        voyanceDAO.modify(v);
        theEmploye.setAvailable(false);
        employeDAO.modify(theEmploye);
        JpaUtil.validerTransaction();
        return true;
    }
    
    /**
     * Renvoie la liste des voyances terminées pour un client donnée
     * @param c le client
     * @return la liste des voyance terminées
     */
    public List<Voyance> getListVoyance(Client c)
    {
        JpaUtil.ouvrirTransaction();
        List<Voyance> lv = voyanceDAO.getVoyancesClient(c);
        JpaUtil.validerTransaction();
        return lv;
    }
    
    /**
     * Renvoie la liste de tous les mediums
     * @return la liste des mediums
     */
    public List<Medium> getAllMedium()
    {
        JpaUtil.ouvrirTransaction();
        List<Medium> lm = mediumDAO.getAllMedium();
        JpaUtil.validerTransaction();
        return lm;
    }
}
