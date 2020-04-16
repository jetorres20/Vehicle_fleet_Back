/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Pablo Garzon
 */
public class AgendaDTO implements Serializable{
    
    private Long id;
    
    private Date fecha;
    
    private Boolean reservada;
    
    private ReservaDTO reserva;
    
    private ConductorDTO conductor;
    
    public AgendaDTO()
    {
        
    }
    
    public AgendaDTO(AgendaEntity ae)
    {
        if(ae != null)
        {
        this.fecha = ae.getFecha();
        this.reservada = ae.isReservada();
        this.id = ae.getId();
        if(ae.getReserva() != null)
            reserva = new ReservaDTO(ae.getReserva());
        else
            reserva = null;
        if(ae.getConductor() != null)
            conductor = new ConductorDTO(ae.getConductor());
        else
            conductor = null;
        }
    }
    
    public AgendaEntity toEntity()
    {
        AgendaEntity agenda = new AgendaEntity();
        agenda.setFecha(getFecha());
        agenda.setReservada(isReservada());
        agenda.setId(getId());
        if(getReserva()!=null)
            agenda.setReserva(reserva.toEntity());
        if(getConductor() != null)
            agenda.setConductor(getConductor().toEntity());
        return agenda;
    }

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
    public void setReservada(Boolean reservada) {
        this.reservada = reservada;
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
     * @return the conductor
     */
    public ConductorDTO getConductor() {
        return conductor;
    }

    /**
     * @param conductor the conductor to set
     */
    public void setConductor(ConductorDTO conductor) {
        this.conductor = conductor;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
