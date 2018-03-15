/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Client;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author tlorettefr
 */
public class ClientDAO {
    
    public Client getClient(int id)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Client.class, id);
    }
    
    public void insertClient(Client client)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(client);
    }
    
    public Client getConnexion(String mail, String hash)
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select c from Client c where c.mail = :mail and c.password = :pwd");
        q.setParameter("mail", mail);
        q.setParameter("pwd", hash);
        try
        {
            return (Client) q.getSingleResult();
        }
        catch(NoResultException e)
        {
            return null;
        }  
    }
    
    
}
