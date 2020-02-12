/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Mateo Laguna
 */
@Entity
public class TipoVehiculoEntity extends BaseEntity implements Serializable
{
    @javax.persistence.Id
    private Long id;
    private String tipo;
    
    /**
     * Muy importante tener en cuenta que el mappedBy debe hacerse en una de las clases de la relación, no en ambas. 
     * De lo contrario generará un error para el servidor porque la base de datos tendrá una incosistencia. 
     */
    @PodamExclude
    @OneToOne
    (
            mappedBy = "tipoVehiculo",
            fetch = javax.persistence.FetchType.LAZY
    )
    private VehiculoEntity vehiculo;
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public Long getId()
    {
        return this.id;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public String getTipo()
    {
        return this.tipo;
    }
    
    public void setVehiculo(VehiculoEntity vehiculo) 
    {
        this.vehiculo = vehiculo;
    }

    public VehiculoEntity getVehiculo() 
    {
        return this.vehiculo;
    }
}
