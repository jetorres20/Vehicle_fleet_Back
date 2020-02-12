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
 * @author Nestor Plata
 */
@Entity
public class ProfesorEntity extends BaseEntity implements Serializable {
    private String nombre;
    private Integer identificacion;
    @PodamExclude
       @javax.persistence.ManyToMany(
        mappedBy = "profesores",
        fetch = javax.persistence.FetchType.LAZY
    )
    private Collection<EstudianteEntity> estudiantes;

    @PodamExclude
       @javax.persistence.OneToMany(
        mappedBy = "profesor",
        fetch = javax.persistence.FetchType.LAZY
    )
    private Collection<EncuestaEntity> encuestas;
    
    @PodamExclude
       @javax.persistence.OneToMany(
        mappedBy = "profesor",
        fetch = javax.persistence.FetchType.LAZY
    )
    private Collection<EncuestaEntity> practicas;
       
    @PodamExclude  
    @javax.persistence.ManyToOne()
    private UniversidadEntity universidad;
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
    public Integer getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the estudiantes
     */
    public Collection<EstudianteEntity> getEstudiantes() {
        return estudiantes;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(Collection<EstudianteEntity> estudiantes) {
        this.estudiantes = estudiantes;
    }

    /**
     * @return the encuestas
     */
    public Collection<EncuestaEntity> getEncuestas() {
        return encuestas;
    }

    /**
     * @param encuestas the encuestas to set
     */
    public void setEncuestas(Collection<EncuestaEntity> encuestas) {
        this.encuestas = encuestas;
    }

    /**
     * @return the practicas
     */
    public Collection<EncuestaEntity> getPracticas() {
        return practicas;
    }

    /**
     * @param practicas the practicas to set
     */
    public void setPracticas(Collection<EncuestaEntity> practicas) {
        this.practicas = practicas;
    }

    /**
     * @return the universidad
     */
    public UniversidadEntity getUniversidad() {
        return universidad;
    }

    /**
     * @param universidad the universidad to set
     */
    public void setUniversidad(UniversidadEntity universidad) {
        this.universidad = universidad;
    }
    
}
