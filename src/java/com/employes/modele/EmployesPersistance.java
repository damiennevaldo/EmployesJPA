/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employes.modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static com.employes.utils.EmployesConstantes.*;
import java.sql.PreparedStatement;

/**
 *
 * @author Jacques Augustin
 */
public class EmployesPersistance {

    private ResultSet rs;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ArrayList<EmployeBean> listeEmployes;
    private EmployeBean employe;
    private ConnexionDB conn;

    public ResultSet getTousLesEmployes() {
        conn = new ConnexionDB();
        rs = null;
        stmt = conn.getStatementDB();

        try {
            rs = stmt.executeQuery(SQL_SELECT_TOUS_EMPLOYES);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }

    public ResultSet getResultSetEmployeParId(int id) {
        conn = new ConnexionDB();
        String req = SQL_SELECT_EMPLOYES_ID + id;
        rs = null;
        stmt = conn.getStatementDB();

        try {
            rs = stmt.executeQuery(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }

    public void supprimerEmployeParId(int id) {
        conn = new ConnexionDB();
        String req = SQL_DELETE_EMPLOYES + id;

        stmt = conn.getStatementDB();

        try {
            stmt.execute(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<EmployeBean> getListeEmployes() {
        listeEmployes = new ArrayList<>();

        //Récupération des employés
        rs = this.getTousLesEmployes();

        try {
            while (rs.next()) {
                employe = new EmployeBean();
                employe.setId(rs.getString("ID"));
                employe.setNom(rs.getString("NOM"));
                employe.setPrenom(rs.getString("PRENOM"));
                employe.setTelDomicile(rs.getString("TELDOM"));
                employe.setTelPortable(rs.getString("TELPORT"));
                employe.setTelPro(rs.getString("TELPRO"));
                employe.setAdresse(rs.getString("ADRESSE"));
                employe.setCodePostal(rs.getString("CODEPOSTAL"));
                employe.setVille(rs.getString("VILLE"));
                employe.setEmail(rs.getString("EMAIL"));

                listeEmployes.add(employe);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeEmployes;
    }

    public EmployeBean getEmployeParId(int idEmploye) {
        employe = new EmployeBean();

        //Récupération des employés
        rs = this.getResultSetEmployeParId(idEmploye);

        try {
            while (rs.next()) {

                employe.setId(rs.getString("ID"));
                employe.setNom(rs.getString("NOM"));
                employe.setPrenom(rs.getString("PRENOM"));
                employe.setTelDomicile(rs.getString("TELDOM"));
                employe.setTelPortable(rs.getString("TELPORT"));
                employe.setTelPro(rs.getString("TELPRO"));
                employe.setAdresse(rs.getString("ADRESSE"));
                employe.setCodePostal(rs.getString("CODEPOSTAL"));
                employe.setVille(rs.getString("VILLE"));
                employe.setEmail(rs.getString("EMAIL"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return employe;
    }

    public void modifierEmploye(EmployeBean eb) {

        conn = new ConnexionDB();

        String modifEmploye = "UPDATE EMPLOYES "
                + "SET NOM = ?, PRENOM=?, "
                + "TELDOM=?, TELPORT=?, "
                + "TELPRO=?, ADRESSE=?,"
                + "CODEPOSTAL=?, VILLE=?,EMAIL=? "
                + "where"
                + " ID=?";

        try {
            pstmt = conn.getPreparedStatementDB(modifEmploye);

            pstmt.setString(1, eb.getNom());
            pstmt.setString(2, eb.getPrenom());
            pstmt.setString(3, eb.getTelDomicile());
            pstmt.setString(4, eb.getTelPortable());
            pstmt.setString(5, eb.getTelPro());
            pstmt.setString(6, eb.getAdresse());
            pstmt.setString(7, eb.getCodePostal());
            pstmt.setString(8, eb.getVille());
            pstmt.setString(9, eb.getEmail());
            pstmt.setString(10, eb.getId());

            int maj = pstmt.executeUpdate();
            System.out.println(maj);

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

    }

}
