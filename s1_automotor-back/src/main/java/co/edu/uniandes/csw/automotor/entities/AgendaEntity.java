/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Pablo Garzon
 */
@Entity
public class AgendaEntity extends BaseEntity implements Serializable{

    
    private Date fecha;
    
    private Boolean reservada;
    
    private Integer duracionEnMin;

    @PodamExclude
    @OneToOne(
            
    )
    ConductorEntity conductor;
    @PodamExclude
    @OneToMany(mappedBy = "agenda")
    Collection<ReservaEntity> reservas;
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
    public Boolean isReservada() {
        return reservada;
    }

    /**
     * @param reservada the reservada to set
     */
    public void setReservada(Boolean reservada) {
        this.reservada = reservada;
    }

    /**
     * @return the condAsociado
     */
    public ConductorEntity getConductor() {
        return conductor;
    }

    /**
     * @param conductor the condAsociado to set
     */
    public void setConductor(ConductorEntity conductor) {
        this.conductor = conductor;
    }
    
        /**
     * @return the reservas
     */
    public Collection<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(Collection<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    /**
     * @return the duracionEnMin
     */
    public Integer getDuracionEnMin() {
        return duracionEnMin;
    }

    /**
     * @param duracionEnMin the duracionEnMin to set
     */
    public void setDuracionEnMin(Integer duracionEnMin) {
        this.duracionEnMin = duracionEnMin;
    }
}
