package Examen.DTOS;

import Examen.Otros.Domicilio;
import Examen.Otros.Categoria;

import java.time.LocalDate;

public class ReclamoDTO {

    private int id;

    private LocalDate fechaSeCreo;

    private LocalDate fechaSeResolvio;

    private Categoria categoria;

    private Domicilio inmueble;
    
    private String descripcion;
    
    private int idUsuario;

    public ReclamoDTO(int id, LocalDate fechaSeCreo, LocalDate fechaSeResolvio, Categoria categoria, Domicilio inmueble, String descripcion,int idUsuario) {
        this.id = id;
        this.fechaSeCreo = fechaSeCreo;
        this.fechaSeResolvio = fechaSeResolvio;
        this.categoria = categoria;
        this.inmueble = inmueble;
        this.descripcion = descripcion;
        this.idUsuario=idUsuario;
    }

    public LocalDate getFechaSeCreo() {
        return fechaSeCreo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Domicilio getInmueble() {
        return inmueble;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    @Override
    public String toString() {
        return "" +fechaSeCreo + " " + categoria + " "+ inmueble;
    }

    public int getId() {
        return id;
    }

    
    
    
    
    
}