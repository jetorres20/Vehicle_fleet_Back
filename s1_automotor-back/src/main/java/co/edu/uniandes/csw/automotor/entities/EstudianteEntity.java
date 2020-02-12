/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Esteban Torres 
 */
@Entity
public class EstudianteEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @javax.persistence.ManyToMany()
    Collection<ProfesorEntity> profesores;
    
     @PodamExclude
    @javax.persistence.ManyToOne()
    UniversidadEntity universidad;
    
    @PodamExclude
    @ManyToMany(
        mappedBy = "estudiantes",
        fetch = javax.persistence.FetchType.LAZY
    )
    private Collection<ReservaEntity> reservas;
      
    private String name;
    
    private Integer codigo;
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public Integer getCodigo(){
            return codigo;
    }
    
    public void setCodigo(int cod){
        
        this.codigo= cod;
    }
    
    public Collection<ReservaEntity> getReservas(){
    return reservas;
    }
    
    public void setReservas(Collection<ReservaEntity> reservasP){
        reservas=reservasP;
    }
    
    public Collection<ProfesorEntity> getProfesor(){
    return profesores;
    }
    
    public void setProfesor(Collection<ProfesorEntity> prof){
        profesores=prof;
    }
    
    public UniversidadEntity getUniversidad(){
    return universidad;
    }
    
    public void setUniversidad(UniversidadEntity uni){
        universidad=uni;
    }
    
    
}
