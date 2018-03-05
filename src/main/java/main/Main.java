/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.ClientDAO;
import dao.EmployeDAO;
import entity.Client;
import dao.JpaUtil;
import dao.MediumDAO;
import dao.VoyanceDAO;
import entity.Astrologue;
import entity.Employe;
import entity.Medium;
import entity.Tarologue;
import entity.Voyance;
import entity.Voyant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tlorettefr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        JpaUtil.init();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
        ClientDAO clientDAO = new ClientDAO();
        EmployeDAO employeDAO = new EmployeDAO();
        MediumDAO mediumDAO = new MediumDAO();
        VoyanceDAO voyanceDAO = new VoyanceDAO();

        JpaUtil.creerEntityManager();
        
        JpaUtil.ouvrirTransaction();

        Voyance v = new Voyance(mediumDAO.getMedium("Toi"), clientDAO.getClient(51));
        
        voyanceDAO.insertVoyance(v);
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        JpaUtil.destroy();
    }
    
}
