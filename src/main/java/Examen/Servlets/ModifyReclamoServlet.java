/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Examen.Servlets;

import Examen.DAOS.IMPL.PersonaDAOMySQL;
import Examen.DAOS.IMPL.ReclamoDAOMySQL;
import Examen.DTOS.LoginDTO;
import Examen.DTOS.PersonaDTO;
import Examen.DTOS.ReclamoDTO;
import Examen.Modelo.Modelo;
import Examen.Otros.Categoria;
import Examen.Otros.Domicilio;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joancito
 */
@WebServlet(name = "ModifyReclamoServlet", urlPatterns = {"/reclamos/modify"})
public class ModifyReclamoServlet extends HttpServlet {

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
        
            Modelo model = new Modelo(new PersonaDAOMySQL(), new ReclamoDAOMySQL());

            PersonaDTO persona = (PersonaDTO) request.getSession().getAttribute("usuario");
            ArrayList<ReclamoDTO> reclamos =  model.obtenerReclamosArray(persona);

            request.setAttribute("reclamos", reclamos);
        
        
            RequestDispatcher vista = request.getRequestDispatcher("/WEB-INF/pages/modifyReclamo.jsp");
            vista.forward(request, response);
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
        
        Modelo model = new Modelo(new ReclamoDAOMySQL());
        
         //Categoria
        String catego = request.getParameter("categoria");
        Categoria categoria = Categoria.valueOf(catego.toUpperCase().trim());
        
        //Domicilio
        
        String calle = (String) request.getParameter("calle");
        int altura = Integer.parseInt(request.getParameter("altura"));
        Domicilio domicilio = new Domicilio(calle,altura);
        
        //Id
        //int id = Integer.parseInt(request.getParameter("boton"));
        int id=4;
        
        
        //Se añade el reclamo
        ReclamoDTO reclamo = new ReclamoDTO(4,categoria,domicilio);
        model.modificarReclamo(reclamo);

        
        
                //Vísta de la Pagina
                doGet(request, response);
        
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
