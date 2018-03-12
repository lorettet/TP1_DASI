/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Medium;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author tlorettefr
 */
public class MediumDAO {

    public MediumDAO() {
       
    }
    
    public void insertMedium(Medium m)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(m);
    }
    
    public List<Medium> getAllMedium()
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.createQuery("select m from Medium m").getResultList();
    }
    
    public Medium getMedium(String name)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Medium.class,name);
        
    }
    
}
