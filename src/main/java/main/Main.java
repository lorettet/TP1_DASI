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

/**
 *
 * @author tlorettefr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        System.out.println("test");
        JpaUtil.init();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
        ClientDAO clientDAO = new ClientDAO();
        EmployeDAO employeDAO = new EmployeDAO();
        MediumDAO mediumDAO = new MediumDAO();
        VoyanceDAO voyanceDAO = new VoyanceDAO();

        JpaUtil.creerEntityManager();
        
        ClientService cs = new ClientService();
        cs.CreerClient(new Client("theo", "theo", 'M', sdf.parse("12/12/1212"), "65445486", "ceciestunmail", "zegzg"));
        cs.CreerClient(new Client("erg", "sg", 'F', sdf.parse("11/11/1111"), "65464", "ceciestunmail", "egerg"));
        
        Employe emp = employeDAO.getSingleEmploye(101);
        Voyance v = employeDAO.getVoyanceAttente(emp);
        System.out.println(v);
        
        JpaUtil.ouvrirTransaction();
        Medium m = mediumDAO.getMedium("test");
        
        for(Employe empp : m.getEmployes())
            System.out.println(empp);
        JpaUtil.validerTransaction();
        
        
        EmployeService es = new EmployeService();
        //es.lancerChat(v);
        
        List<Pair<Medium, Integer>> lm = es.getNbDemandesParMedium();
        for (Pair<Medium, Integer> p : lm){
            System.out.println(p);
        }
        
        List<Pair<Employe, Integer>> le = es.getNbVoyancesRealiseesParEmploye();
        for (Pair<Employe, Integer> p : le){
            System.out.println(p);
        }
        
        
        JpaUtil.fermerEntityManager();
        JpaUtil.destroy();
    }
    
}
