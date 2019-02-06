/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Autor;
import es.albarregas.beans.Libro;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author paco
 */
@WebServlet(name = "Eleccion", urlPatterns = {"/Eleccion"})
public class Eleccion extends HttpServlet {

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
        String url = null;

        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO gdao = daof.getGenericoDAO();
        HttpSession sesion = request.getSession();
        switch (request.getParameter("op")) {
            case "add":
                url = "JSP/insertar.jsp";
                break;
            case "delete":
                break;
            case "updateAutor":
                break;
            case "updateLibro":
                break;
            case "listAutores":
                Set<Autor> listadoAutores = gdao.get(Autor.class);
                sesion.setAttribute("listadoAutores", listadoAutores);
                url = "JSP/listarAutores.jsp";
                break;
            case "listLibros":
                Set<Libro> listadoLibros = gdao.get(Libro.class);
                sesion.setAttribute("listadoLibros", listadoLibros);
                url = "JSP/listarLibros.jsp";
                break;
        }

        switch (request.getParameter("op")) {
            case "delete":
                url = "JSP/eliminar.jsp";
                break;
            case "updateAutor":
                Set<Autor> listadoAutores = gdao.get(Autor.class);
                request.setAttribute("listadoAutores", listadoAutores);
                url = "JSP/actualAutor.jsp";
                break;
            case "updateLibro":
                Set<Libro> listadoLibros = gdao.get(Libro.class);
                request.setAttribute("listadoLibros", listadoLibros);
                url = "JSP/actualLibro.jsp";
                break;
        }

        request.getRequestDispatcher(url).forward(request, response);
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
