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
import service.BDDTest;
import service.ClientService;
import service.EmployeService;
import service.InitService;

/**
 *
 * @author tlorettefr
 */
public class Main {
    
    static int nbTest = 0;
    static int nbSuccess = 0;
    static int nbError = 0;
    final static int TPS_ATTENTE = 0;

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

        
        JpaUtil.creerEntityManager();
        
        System.out.println("=== Initialisation de la base de données... ===");
        
        initS.init();
        
        System.out.println("=== Inscription d'un client ===");
        Thread.sleep(TPS_ATTENTE);
        Client nouveauClient = new Client("Lorette", "Théo", 'M', sdf.parse("24/11/1997"), "0612131415", "lorette@insa-lyon.fr", "monpwd", "quelque part");
        Main.testMsg("Le client a bien été enregristé", "Erreur lors de l'enregistrement du nouveau client", cliS.CreerClient(nouveauClient));
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== Inscription d'un autre client avec la même adresse ===");
        Thread.sleep(TPS_ATTENTE);
        Client nouveauClientErr = new Client("Un autre", "client", 'F', sdf.parse("24/11/1997"), "0456898745", "lorette@insa-lyon.fr", "azerty123", "quelque part");
        Main.testMsg("Le client a bien été refusé", "Erreur lors de l'enregistrement du nouveau client", !cliS.CreerClient(nouveauClientErr));
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== Connexion du nouveau client : NOK ===");
        Thread.sleep(TPS_ATTENTE);
        Client leClient = cliS.connect("lorette@insa-lyon.fr", "erreur");
        Main.testMsg("La connexion a bien été refusé", "Erreur lors de la connexion", leClient == null);
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== Connexion du nouveau client : OK ===");
        Thread.sleep(TPS_ATTENTE);
        leClient = cliS.connect("lorette@insa-lyon.fr", "monpwd");
        Main.testMsg("La connexion a bien été accepté, voici les infos du client :", "Erreur lors de la connexion", leClient != null, leClient);
        Thread.sleep(TPS_ATTENTE);

        System.out.println("=== Le client consulte la liste des voyants ===");
        Thread.sleep(TPS_ATTENTE);
        List<Medium> lm = cliS.getAllMedium();
        for(Medium m : lm)
        {
            System.out.println(m.toString());
        }
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== L'employé dupontl se connecte ===");
        Thread.sleep(TPS_ATTENTE);
        Employe emp = empS.connect("dupontl", "azerty14");
        Main.testMsg("L'employe dupontl s'est bien connecté, voici ses informations", "Erreur lors de la connection de l'employé", emp != null, emp);
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== dupontl regarde s'il y a une voyance ===");
        Thread.sleep(TPS_ATTENTE);
        Voyance v = empS.getCurrentVoyance(emp);
        Main.testMsg("Il n'y a aucune voyance en cour", "Une voyance lui a été attribué", v==null);
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== Le client demande une voyance à Mme Mounia Mounia ===");
        Main.testMsg("La demande a bien été envoyé!", "La demande a échoué!", cliS.demanderVoyance(lm.get(0), leClient));
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== L'employé dupont n'est plus disponible ===");
        Thread.sleep(TPS_ATTENTE);
        emp = empS.updateEmploye(emp);
        Main.testMsg("dupontl n'est plus disponible", "dupontl est toujours disponible", !emp.isAvailable(), emp);
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== dupontl consulte sa voyance en cour ===");
        Thread.sleep(TPS_ATTENTE);
        v = empS.getCurrentVoyance(emp);
        Main.testMsg("Il n'y a aucune voyance en cour, la voici :", "Une voyance lui a été attribué", v!=null, v);
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== dupontl accepete la voyance ===");
        Thread.sleep(TPS_ATTENTE);
        empS.lancerChat(v);
        v = empS.getCurrentVoyance(emp);
        System.out.println(v);
        Main.testMsg("La voyance a bien été accepeté", "Erreur lors de l acceptation", v.getHeureDebut()!=null, v);
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== dupont ferme la voyance en cour ===");
        Thread.sleep(TPS_ATTENTE);
        empS.terminerChat(v, "Tout va bien!");
        v = empS.updateVoyance(v);
        Main.testMsg("La voyance a bien été mise a jour", "La voyance n'a pas été mise à jour", v.getHeureFin() != null, v);
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== dupont est de nouveau disponible ===");
        Thread.sleep(TPS_ATTENTE);
        emp = empS.updateEmploye(emp);
        Main.testMsg("dupont est bien disponible", "dupontl reste indisponible", emp.isAvailable(), emp);
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== dupont souhaite consulter la liste des voyances de son client ===");
        Thread.sleep(TPS_ATTENTE);
        List<Voyance> lv = empS.getListVoyance(leClient);
        Main.testMsg("La liste est viable", "La liste est fausse", lv.size() == 1, lv.get(0));
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== Nombre de demandes par employé=== ");
        Thread.sleep(TPS_ATTENTE);
        List<Pair<Medium,Integer>> stats1 = empS.getNbDemandesParMedium();
        for(Pair<Medium, Integer> p : stats1)
        {
            System.out.println(p.getKey().getNom() + " : " + p.getValue());
        }
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== Nombre de demandes par employé=== ");
        Thread.sleep(TPS_ATTENTE);
        List<Pair<Employe,Integer>> stats2 = empS.getNbVoyancesRealiseesParEmploye();
        for(Pair<Employe, Integer> p : stats2)
        {
            System.out.println(p.getKey().getUsername()+ " : " + p.getValue());
        }
        Thread.sleep(TPS_ATTENTE);
        
        System.out.println("=== Répartition des demandes par employé=== ");
        Thread.sleep(TPS_ATTENTE);
        List<Pair<Employe,Integer>> stats3 = empS.getRepartitionVoyancesRealiseesParEmploye();
        for(Pair<Employe, Integer> p : stats3)
        {
            System.out.println(p.getKey().getUsername()+ " : " + p.getValue() + "%");
        }
        Thread.sleep(TPS_ATTENTE);
            
        
        JpaUtil.fermerEntityManager();
        JpaUtil.destroy();
        
        System.out.println("=========== FIN ==========");
        System.out.println("Tests : " + nbTest);
        System.out.println("Succes : " + nbSuccess);
        System.out.println("Erreur : " + nbError);
    }
    
    private static void testMsg(String successMsg, String errorMsg, boolean testSucces)
    {
        testMsg(successMsg, errorMsg, testSucces, null);
    }
    
    private static void testMsg(String successMsg, String errorMsg, boolean testSucces, Object o)
    {
        nbTest++;
        if(testSucces)
        {
            System.out.println("#SUCCESS# " + successMsg);
            if(o!=null) System.out.println(o);
            nbSuccess++;
        }
        else{
            System.err.println("#ERROR# " + errorMsg);
            nbError++;
        }
    }
    
}
