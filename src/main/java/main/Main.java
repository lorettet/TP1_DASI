/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.ClientDAO;
import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import dao.VoyanceDAO;
import entity.Client;
import entity.Employe;
import entity.Medium;
import entity.Voyance;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.util.Pair;
import service.ClientService;
import service.EmployeService;
import service.InitService;

/**
 *
 * @author tlorettefr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, InterruptedException {
        JpaUtil.init();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
        InitService initS = new InitService();
        EmployeService empS = new EmployeService();
        ClientService cliS = new ClientService();
        final int TPS_ATTENTE = 3000;
        
        JpaUtil.creerEntityManager();
        
        System.out.println("Initialisation de la base de données...");
        
        initS.init();
        
        System.out.println("Inscription d'un client");
        Thread.sleep(TPS_ATTENTE);
        Client nouveauClient = new Client("Lorette", "Théo", 'M', sdf.parse("24/11/1997"), "0612131415", "lorette@insa-lyon.fr", "monpwd", "quelque part");
        if(cliS.CreerClient(nouveauClient)){
            System.out.println("Le client a bien été enregristé");
        }
        else{
            System.err.println("Erreur lors de l'enregistrement du nouveau client");
        }
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("Inscription d'un autre client avec la même adresse");
        Thread.sleep(TPS_ATTENTE);
        Client nouveauClientErr = new Client("Un autre", "client", 'F', sdf.parse("24/11/1997"), "0456898745", "lorette@insa-lyon.fr", "azerty123", "quelque part");
        if(!cliS.CreerClient(nouveauClientErr)){
            System.out.println("Le client a bien été refusé");
        }
        else{
            System.err.println("Erreur lors de l'enregistrement du nouveau client");
        }
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("Connexion du nouveau client : NOK");
        Thread.sleep(TPS_ATTENTE);
        Client leClient = cliS.connect("lorette@insa-lyon.fr", "erreur");
        if(leClient == null){
            System.out.println("La connexion a bien été refusé");
        }
        else{
            System.err.println("Erreur lors de la connexion");
        }
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("Connexion du nouveau client : OK");
        Thread.sleep(TPS_ATTENTE);
        leClient = cliS.connect("lorette@insa-lyon.fr", "monpwd");
        if(leClient != null){
            System.out.println("La connexion a bien été accepté, voici les infos du client :");
            System.out.println(leClient);
        }
        else{
            System.err.println("Erreur lors de la connexion");
        }

        System.out.println("Le client consulte la liste des voyants");
        Thread.sleep(TPS_ATTENTE);
        
        JpaUtil.fermerEntityManager();
        JpaUtil.destroy();
    }
    
}
