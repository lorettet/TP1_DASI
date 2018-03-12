/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author tlorettefr
 */
public class EmployeDAO {
    
    public void insertEmploye(Employe employe){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(employe);
    }
    
    public Employe getSingleEmploye(int id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Employe.class, id);
    }
    
    public List<Employe> getAvailableEmployes(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select e from Employe e where e.available = true");
        return q.getResultList();
    }
    
    public void modify(Employe employe)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(employe);
    }
    
    public int getNbVoyance(Employe emp)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select v from Voyance v where v.employe = :emp");
        q.setParameter("emp", emp);
        return q.getResultList().size();
    }
}