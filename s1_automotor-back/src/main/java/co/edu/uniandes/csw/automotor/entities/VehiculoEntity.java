/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Andrés Caycedo S.
 */
@Entity
public class VehiculoEntity extends BaseEntity implements Serializable {

    //Atributos
    private String marca;
    private String modelo;
    private Integer capacidad;
    private String placa;

    //Asociaciones
    @PodamExclude
    @javax.persistence.OneToOne(
            mappedBy = "vehiculo",
            fetch = javax.persistence.FetchType.LAZY
    )
   private RegistroEntity registro;
    
    @PodamExclude
    @OneToOne
   private TipoVehiculoEntity tipoVehiculo;

    //Metodos
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
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.setCapacidad((Integer) capacidad);
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
     * @return the registro
     */
    public RegistroEntity getRegistro() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(RegistroEntity registro) {
        this.registro = registro;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the tipoVehiculo
     */
    public TipoVehiculoEntity getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * @param tipoVehiculo the tipoVehiculo to set
     */
    public void setTipoVehiculo(TipoVehiculoEntity tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

}
