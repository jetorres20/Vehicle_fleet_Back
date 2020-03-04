/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pablo Garzon
 */
public class ConductorDetailDTO extends ConductorDTO implements Serializable
{
    //private List<Franja>
    
    private List<AgendaDTO> agendas;

    private List<ReservaDTO> reservas;
    
    public ConductorDetailDTO()
    {
        super();
    }
    
    public ConductorDetailDTO(ConductorEntity conductor)
    {
        super(conductor);
        if (conductor != null) {
            agendas = new ArrayList<>();
            for (AgendaEntity entityAgenda : conductor.getAgendas()) {
                agendas.add(new AgendaDTO(entityAgenda));
            }
            reservas = new ArrayList();
            for (ReservaEntity entityReserva : conductor.getReservas()) {
                //conductor.add(new ReservaDTO());
            }
        }
    }
    
    @Override
    public ConductorEntity toEntity()
    {
        ConductorEntity conductorEntity = super.toEntity();
        if (agendas != null) {
            List<AgendaEntity> agendaEntity = new ArrayList<>();
            for (AgendaDTO dtoAgenda : agendas) {
                agendaEntity.add(dtoAgenda.toEntity());
            }
            conductorEntity.setAgendas(agendaEntity);
        }
        if (reservas != null) {
            List<ReservaEntity> reservaEntity = new ArrayList<>();
            for (ReservaDTO dtoReserva : reservas) {
                reservaEntity.add(dtoReserva.toEntity());
            }
            conductorEntity.setReservas(reservaEntity);
        }
        return conductorEntity;
    }
    
    /**
     * @return the agendas
     */
    public List<AgendaDTO> getAgendas() {
        return agendas;
    }

    /**
     * @param agendas the agendas to set
     */
    public void setAgendas(List<AgendaDTO> agendas) {
        this.agendas = agendas;
    }

    /**
     * @return the reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
}
