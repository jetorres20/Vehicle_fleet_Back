/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;

/**
 *
 * @author Juan Esteban Torres 
 */
@Entity
public class EstudianteEntity extends BaseEntity implements Serializable{
    
      @javax.persistence.ManyToMany()
    Collection<ProfesorEntity> profesores;
      
      @javax.persistence.ManyToOne()
    UniversidadEntity universidad;
      
    private String name;
    
    private int codigo;
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public int getCodigo(){
            return codigo;
    }
    
    public void setCodigo(int cod){
        
        this.codigo= cod;
    }
    
    
    
    
}
