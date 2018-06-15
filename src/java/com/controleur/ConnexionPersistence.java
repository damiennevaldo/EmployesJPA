/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author esic
 */
@Stateless
public class ConnexionPersistence {

    @PersistenceContext(unitName = "EmployesServletJPAPU")
    private EntityManager em;
    
    //GET EMPLOYE PAR ID (PREPARED STMT)
    //MODIFIER EMPLOYES (PREPARED STATEMENT)
    //SUPPRIMER EMPLOYES (PREPARED STMT)
    //GET IDENTIFIANTS (SELECT ALL iDENTIFIANTS
    
    public Collection getEmployes(){
        Query q = em.createQuery("SELECT e from Employes e");
        return q.getResultList();
    }
    
    public Collection getEmployesId(){
        
    }
    
    public Collection getIdentifiants(){
        Query q = em.createQuery("SELECT i from Identifiants i");
        return q.getResultList();
    }
    
    public Collection modifierEmployes(){
        Query q = em.createQuery("");
        return q.getResultList();
    }
    
    public Collection supprimerEmployes(){
        Query q = em.createQuery("DELETE from Employes where id=?");
        return q.getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }

   
}
