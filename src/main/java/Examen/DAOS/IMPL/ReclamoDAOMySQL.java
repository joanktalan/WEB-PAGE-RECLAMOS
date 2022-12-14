/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examen.DAOS.IMPL;

import Examen.DAOS.ReclamoDAO;
import Examen.DTOS.PersonaDTO;
import Examen.Otros.Categoria;
import Examen.Otros.Domicilio;
import Examen.DTOS.ReclamoDTO;
import Examen.Modelo.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import Examen.DTOS.AdministradorDTO;
import Examen.DTOS.ContribuyenteDTO;
import java.time.LocalDate;

/**
 *
 * @author Joancito
 */
public class ReclamoDAOMySQL implements ReclamoDAO {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/usuariosyreclamos?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "root";

    

    @Override
    public Collection<ReclamoDTO> obtenerReclamos(PersonaDTO persona) {
        Collection<ReclamoDTO> reclamos = new ArrayList<ReclamoDTO>();
        try {

            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(persona.getSQL());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                LocalDate fechaSeCreo = rs.getDate(2).toLocalDate();
                
                LocalDate fechaSeResolvio = null;
                
                if(rs.getDate(3)!=null){
                    fechaSeResolvio = rs.getDate(3).toLocalDate();
                }
                
               
                Categoria categoria = Categoria.valueOf(rs.getString(4).toUpperCase());

                String calle = rs.getString(5);
                int altura = rs.getInt(6);
                Domicilio domicilio = new Domicilio(calle, altura);

                String descripcion = rs.getString(7);

                int idUsuario = rs.getInt(8);

                ReclamoDTO reclamo = new ReclamoDTO(id, fechaSeCreo, fechaSeResolvio, categoria, domicilio, descripcion, idUsuario);
                reclamos.add(reclamo);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al obtener reclamo", ex);
        }
        return reclamos;

    }
    
    @Override
    public ArrayList<ReclamoDTO> obtenerReclamosArray(PersonaDTO persona) {
        ArrayList<ReclamoDTO> reclamos = new ArrayList<ReclamoDTO>();
        try {

            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(persona.getSQL());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                LocalDate fechaSeCreo = rs.getDate(2).toLocalDate();
                
                LocalDate fechaSeResolvio = null;
                
                if(rs.getDate(3)!=null){
                    fechaSeResolvio = rs.getDate(3).toLocalDate();
                }
                
               
                Categoria categoria = Categoria.valueOf(rs.getString(4).toUpperCase());

                String calle = rs.getString(5);
                int altura = rs.getInt(6);
                Domicilio domicilio = new Domicilio(calle, altura);

                String descripcion = rs.getString(7);

                int idUsuario = rs.getInt(8);

                ReclamoDTO reclamo = new ReclamoDTO(id, fechaSeCreo, fechaSeResolvio, categoria, domicilio, descripcion, idUsuario);
                reclamos.add(reclamo);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al obtener reclamo", ex);
        }
        return reclamos;

    }

