/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examen.Modelo;

import Examen.DAOS.PersonaDAO;
import Examen.DAOS.ReclamoDAO;
import Examen.DAOS.IMPL.PersonaDAOMySQL;
import Examen.DAOS.IMPL.ReclamoDAOMySQL;
import Examen.DAOS.LoginDAO;
import Examen.DTOS.LoginDTO;
import Examen.DTOS.PersonaDTO;
import Examen.DTOS.ReclamoDTO;
import Examen.Otros.Categoria;
import Examen.Otros.Domicilio;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Joan
 */
public class Modelo {

    private PersonaDAO personaDAO;
    private ReclamoDAO reclamoDAO;
    private LoginDAO loginDAO;

    public Modelo() {
    }

    public Modelo(PersonaDAO personaDAO, ReclamoDAO reclamoDAO) {
        this.personaDAO = personaDAO;
        this.reclamoDAO = reclamoDAO;
    }

    public Modelo(PersonaDAO personaDAO, LoginDAO loginDAO) {
        this.personaDAO = personaDAO;
        this.loginDAO = loginDAO;
    }

    public Modelo(PersonaDAO personaDAO, ReclamoDAO reclamoDAO, LoginDAO loginDAO) {
        this.personaDAO = personaDAO;
        this.reclamoDAO = reclamoDAO;
        this.loginDAO = loginDAO;
    }

    public Modelo(ReclamoDAO reclamoDAO) {
        this.reclamoDAO = reclamoDAO;
    }

    public Modelo(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }
    
    

    //RECLAMOS

    public Collection<ReclamoDTO> obtenerReclamos(PersonaDTO persona) {
        return reclamoDAO.obtenerReclamos(persona);
    }
    
    public ArrayList<ReclamoDTO> obtenerReclamosArray(PersonaDTO persona) {
        return reclamoDAO.obtenerReclamosArray(persona);
    }
    
    public void añadirReclamo(ReclamoDTO reclamo){
        reclamoDAO.agregarReclamo(reclamo);
    }
    
    public void borrarReclamo(int numReclamo){
        reclamoDAO.borrarReclamo(numReclamo);
    }
    
    public void modificarReclamo(ReclamoDTO reclamo){
        reclamoDAO.modificarReclamo(reclamo);
    }
    
    public void resolverReclamo(int numReclamo){
        reclamoDAO.resolverReclamo(numReclamo);
    }
    
    //PERSONAS
    public PersonaDTO obtenerPersona(String usuario, String contraseña) {
        return personaDAO.obtenerPersona(usuario, contraseña);
    }

    public boolean IdExiste(int id) {
        return personaDAO.IdExiste(id);
    }
    
    public void añadirUsuario(PersonaDTO usuarioNuevo){
       personaDAO.añadirUsuario(usuarioNuevo);
    }
    
    public String parametrosCorrectos(String nombreUsuario, String dni, String mail, String telefono){
        return personaDAO.parametrosCorrectos(nombreUsuario, dni, mail, telefono);
    }
    
    public boolean usuarioExiste(String usuario){
        return personaDAO.usuarioExiste(usuario);
    }
    
    public boolean telefonoExiste(String telefono){
        return personaDAO.telefonoExiste(telefono);
    }

    //LOGINS
    public Collection<LoginDTO> obtenerLogins(int id) {
        return loginDAO.obtenerLogins(id);
    }

    public void cargarLogin(LoginDTO login) {
        loginDAO.cargarLogin(login);
    }
    
    

   
}








//CODIGO EXTRA PARA EL FUTURO

 //RECLAMOS

//    public Collection<ReclamoDTO> obtenerReclamos() {
//        return reclamoDAO.obtenerReclamos();
//    }

//PERSONAS

//    public Collection<PersonaDTO> obtenerPersonas(){
//        return personaDAO.obtenerPersonas();
//    }
        
//
//    public boolean contraseñaExiste(String contrasenia,String usuario) {
//        return personaDAO.contraseñaExiste(contrasenia,usuario);
//    }

 //    public String agregarReclamo(int idReclamo, Date fechaSeCreo, Date fechaSeResolvio, Categoria categoria, Domicilio inmueble, String descripcion,int idUsuario){
//    return personaDAO.agregarReclamo(idReclamo,fechaSeCreo,fechaSeResolvio,categoria,inmueble,descripcion,idUsuario);
//    }
//     public PersonaDTO obtenerPersona(int personaID){
//        return personaDAO.obtenerPersona(personaID);
//    }