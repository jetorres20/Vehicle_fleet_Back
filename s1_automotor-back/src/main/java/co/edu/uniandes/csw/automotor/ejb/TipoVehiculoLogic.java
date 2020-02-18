/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.TipoVehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.TipoVehiculoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo Laguna G.
 */
@Stateless
public class TipoVehiculoLogic {
    
    @Inject
    private TipoVehiculoPersistence persistence;
    
    public TipoVehiculoEntity createTipoVehiculo(TipoVehiculoEntity tipoVehiculo) throws BusinessLogicException
    {
        if(tipoVehiculo.getTipo() == null)
        {
            throw new BusinessLogicException("El tipo de vehiculo está vacío.");
        }
        
        tipoVehiculo = persistence.create(tipoVehiculo);
        return tipoVehiculo;
    }
}
