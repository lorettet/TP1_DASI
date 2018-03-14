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
import static entity.Voyance_.heureDebut;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author tlorettefr
 */
public class EmployeService {
    
    private final ClientDAO clientDAO;
    private final VoyanceDAO voyanceDAO;
    private final EmployeDAO employeDAO;
    private final MediumDAO mediumDAO;
    
    public EmployeService()
    {
        clientDAO = new ClientDAO();
        voyanceDAO = new VoyanceDAO();
        employeDAO = new EmployeDAO();
        mediumDAO = new MediumDAO();
    }
    
    public Employe connect(String username, String password)
    {
        JpaUtil.ouvrirTransaction();
        password = Hashing.sha256().hashString(password,StandardCharsets.UTF_8).toString();
        Employe e = employeDAO.getConnexion(username, password);
        JpaUtil.validerTransaction();
        return e;
    }
    
    public void lancerChat(Voyance v){
        JpaUtil.ouvrirTransaction();
        Date date = new Date();
        v.setHeureDebut(date);
        voyanceDAO.modify(v);
        JpaUtil.validerTransaction();
    }
 
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
    
    public void terminerChat(Voyance v, String commentaire){
        v.setCommentaire(commentaire);
        terminerChat(v);
    }
    
    public Voyance getCurrentVoyance(Employe emp){
        JpaUtil.ouvrirTransaction();
        Voyance v = employeDAO.getCurrentVoyance(emp);
        JpaUtil.validerTransaction();
        return v;
    }
    
     public List<Voyance> getListVoyance(Client c)
    {
        JpaUtil.ouvrirTransaction();
        List<Voyance> lv = voyanceDAO.getVoyancesClient(c);
        JpaUtil.validerTransaction();
        return lv;
    }
     
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
    
    public Employe getEmploye(int id)
    {
        JpaUtil.ouvrirTransaction();
        Employe emp = employeDAO.getSingleEmploye(id);
        JpaUtil.validerTransaction();
        return emp;
    }
    
    public Voyance getVoyance(int id)
    {
        JpaUtil.ouvrirTransaction();
        Voyance v = voyanceDAO.getVoyance(id);
        JpaUtil.validerTransaction();
        return v;
    }
}
