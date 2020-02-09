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
 * @author Juan Esteban Torres
 */
@Entity

public class EncuestaEntity  extends BaseEntity implements Serializable {
    
    
    private int calificacion;
    
    private String comentario;
    
    public void setCalificacion(int cal){
        
        calificacion=cal;
    }
    
    public int getCalificacion(){
        return calificacion;
    }
    
    public void setComentario(String cadena){
        
        comentario=cadena;
    }
    
    public String getComentario(){
        return comentario;
    }
    
    
}
