/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Client;
import entity.Medium;
import entity.Voyance;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tlorettefr
 */
public class VoyanceDAO {
    
    public void insertVoyance(Voyance v)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(v);
    }
    
    public void modify(Voyance v)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(v);
    }
    
    public Voyance getVoyance(int id)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Voyance.class,id);
    }
    
    public List<Voyance> getVoyancesClient(Client c)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select v from Voyance v where v.client = :client and v.heureFin is not null");
        q.setParameter("client", c);
        return q.getResultList();
    }
    
    public int getNbVoyancesMedium(Medium m){
         EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select v from Voyance v where v.medium = :med");
        q.setParameter("med", m);
        return q.getResultList().size();
    }
    
    public int getNbVoyances(){
         EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select v from Voyance v");
        return q.getResultList().size();
    }

}
