/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import entity.Employe;
import entity.Medium;

/**
 *
 * @author tlorettefr
 */
public class InitService {
    
    public void init()
    {
        createEmployes();
        createMediums();
    }
    
    private void createEmployes()
    {
        EmployeDAO empDAO = new EmployeDAO();
        JpaUtil.ouvrirTransaction();
        for(Employe emp : BDDTest.employes)
        {
            empDAO.insertEmploye(emp);
        }
        JpaUtil.validerTransaction();
    }
    
    private void createMediums()
    {
        MediumDAO voyDAO = new MediumDAO();
        JpaUtil.ouvrirTransaction();
        for(Medium med : BDDTest.mediums)
        {
            voyDAO.insertMedium(med);
        }
        JpaUtil.validerTransaction();
    }
    
}
