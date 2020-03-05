/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.EncuestaEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Esteban Torres
 */
public class EncuestaDTO implements Serializable {

    private ReservaDTO reserva;
    private ProfesorDTO profesor;
    private Long id;
    private Integer calificacion;
    private String comentario;

    public EncuestaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param encuestaEntity: Es la entidad que se va a convertir a DTO
     */
    public EncuestaDTO(EncuestaEntity encuestaEntity) {
        if (encuestaEntity.getReserva() != null) {
            this.reserva = new ReservaDTO(encuestaEntity.getReserva());
        } else {
            reserva = null;
        }
        
        if (encuestaEntity.getProfesor() != null) {
            this.profesor = new ProfesorDTO(encuestaEntity.getProfesor());
        } else {
            profesor = null;
        }

        id = encuestaEntity.getId();
        calificacion = encuestaEntity.getCalificacion();
        comentario = encuestaEntity.getComentario();
    }

    /**
     * @return the reserva
     */
    public ReservaDTO getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(ReservaDTO reserva) {
        this.reserva = reserva;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public EncuestaEntity toEntity() {
        EncuestaEntity encuestaEntity = new EncuestaEntity();
        encuestaEntity.setCalificacion(this.getCalificacion());
        encuestaEntity.setComentario(this.getComentario());
        encuestaEntity.setId(this.getId());
        return encuestaEntity;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the profesor
     */
    public ProfesorDTO getProfesor() {
        return profesor;
    }

    /**
     * @param profesor the profesor to set
     */
    public void setProfesor(ProfesorDTO profesor) {
        this.profesor = profesor;
    }

}
