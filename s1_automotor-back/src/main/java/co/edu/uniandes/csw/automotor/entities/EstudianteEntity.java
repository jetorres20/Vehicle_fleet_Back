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
 * @author estudiante
 */
@Entity
public class EstudianteEntity extends BaseEntity implements Serializable{
    
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
