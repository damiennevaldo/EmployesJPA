/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employes.modele;

import com.employes.modele.ConnexionDB;
import com.employes.utils.EmployesConstantes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jacques Augustin
 */
public class UtilisateurPersistance {

    private ConnexionDB connexion;
    private String req;
    private ResultSet rs;
    private Statement stmt;

    public ResultSet getUtilisateurs() {
        connexion = new ConnexionDB();
        req = EmployesConstantes.SQL_SELECT_IDENTIFIANTS;
        stmt = connexion.getStatementDB();

        try {
            rs = stmt.executeQuery(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
}
