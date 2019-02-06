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

/**
 *
 * @author paco
 */
@WebServlet(name = "Conclusion", urlPatterns = {"/Conclusion"})
public class Conclusion extends HttpServlet {

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
        Autor autor = new Autor();
        Libro libro = new Libro();
        String[] librosAdd = null;
        String[] autoresAdd = null;
        
        switch (request.getParameter("op")) {
            case "updateAutor":
                autor = (Autor) gdao.getById(Integer.parseInt(request.getParameter("idAutor")),Autor.class);
                
                Set<Libro> librosNuevos = autor.getLibros();
                librosAdd = request.getParameterValues("registro");
                
                for(String l : librosAdd){
                    libro = (Libro) gdao.getById(Integer.parseInt(l),Libro.class);
                    
                    Set<Autor> autoresLibrosNuevos = libro.getAutores();
                    autoresLibrosNuevos.add(autor);
                    libro.setAutores(autoresLibrosNuevos);
                    
                    gdao.insertOrUpdate(libro);
                    librosNuevos.add(libro);
                }
                
                autor.setLibros(librosNuevos);
                
                gdao.insertOrUpdate(autor);
                url = "index.jsp";
                break;
            case "updateLibro":
                libro = (Libro) gdao.getById(Integer.parseInt(request.getParameter("idLibro")), Libro.class);
                
                Set<Autor> autoresNuevos = libro.getAutores();
                autoresAdd = request.getParameterValues("registro");
                
                for(String a : autoresAdd){
                    autor = (Autor) gdao.getById(Integer.parseInt(a), Autor.class);
                    
                    Set<Libro> librosAutoresNuevos = autor.getLibros();
                    librosAutoresNuevos.add(libro);
                    autor.setLibros(librosAutoresNuevos);
                    
                    gdao.insertOrUpdate(autor);
                    autoresNuevos.add(autor);
                }
                
                libro.setAutores(autoresNuevos);
                
                gdao.insertOrUpdate(libro);
                url = "index.jsp";
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
