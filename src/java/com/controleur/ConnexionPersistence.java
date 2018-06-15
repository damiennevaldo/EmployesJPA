/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import com.model.Employes;
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
   
    //MODIFIER EMPLOYES (PREPARED STATEMENT) Objet Employes
  
    public Collection getEmployes(){
        Query q = em.createQuery("SELECT e from Employes e");
        return q.getResultList();
    }
    
    public Collection getEmployesId(int idEmp){
        Query q = em.createQuery("SELECT e from Employes e where e.id=:idEmp");
        q.setParameter("idEmp", idEmp);
        return q.getResultList();
    }
    
    public Collection getIdentifiants(){
        Query q = em.createQuery("SELECT i from Identifiants i");
        return q.getResultList();
    }
    
    public void modifierEmployes(Employes e){
        Employes emp = (Employes) em.find(Employes.class, e.getId());
        emp.setNom(e.getNom());
        emp.setPrenom(e.getPrenom());
        emp.setTeldom(e.getTeldom());
        emp.setTelport(e.getTelport());
        emp.setTelpro(e.getTelpro());
        emp.setAdresse(e.getAdresse());
        emp.setCodepostal(e.getCodepostal());
        emp.setVille(e.getVille());
        emp.setEmail(e.getEmail());
        em.persist(emp);        
    }

    public int supprimerEmployes(int idEmp){
        Query q = em.createQuery("DELETE from Employes e where e.id=:idEmp");
        q.setParameter("idEmp", idEmp);
        return q.executeUpdate();
    }

    public void persist(Object object) {
        em.persist(object);
    }

   
}
