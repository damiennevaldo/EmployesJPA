/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employes.modele;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import static com.employes.utils.EmployesConstantes.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jacques Augustin
 */
public class ConnexionDB {

    private String dbUrl;
    private String dbUser;
    private String dbPwd;
    private InputStream input;
    private  Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private Properties prop;
    private ClassLoader classLoader;

    public ConnexionDB() {

        classLoader = Thread.currentThread().getContextClassLoader();
        input = classLoader.getResourceAsStream(CHEMIN_FICHIER_PROPERTIES);

        prop = new Properties();

        try {
            prop.load(input);
            this.dbUrl = prop.getProperty(DB_URL);
            this.dbUser = prop.getProperty(DB_USER);
            this.dbPwd = prop.getProperty(DB_PWD);
            
            // Code ci-dessous uniquement Pour le serveur d'applications TomEE
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbUser);
       // } catch (IOException | SQLException | ClassNotFoundException ex) {
        } catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        } 

    }

    public Statement getStatementDB() {
        try {
            
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stmt;
    }

    public PreparedStatement getPreparedStatementDB(String req) {
        try {
            conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbUser);
            pstmt = conn.prepareStatement(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pstmt;
    }
}
