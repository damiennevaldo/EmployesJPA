/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import com.employes.utils.EmployesConstantes;
import com.model.Employes;
import com.model.Identifiants;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jacques
 */
public class Controleur extends HttpServlet {

    @EJB
    private ConnexionPersistence connexionPersistence;

    ArrayList<Employes> listeEmployes;
    ArrayList<Identifiants> listeIdentifiants = new ArrayList<>();
    Employes employe;
    String idEmploye = EmployesConstantes.FRM_ID_EMPL_SELECT;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String loginForm = request.getParameter(EmployesConstantes.FRM_LOGIN);
        String mdpForm = request.getParameter(EmployesConstantes.FRM_MDP);
        String action = request.getParameter(EmployesConstantes.ACTION);

        if (action == null) {
            request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);

        } else if (action != null) {

            switch (action) {

                case EmployesConstantes.ACTION_LOGIN:
                    //Si le nom d'utilisateur et le mot de passe sont vide, renvoyer vers l'index
                    //avec un message d'erreur.
                    if (loginForm != null && mdpForm != null) {

                        if (loginForm.isEmpty() || mdpForm.isEmpty()) {
                            request.setAttribute("cleMessageErreur", EmployesConstantes.ERREUR_SAISIE_VIDE);
                            request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);
                        } else {
                            listeIdentifiants.addAll(connexionPersistence.getIdentifiants());
                            String ad = "test";
//                            for (Identifiants i : listeIdentifiants) {
                            if (ad == "test") {
                                System.out.println("OK");
//                                if (i.getLogin().equals(loginForm) && i.getMdp().equals(mdpForm)) {
//                                    System.out.println(listeEmployes.get(0).getCodepostal());
//                                    listeEmployes.addAll(connexionPersistence.getEmployes());
//                                    System.out.println(listeEmployes.get(0).getAdresse());
//                                    request.setAttribute("cleListeEmployes", listeEmployes);
//                                    System.out.println(listeEmployes.get(0).getEmail());
//                                    request.getRequestDispatcher(EmployesConstantes.PAGE_TOUS_LES_EMPLOYES).forward(request, response);
                            } else {

                                request.setAttribute("cleMessageErreur", EmployesConstantes.ERREUR_INFOS_CONN_KO);
                                request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);
                            }
                        }
//                        }
                    }

                case EmployesConstantes.ACTION_SUPPRIMER:
                    if (request.getParameter(idEmploye) != null) {
                        int idClientASupprimer = Integer.parseInt(request.getParameter(idEmploye));
                        connexionPersistence.supprimerEmployes(idClientASupprimer);
                        listeEmployes.addAll(connexionPersistence.getEmployes());
                        request.setAttribute("cleListeEmployes", listeEmployes);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_TOUS_LES_EMPLOYES).forward(request, response);
                    }

                case EmployesConstantes.ACTION_MODIFIER:
                    employe = (Employes) session.getAttribute("employe");
                    employe.setAdresse(request.getParameter(EmployesConstantes.CHAMP_ADRESSE));
                    employe.setCodepostal(request.getParameter(EmployesConstantes.CHAMP_CODEPOSTAL));
                    employe.setEmail(request.getParameter(EmployesConstantes.CHAMP_EMAIL));
                    employe.setNom(request.getParameter(EmployesConstantes.CHAMP_NOM));
                    employe.setPrenom(request.getParameter(EmployesConstantes.CHAMP_PRENOM));
                    employe.setTeldom(request.getParameter(EmployesConstantes.CHAMP_TELDOMICILE));
                    employe.setTelport(request.getParameter(EmployesConstantes.CHAMP_TELPORTABLE));
                    employe.setTelpro(request.getParameter(EmployesConstantes.CHAMP_TELPRO));
                    employe.setVille(request.getParameter(EmployesConstantes.CHAMP_VILLE));

                    connexionPersistence.modifierEmployes(employe);
                    listeEmployes.addAll(connexionPersistence.getEmployes());
                    request.setAttribute("cleListeEmployes", listeEmployes);
                    request.getRequestDispatcher(EmployesConstantes.PAGE_TOUS_LES_EMPLOYES).forward(request, response);

                case EmployesConstantes.ACTION_DETAILS:
                    if (request.getParameter(idEmploye) != null) {
                        int idEmployeSelect = Integer.parseInt(request.getParameter(idEmploye));
                        listeEmployes.addAll(connexionPersistence.getEmployesId(idEmployeSelect));
                        employe = listeEmployes.get(0);
                        session.setAttribute("employe", employe);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_DETAIL_EMPLOYE).forward(request, response);

                    }

                case EmployesConstantes.ACTION_VOIR_LISTE:
                    listeEmployes.addAll(connexionPersistence.getEmployes());
                    request.setAttribute("cleListeEmployes", listeEmployes);
                    request.getRequestDispatcher(EmployesConstantes.PAGE_TOUS_LES_EMPLOYES).forward(request, response);

            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
