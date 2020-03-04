/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.UniversidadEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Villamarin
 */
public class UniversidadDTO implements Serializable{
    
    private String name;
    
    private String city;
    
    private Long id;
    
    private Boolean isPrivate;
    
    public UniversidadDTO(){
    }
    
    public UniversidadDTO(UniversidadEntity uni){
        if(uni!=null){
            this.city=uni.getCity();
            this.name=uni.getName();
            this.isPrivate=uni.getIsPrivate();
            this.id=uni.getId();
        }
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
    
    public UniversidadEntity toEntity() {
        UniversidadEntity universidad = new UniversidadEntity();
        universidad.setCity(this.getCity());
        universidad.setIsPrivate(this.getIsPrivate());
        universidad.setName(this.getName());
        universidad.setId(this.getId());
        return universidad;
    }
}
