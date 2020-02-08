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
 * @author Nestor Plata
 */
@Entity
public class ProfesorEntity extends BaseEntity implements Serializable {
    private String nombre;
    private int identificacion;
       @javax.persistence.OneToMany(
        mappedBy = "profesor",
        fetch = javax.persistence.FetchType.LAZY
    )
    Collection<PracticaEntity> practicas;

    @javax.persistence.ManyToOne()
    UniversidadEntity universidad;
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
     * @return the identificacion
     */
    public int getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
    
}
