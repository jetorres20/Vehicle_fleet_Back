/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import java.io.Serializable;

/**
 *
 * @author Pablo Garzon
 */
public class ConductorDTO implements Serializable{
    
    private String name;
    private Long idConductor;
    private Long id;
    
    public ConductorDTO()
    {
    }
    
    public ConductorDTO(ConductorEntity entity)
    {
        if(entity != null)
        {
        this.name = entity.getName();
        this.idConductor = entity.getIdConductor();
        this.id = entity.getId();
         }
    }
    
    public ConductorEntity toEntity()
    {
        ConductorEntity ce = new ConductorEntity();
        ce.setIdConductor(getIdConductor());
        ce.setName(getName());
        ce.setId(getId());
        return ce;
    }

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
     * @return the idConductor
     */
    public long getIdConductor() {
        return idConductor;
    }

    /**
     * @param idConductor the idConductor to set
     */
    public void setIdConductor(long idConductor) {
        this.idConductor = idConductor;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
