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
        
        Client c = new Client("emilie", "test", 'F', sdf.parse("20/12/1997"), "564654", "mailtest", "azerty", "erigbqg");

        cs.CreerClient(c);
        
        System.out.println(cs.connect("mailtest", "azerty"));
        
        
        JpaUtil.fermerEntityManager();
        JpaUtil.destroy();
    }
    
}
