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

    
    
    private String name;
    
    private String city;
    
    private Boolean isPrivate;
    
    @javax.persistence.Id
    private Long id;
    
    @PodamExclude
    @javax.persistence.OneToMany(
    mappedBy = "universidad",
    fetch = javax.persistence.FetchType.LAZY
    )
    private Collection<ProfesorEntity> profesores;
    
    @PodamExclude
    @javax.persistence.OneToMany(
    mappedBy = "universidad",
    fetch = javax.persistence.FetchType.LAZY
    )
    private Collection<EstudianteEntity> estudiantes;
    
    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the isPrivate
     */
    public Boolean getIsPrivate() {
        return isPrivate;
    }

    /**
     * @param isPrivate the isPrivate to set
     */
    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    
    /**
     * @return the profesores
     */
    public Collection<ProfesorEntity> getProfesores() {
        return profesores;
    }

    /**
     * @param profesores the profesores to set
     */
    public void setProfesores(Collection<ProfesorEntity> profesores) {
        this.profesores = profesores;
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
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
