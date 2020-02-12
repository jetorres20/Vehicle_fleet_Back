/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author Pablo Garzon
 */
@Entity
public class ConductorEntity extends BaseEntity implements Serializable{
    
    private String name;

    @PodamExclude
    @OneToOne(mappedBy = "conductor")
    AgendaEntity agenda;
    @OneToMany(mappedBy = "conductor")
    private Collection<FranjaHorariaSemanalEntity> franjasHorariasSemanales;
    @OneToMany(mappedBy = "conductor")
    private Collection<ReservaEntity> reservas;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the agendaAs
     */
    public AgendaEntity getAgenda() {
        return agenda;
    }

    /**
     * @param agendaAs the agendaAs to set
     */
    public void setAgenda(AgendaEntity agenda) {
        this.agenda = agenda;
    }

    /**
     * @return the franjasHorariasSemanales
     */
    public Collection<FranjaHorariaSemanalEntity> getFranjasHorariasSemanales() {
        return franjasHorariasSemanales;
    }

    /**
     * @param franjasHorariasSemanales the franjasHorariasSemanales to set
     */
    public void setFranjasHorariasSemanales(Collection<FranjaHorariaSemanalEntity> franjasHorariasSemanales) {
        this.franjasHorariasSemanales = franjasHorariasSemanales;
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

   
}
