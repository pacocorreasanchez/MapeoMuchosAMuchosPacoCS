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
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author paco
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

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
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO gdao = daof.getGenericoDAO();

        String url = null;

        Autor autor = new Autor();
        Libro libro = new Libro();

        switch (request.getParameter("op")) {
            case "add":
                Set<Libro> libros = new HashSet<>();
                autor.setNombre(request.getParameter("nombre"));
                if (request.getParameter("libro1") != null && request.getParameter("libro1").length() > 0) {
                    libro.setTitulo(request.getParameter("libro1"));
                    libro.getAutores().add(autor);
                    libros.add(libro);
                }

                if (request.getParameter("libro2") != null && request.getParameter("libro2").length() > 0) {
                    libro = new Libro();
                    libro.setTitulo(request.getParameter("libro2"));
                    libro.getAutores().add(autor);
                    libros.add(libro);
                }

                if (request.getParameter("libro3") != null && request.getParameter("libro3").length() > 0) {
                    libro = new Libro();
                    libro.setTitulo(request.getParameter("libro3"));
                    libro.getAutores().add(autor);
                    libros.add(libro);
                }

                autor.setLibros(libros);
                gdao.insertOrUpdate(autor);
                url = "index.jsp";
                break;
            case "delete":
                autor = (Autor) gdao.getById(Integer.parseInt(request.getParameter("registro")), Autor.class);
                gdao.delete(autor);
                url = "index.jsp";
                break;
            case "updateAutor":
                autor = (Autor) gdao.getById(Integer.parseInt(request.getParameter("registro")), Autor.class);
                Set<Libro> listadoLibros = gdao.get(Libro.class);
                Set<Libro> librosPropios = new HashSet();
                Set<Autor> autores = null;
                
                for(Libro l : listadoLibros){//recorremos la lista de los libros
                    autores = l.getAutores();//para el Set de autores, lo igualamos al Set de autores del bean Libro
                    for(Autor a : autores){//recorremos la lista de autores
                        if(autor.getIdAutor() == a.getIdAutor()){//si el idAutor es igual al idAutor del libro cogido, lo metemos en el Set libros propios para después borrarlo
                            librosPropios.add(l);//añadimos a la lista librosPropios todos los libros de ese autor
                        }
                    }
                }
                
                
                for(Libro l : librosPropios){//recorremos la lista de librosPropios
                    listadoLibros.remove(l);//Eliminamos del Set listadoLibros, los libros propios del autor
                }
                request.setAttribute("autor",autor);
                request.setAttribute("listadoLibros",listadoLibros);
                url = "JSP/finActualizarAutor.jsp";
                break;
            case "updateLibro":
                libro = (Libro) gdao.getById(Integer.parseInt(request.getParameter("registro")), Libro.class);
                Set<Autor> listadoAutores = gdao.get(Autor.class);
                Set<Autor> autoresPropios = new HashSet();
                Set<Libro> libros1 = null;
                
                for(Autor a : listadoAutores){
                    libros1 = a.getLibros();
                    for(Libro l : libros1){
                        if(libro.getIdLibro()== l.getIdLibro()){
                            autoresPropios.add(a);
                        }
                    }
                }
                for(Autor a : autoresPropios){
                    listadoAutores.remove(a);
                }
                request.setAttribute("libro", libro);
                request.setAttribute("listadoAutores", listadoAutores);
                url = "JSP/finActualizarLibro.jsp";
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
