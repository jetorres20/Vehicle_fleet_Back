/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.TipoVehiculoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TipoVehiculoDTO Objeto de transferencia de datos de TipoVehiculo. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el cliente
 * y el servidor.   
 * @author Mateo Laguna G.
 */
public class TipoVehiculoDTO implements Serializable{
    
    private String tipo;
    private Long id;
    
    /**
     * Constructor por defecto
     */
    public TipoVehiculoDTO() {
    }
    
    /**
     * Convertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param tipoVehiculoEntity: Es la entidad que se va a convertir a DTO
     */
    public TipoVehiculoDTO(TipoVehiculoEntity tipoVehiculoEntity) {
        if(tipoVehiculoEntity != null) {
            this.id = tipoVehiculoEntity.getId();
            this.tipo = tipoVehiculoEntity.getTipo();
        }
    }
    
    /**
     * Devuelve el tipo del TipoVehiculo.
     *
     * @return el tipo
     */
    public String getTipo() {
        return this.tipo;
    }
    
    /**
     * Modifica el tipo de TipoVehiculo.
     *
     * @param tipo the id to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
     /**
     * Devuelve el Id del TipoVehiculo.
     *
     * @return el Id
     */
    public Long getId() {
        return this.id;
    }
    
    /**
     * Modifica el id de TipoVehiculo.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TipoVehiculoEntity toEntity() {
        TipoVehiculoEntity tipoVehiculoEntity = new TipoVehiculoEntity();
        tipoVehiculoEntity.setTipo(this.tipo);
        return tipoVehiculoEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
