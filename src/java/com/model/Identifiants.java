/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author esic
 */
@Entity
@Table(name = "IDENTIFIANTS")
@NamedQueries({
    @NamedQuery(name = "Identifiants.findAll", query = "SELECT i FROM Identifiants i")})
public class Identifiants implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 32)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 32)
    @Column(name = "MDP")
    private String mdp;

    public Identifiants() {
    }

    public Identifiants(Integer id2) {
        this.id = id2;
    }

    public Integer getId2() {
        return id;
    }

    public void setId2(Integer id2) {
        this.id = id2;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identifiants)) {
            return false;
        }
        Identifiants other = (Identifiants) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.controleur.Identifiants[ id2=" + id + " ]";
    }
    
}
