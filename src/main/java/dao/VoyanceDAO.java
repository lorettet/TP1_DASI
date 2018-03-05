/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Voyance;
import javax.persistence.EntityManager;

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

}
