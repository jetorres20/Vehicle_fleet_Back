/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.adapters.DateAdapter;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Mateo Laguna G.
 */
public class ReservaDTO implements Serializable{
    
    private Long id;
    private Boolean estadoValidacion;
    private Boolean reservaRecurrente;
    private Boolean cancelada;
    private String motivoCancelacion;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaReserva;
    private Date fechaServicio;
    
    /*
    * Relación a un vehiculo  
    * dado que esta tiene cardinalidad 1.
     */
    private VehiculoDTO vehiculo;
    
    /*
    * Relación a una encuesta 
    * dado que esta tiene cardinalidad 0..1.
     */
    private EncuestaDTO encuesta;
            
    /*
    * Relación a una agenda 
    * dado que esta tiene cardinalidad 1.
     */
    private AgendaDTO agenda;
    
     /**
     * Constructor por defecto
     */
    public ReservaDTO() {
    }
    
    /**
     * Constructor a partir de la entidad
     *
     * @param reservaEntity La entidad del libro
     */
    public ReservaDTO(ReservaEntity reservaEntity) {
        if (reservaEntity != null) {
            this.fechaReserva = reservaEntity.getFechaReserva();
            this.fechaServicio = reservaEntity.getFechaServicio();
            this.estadoValidacion = reservaEntity.getEstadoValidacion();
            this.reservaRecurrente = reservaEntity.getReservaRecurrente();
            this.cancelada = reservaEntity.getCancelada();
            this.motivoCancelacion = reservaEntity.getMotivoCancelacion();
            
            if (reservaEntity.getVehiculo() != null) {
                this.vehiculo = new VehiculoDTO(reservaEntity.getVehiculo());
            } else {
                this.vehiculo = null;
            }
            
            if (reservaEntity.getAgenda() != null) {
                this.agenda = new AgendaDTO(reservaEntity.getAgenda());
            } else {
                this.agenda = null;
            }
            
            if (reservaEntity.getEncuesta() != null) {
                this.encuesta = new EncuestaDTO(reservaEntity.getEncuesta());
            } else {
                this.encuesta = null;
            }
            
        }
        
    }
        
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad de la reserva asociado.
     */
    public ReservaEntity toEntity() {
        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setFechaReserva(this.fechaReserva);
        reservaEntity.setFechaServicio(this.fechaServicio);
        reservaEntity.setEstadoValidacion(this.estadoValidacion);
        reservaEntity.setReservaRecurrente(this.reservaRecurrente);
        reservaEntity.setCancelada(this.cancelada);
        reservaEntity.setMotivoCancelacion(this.motivoCancelacion);
        if (this.vehiculo != null) {
            reservaEntity.setVehiculo(this.vehiculo.toEntity());
        }
        return reservaEntity;
    }    
  
    public Date getFechaReserva() {
        return this.fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    public Date getFechaServicio() {
        return this.fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }
    
    public Boolean getEstadoValidacion() {
        return this.estadoValidacion;
    }

    public void setEstadoValidacion(Boolean estadoValidacion) {
        this.estadoValidacion = estadoValidacion;
    }
    
    public Boolean getReservaRecurrente() {
        return this.reservaRecurrente;
    }

    public void setReservaRecurrente(Boolean reservaRecurrente) {
        this.reservaRecurrente = reservaRecurrente;
    }
    
    public Boolean getCancelada() {
        return this.cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }
    
    public String getMotivoCancelacion() {
        return this.motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }
    
    public VehiculoDTO getVehiculo(){
        return this.vehiculo;
    }
    
    public void setVehiculo(VehiculoDTO vehiculo){
        this.vehiculo = vehiculo;
    }
    
    public AgendaDTO getAgenda(){
        return this.agenda;
    }
    
    public void setAgenda(AgendaDTO agenda){
        this.agenda = agenda;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public EncuestaDTO getEncuesta(){
        return this.encuesta;
    }
    
    public void setEncuesta(EncuestaDTO encuesta){
        this.encuesta = encuesta;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
