/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Client;
import entity.Employe;
import entity.Voyance;
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
    
    public Employe getConnexion(String username, String password)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select e from Employe e where e.username = :usr and password = :pwd");
        q.setParameter("usr", username);
        q.setParameter("pwd", password);
        try
        {
            return (Employe) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            return null;
        }
    }
    
    public List<Employe> getAllEmployes(){
         EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select e from Employe e");
        return q.getResultList();
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
    
    public int getNbVoyancesRealisees(Employe emp)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select v from Voyance v where v.employe = :emp and v.heureFin is not null");
        q.setParameter("emp", emp);
        return q.getResultList().size();
    }
    
    public Voyance getVoyanceAttente(Employe emp){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select v from Voyance v where v.employe = :emp and v.heureDebut = null");
        q.setParameter("emp", emp);
        try
        {
            return (Voyance) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            return null;
        }
    }
}