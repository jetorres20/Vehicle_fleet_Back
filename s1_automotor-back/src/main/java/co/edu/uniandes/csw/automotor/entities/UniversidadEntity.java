/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Villamarin
 */
@Entity
public class UniversidadEntity extends BaseEntity implements Serializable{
    
    private String nombre;
    
    private String ciudad;
    
    private boolean privada;
    
    @PodamExclude
    @javax.persistence.OneToMany(
    mappedBy = "universidad",
    fetch = javax.persistence.FetchType.LAZY
    )
    Collection<ProfesorEntity> profesores;
    
    @PodamExclude
    @javax.persistence.OneToMany(
    mappedBy = "universidad",
    fetch = javax.persistence.FetchType.LAZY
    )
    Collection<ProfesorEntity> estudiantes;
    
    

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the privada
     */
    public boolean getPrivada() {
        return privada;
    }

    /**
     * @param privada the privada to set
     */
    public void setPrivada(boolean privada) {
        this.privada = privada;
    }
    
    
}
