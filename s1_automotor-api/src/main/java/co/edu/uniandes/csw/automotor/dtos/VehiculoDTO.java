/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Andrés Caycedo S.
 */
public class VehiculoDTO implements Serializable {

    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private Integer capacidad;

    private RegistroDTO registro;

    public VehiculoDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param vehiculoEntity: Es la entidad que se va a convertir a DTO
     */
    public VehiculoDTO(VehiculoEntity vehiculoEntity) {
        if (vehiculoEntity != null) {
            this.id = vehiculoEntity.getId();
            this.marca = vehiculoEntity.getMarca();
            this.capacidad = vehiculoEntity.getCapacidad();
            this.modelo = vehiculoEntity.getModelo();
            this.placa = vehiculoEntity.getPlaca();

            this.registro = new RegistroDTO(vehiculoEntity.getRegistro());
        }
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the capacidad
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the registro
     */
    public RegistroDTO getRegistro() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(RegistroDTO registro) {
        this.registro = registro;
    }

    /**
     * Convertir DTO a entity
     */
    public VehiculoEntity toEntity() {
        VehiculoEntity entidad = new VehiculoEntity();

        entidad.setMarca(this.getMarca());
        entidad.setCapacidad(this.getCapacidad());
        entidad.setModelo(this.getModelo());
        entidad.setPlaca (this.getPlaca());
        entidad.setId(this.getId());
        entidad.setRegistro(this.registro.toEntity());
        return entidad;
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
