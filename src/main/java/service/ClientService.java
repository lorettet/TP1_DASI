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

    public Client connect(String mail, String password)
    {
        JpaUtil.ouvrirTransaction();
        password = Hashing.sha256().hashString(password,StandardCharsets.UTF_8).toString();
        Client client = clientDAO.getConnexion(mail, password);
        JpaUtil.validerTransaction();
        return client;
    }
    
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
    
    public List<Voyance> getListVoyance(Client c)
    {
        JpaUtil.ouvrirTransaction();
        List<Voyance> lv = voyanceDAO.getVoyancesClient(c);
        JpaUtil.validerTransaction();
        return lv;
    }
    
    public List<Medium> getAllMedium()
    {
        JpaUtil.ouvrirTransaction();
        List<Medium> lm = mediumDAO.getAllMedium();
        JpaUtil.validerTransaction();
        return lm;
    }
}
