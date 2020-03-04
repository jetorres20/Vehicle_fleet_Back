/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;

/**
 *
 * @author Juan Esteban Torres
 */
public class EstudianteDTO {
    
    private String nombre;
    private Integer codigo;
    
    public EstudianteDTO(){
        
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param estudianteEntity: Es la entidad que se va a convertir a DTO
     */
    public EstudianteDTO(EstudianteEntity estudianteEntity) {
        if (estudianteEntity != null) {
            this.nombre = estudianteEntity.getName();
            this.codigo = estudianteEntity.getCodigo();
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
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
}