    @Override
    public void agregarReclamo(ReclamoDTO reclamo) {
        
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
            
            
//            PreparedStatement ps = con.prepareStatement("INSERT INTO `usuariosyreclamos`.`reclamos"
//                    + "` (`fechaSeCreo`, `categoria`, `calle`,`altura`,`descripcion`,`personaid1`)"
//                    + " VALUES ('"+ reclamo.getFechaSeCreo() +"', '"+reclamo.getCategoria()+"', '"+reclamo.getInmueble().getCalle()+"'"
//                            + ", '" + reclamo.getInmueble().getAltura() + "', '" + reclamo.getDescripcion() + "', '" + reclamo.getIdUsuario() + "')");

            PreparedStatement ps = con.prepareStatement("INSERT INTO `usuariosyreclamos`.`reclamos"
                    + "` (`fechaSeCreo`, `categoria`, `calle`,`altura`,`personaid1`)"
                    + " VALUES ('"+ reclamo.getFechaSeCreo() +"', '"+reclamo.getCategoria()+"', '"+reclamo.getInmueble().getCalle()+"'"
                            + ", '" + reclamo.getInmueble().getAltura() + "', '" + reclamo.getIdUsuario() + "')");
    
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al obtener reclamo", ex);
        }
    }
    
    @Override
    public void borrarReclamo(int numReclamo) {
        
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
            
            
//            PreparedStatement ps = con.prepareStatement("INSERT INTO `usuariosyreclamos`.`reclamos"
//                    + "` (`fechaSeCreo`, `categoria`, `calle`,`altura`,`descripcion`,`personaid1`)"
//                    + " VALUES ('"+ reclamo.getFechaSeCreo() +"', '"+reclamo.getCategoria()+"', '"+reclamo.getInmueble().getCalle()+"'"
//                            + ", '" + reclamo.getInmueble().getAltura() + "', '" + reclamo.getDescripcion() + "', '" + reclamo.getIdUsuario() + "')");

            PreparedStatement ps = con.prepareStatement("DELETE FROM `usuariosyreclamos`.`reclamos` WHERE  `reclamoid`= "+ numReclamo);
            
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al obtener reclamo", ex);
        }
    }

    @Override
    public void modificarReclamo(ReclamoDTO reclamo) {
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
            
            
            if(reclamo.getCategoria()!=null){
                PreparedStatement actualizarCategoria = con.prepareStatement("UPDATE `usuariosyreclamos`.`reclamos`"
                        + " SET `categoria`='" + reclamo.getCategoria() + "' WHERE  `reclamoid`=" + reclamo.getId());
                actualizarCategoria.executeUpdate();
            }
            
            if(!reclamo.getInmueble().getCalle().isBlank()){
                PreparedStatement actualizarCalle = con.prepareStatement("UPDATE `usuariosyreclamos`.`reclamos`"
                        + " SET `calle`='" + reclamo.getInmueble().getCalle() + "' WHERE  `reclamoid`=" + reclamo.getId());
                actualizarCalle.executeUpdate();
            }
            
            if(reclamo.getInmueble().getAltura()!=0){
                PreparedStatement actualizarAltura = con.prepareStatement("UPDATE `usuariosyreclamos`.`reclamos`"
                        + " SET `altura`=" +  reclamo.getInmueble().getAltura() +" WHERE  `reclamoid`="+reclamo.getId());
                actualizarAltura.executeUpdate();
            }
            
            

        } catch (SQLException ex) {
            throw new RuntimeException("Error al modificar reclamo", ex);
        }
    }

    @Override
    public void resolverReclamo(int numReclamo) {
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
            
            PreparedStatement actualizarCategoria = con.prepareStatement("UPDATE `usuariosyreclamos`.`reclamos` "
                    + "SET `fechaSeResolvio` = '" +  LocalDate.now() + "' WHERE (`reclamoid` = '" + numReclamo + "');");
                actualizarCategoria.executeUpdate();
            
            

        } catch (SQLException ex) {
            throw new RuntimeException("Error al resolver reclamo", ex);
        }
    }
}
    
    


    


    
    
//CODIGO EXTRA
//@Override
//    public Collection<ReclamoDTO>obtenerReclamos()  {
//        Collection<ReclamoDTO> reclamos = new ArrayList<ReclamoDTO>();
//        try {
//            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
//            
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM reclamos");
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()) {
//                
//                //aca completar con los datos de Reclamo
//                
//                int cod = rs.getInt(1);
//                
//                LocalDate fechaSeCreo = rs.getDate(2).toLocalDate();
//                
//                LocalDate fechaSeResolvio = rs.getDate(3).toLocalDate();
//                
//                Categoria categoria = Categoria.valueOf(rs.getString(4).toUpperCase());
//                
//                String calle = rs.getString(5);
//                
//                int altura = rs.getInt(6);
//                
//                Domicilio domicilio = new Domicilio(calle, altura);
//                
//                String descripcion =rs.getString(7);
//                
//                int idUsuario=rs.getInt(8);
//               
//                ReclamoDTO reclamo = new ReclamoDTO(cod, fechaSeCreo, fechaSeResolvio, categoria, domicilio, descripcion,idUsuario);
//                reclamos.add(reclamo);
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error al obtener reclamo", ex);
//        }
//        return reclamos;
//    }