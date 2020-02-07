/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Pablo Garzon
 */
@Entity
public class AgendaEntity extends BaseEntity implements Serializable{
    
    private Date fecha;
    
    private boolean reservada;

    @PodamExclude
    @OneToOne(
            
    )
    ConductorEntity conductor;
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the reservada
     */
    public boolean isReservada() {
        return reservada;
    }

    /**
     * @param reservada the reservada to set
     */
    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    /**
     * @return the condAsociado
     */
    public ConductorEntity getConductor() {
        return conductor;
    }

    /**
     * @param condAsociado the condAsociado to set
     */
    public void setConductor(ConductorEntity conductor) {
        this.conductor = conductor;
    }
}
