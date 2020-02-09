/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Nestor Plata
 */
@Entity
public class PracticaEntity extends BaseEntity implements Serializable{
    private String destino;
    private String descripcion;
    private double tiempoDeDesplazamiento;
    private double duracion;
     @javax.persistence.ManyToOne(
    )
    ProfesorEntity profesor;

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
    public double getTiempoDeDesplazamiento() {
        return tiempoDeDesplazamiento;
    }

    /**
     * @param tiempoDeDesplazamiento the tiempoDeDesplazamiento to set
     */
    public void setTiempoDeDesplazamiento(double tiempoDeDesplazamiento) {
        this.tiempoDeDesplazamiento = tiempoDeDesplazamiento;
    }

    /**
     * @return the duracion
     */
    public double getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
    
}
