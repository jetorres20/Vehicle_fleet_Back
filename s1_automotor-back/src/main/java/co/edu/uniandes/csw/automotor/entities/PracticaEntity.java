/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Nestor Plata
 */
@Entity
public class PracticaEntity extends BaseEntity implements Serializable{
    private String destino;
    private String descripcion;
    private Double tiempoDeDesplazamiento;
    private Double duracion;;
    @PodamExclude
     @javax.persistence.ManyToOne(
    )
    private ProfesorEntity profesor;
     @PodamExclude
      @javax.persistence.OneToOne(
    )
    private ReservaEntity reserva;

     
     
     
     
    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tiempoDeDesplazamiento
     */
    public Double getTiempoDeDesplazamiento() {
        return tiempoDeDesplazamiento;
    }

    /**
     * @param tiempoDeDesplazamiento the tiempoDeDesplazamiento to set
     */
    public void setTiempoDeDesplazamiento(Double tiempoDeDesplazamiento) {
        this.tiempoDeDesplazamiento = tiempoDeDesplazamiento;
    }

    /**
     * @return the duracion
     */
    public Double getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the reserva
     */
    public ReservaEntity getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }

    /**
     * @return the profesor
     */
    public ProfesorEntity getProfesor() {
        return profesor;
    }

    /**
     * @param profesor the profesor to set
     */
    public void setProfesor(ProfesorEntity profesor) {
        this.profesor = profesor;
    }
    
    
}
