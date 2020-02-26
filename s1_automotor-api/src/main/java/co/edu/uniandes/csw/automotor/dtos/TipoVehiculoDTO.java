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
 *
 * @author Mateo Laguna G.
 */
public class TipoVehiculoDTO implements Serializable{
    
    private String tipo;
    
    /**
     * Constructor por defecto
     */
    public TipoVehiculoDTO() {
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param editorialEntity: Es la entidad que se va a convertir a DTO
     */
    public TipoVehiculoDTO(TipoVehiculoEntity tipoVehiculoEntity) {
        if(tipoVehiculoEntity != null) {
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
