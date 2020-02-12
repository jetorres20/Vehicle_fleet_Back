/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Esteban Torres
 */
@Entity

public class EncuestaEntity  extends BaseEntity implements Serializable {
    
    @PodamExclude
    @javax.persistence.ManyToOne()
    ProfesorEntity profesor;
    
    @PodamExclude
    @javax.persistence.OneToOne(mappedBy = "encuesta",
            fetch = javax.persistence.FetchType.LAZY)
    private ReservaEntity reserva;
    
    private Integer calificacion;
    
    
    private String comentario;
    
    public void setCalificacion(int cal){
        
        calificacion=cal;
    }
    
    public Integer getCalificacion(){
        return calificacion;
    }
    
    public void setComentario(String cadena){
        
        comentario=cadena;
    }
    
    public String getComentario(){
        return comentario;
    }
    
    public ReservaEntity getReserva(){
        return reserva;
    }
    
    public void setReserva(ReservaEntity res){
        reserva=res;
    }
    
    public void setProfesor(ProfesorEntity prof){
        profesor=prof;
    }
    
    public ProfesorEntity getProfesor(){
        return profesor;
    }
    
}
