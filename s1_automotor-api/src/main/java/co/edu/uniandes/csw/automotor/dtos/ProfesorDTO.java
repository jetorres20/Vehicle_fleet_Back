/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;

/**
 *
 * @author Nestor Plata
 */
public class ProfesorDTO {

    
    
    
      public ProfesorDTO(){
      }
     public ProfesorDTO(ProfesorEntity prof){
         if(prof!=null){
             this.identificacion=prof.getIdentificacion();
             this.nombre=prof.getNombre();
             this.id=prof.getId();
             
         }
     }
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
    private String nombre;
    private Integer identificacion;
    
    private Long id;
    
    public ProfesorEntity toEntity() {
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.setIdentificacion(this.getIdentificacion());
        profesorEntity.setNombre(this.getNombre());
        profesorEntity.setId(this.getId());
        return profesorEntity;
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
