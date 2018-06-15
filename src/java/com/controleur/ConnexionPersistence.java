/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author esic
 */
@Stateless
public class ConnexionPersistence {

    @PersistenceContext(unitName = "EmployesServletJPAPU")
    private EntityManager em;
    
    //GET EMPLOYES (SELECT ALL EMPLOYES)
    //GET EMPLOYE PAR ID (PREPARED STMT)
    //MODIFIER EMPLOYES (PREPARED STATEMENT)
    //SUPPRIMER EMPLOYES (PREPARED STMT)
    //GET IDENTIFIANTS (SELECT ALL iDENTIFIANTS

    public void persist(Object object) {
        em.persist(object);
    }

   
}
