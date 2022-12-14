/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Examen.Servlets.Logins;

import Examen.DAOS.IMPL.LoginDAOHardCode;
import Examen.DAOS.IMPL.LoginDAOMySQL;
import Examen.DAOS.IMPL.PersonaDAOMySQL;
import Examen.DTOS.AdministradorDTO;
import Examen.DTOS.LoginDTO;
import Examen.DTOS.PersonaDTO;
import Examen.Modelo.Modelo;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alumno
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"parcial2"})
public class LoginServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Modelo model = new Modelo(new PersonaDAOMySQL(), new LoginDAOMySQL());

        String usuario = request.getParameter("user");

        String contrasenia = request.getParameter("contrasenia");

        PersonaDTO persona = model.obtenerPersona(usuario, contrasenia);

        if (persona != null) {

            //cargando el login
            LoginDTO login = new LoginDTO(persona.getId(), LocalDate.now(), LocalTime.now());
            model.cargarLogin(login);                    

            //Guardandando y seteando los datos y configuracion del usuario
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(600);
            request.getSession().setAttribute("usuario", persona);
            request.getSession().setAttribute("sesionIniciada", true);
             request.getSession().setAttribute("tieneNavbar", persona.tieneNavbar());
             request.getSession().setAttribute("nomUsuario", persona.getUsuario());
            
               
            

            String haciaDondeIba = request.getParameter("deDondeViene");
            response.sendRedirect(request.getContextPath() + haciaDondeIba);
        } else {
            RequestDispatcher vista = request.getRequestDispatcher("WEB-INF/views/page401.jsp");
            vista.forward(request, response);

        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


