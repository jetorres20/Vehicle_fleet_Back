/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Mateo Laguna
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable
{
   
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaReserva;
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaServicio;
    private Boolean estadoValidacion;
    private Boolean reservaRecurrente;
    private Boolean cancelada;
    private String motivoCancelacion;
    
    @PodamExclude
    @ManyToOne()
    private ProfesorEntity profesor;
    
    @PodamExclude
    @OneToOne
    private EncuestaEntity encuesta;
    
    @PodamExclude
    @ManyToMany
    private Collection<EstudianteEntity> estudiantes;
   
    @PodamExclude
    @OneToOne
    private PracticaEntity practica;
    
    @PodamExclude
    @ManyToOne()
    private VehiculoEntity vehiculo;
    
    @PodamExclude
    @OneToOne()
    private AgendaEntity agenda;
    
    @PodamExclude
    @ManyToOne()
    private ConductorEntity conductor;
        
    public void setFechaReserva(Date fechaReserva)
    {
        this.fechaReserva = fechaReserva;
    }
    
    public Date getFechaReserva()
    {
        return this.fechaReserva;
    }
    
    public void setFechaServicio(Date fechaServicio)
    {
        this.fechaServicio = fechaServicio;
    }
    
    public Date getFechaServicio()
    {
        return this.fechaServicio;
    }
    
    public void setEstadoValidacion(Boolean estadoValidacion)
    {
        this.estadoValidacion = estadoValidacion;
    }
    
    public Boolean getEstadoValidacion()
    {
        return this.estadoValidacion;
    }
    
    public void setReservaRecurrente(Boolean reservaRecurrente)
    {
        this.reservaRecurrente = reservaRecurrente;
    }
    
    public Boolean getReservaRecurrente()
    {
        return this.reservaRecurrente;
    }
    
    public void setCancelada(Boolean cancelada)
    {
        this.cancelada = cancelada;
    }
    
    public Boolean getCancelada()
    {
        return this.cancelada;
    }
    
    public void setMotivoCancelacion(String motivoCancelacion)
    {
        this.motivoCancelacion = motivoCancelacion;
    }
    
    public String getMotivoCancelacion()
    {
        return this.motivoCancelacion;
    }
    
    public void setProfesor(ProfesorEntity profesor)
    {
        this.profesor = profesor;
    }
    
    public ProfesorEntity getProfesor()
    {
        return this.profesor;
    }
    
    public void setEncuesta(EncuestaEntity encuesta)
    {
        this.encuesta = encuesta;
    }
    
    public EncuestaEntity getEncuesta()
    {
        return this.encuesta;
    }
    
    public void setEstudiantes(Collection<EstudianteEntity> estudiantes)
    {
        this.estudiantes = estudiantes;
    }
    
    public Collection<EstudianteEntity> getEstudiantes()
    {
        return this.estudiantes;
    }
    
    public void setPractica(PracticaEntity practica)
    {
        this.practica = practica;
    }
    
    public PracticaEntity getPractica()
    {
        return this.practica;
    }
    
    public void setVehiculo(VehiculoEntity vehiculo)
    {
        this.vehiculo = vehiculo;
    }
    
    public VehiculoEntity getVehiculo()
    {
        return this.vehiculo;
    }
    
    public void setAgenda(AgendaEntity agenda)
    {
        this.agenda = agenda;
    }
    
    public AgendaEntity getAgenda()
    {
        return this.agenda;
    }
    
    public void setConductor(ConductorEntity conductor)
    {
        this.conductor = conductor;
    }
    
    public ConductorEntity getConductor()
    {
        return this.conductor;
    }
    
}
