/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.TipoVehiculoEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.TipoVehiculoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo Laguna G.
 */
@Stateless
public class TipoVehiculoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TipoVehiculoLogic.class.getName());
    
    @Inject
    private TipoVehiculoPersistence persistence;
    
    public TipoVehiculoEntity createTipoVehiculo(TipoVehiculoEntity tipoVehiculo) throws BusinessLogicException
    {
        if(tipoVehiculo.getTipo().equals(""))
        {
            throw new BusinessLogicException("El tipo de vehiculo está vacío.");
        }
        else
        {
            tipoVehiculo = persistence.create(tipoVehiculo);
            return tipoVehiculo;
        }
    }
    
    public List<TipoVehiculoEntity> getTipoVehiculos()
    {
        List<TipoVehiculoEntity> tipoVehiculos = (List<TipoVehiculoEntity>) persistence.finAll();
        return tipoVehiculos;
    }
    
    /**
     * Obtiene los datos de una instancia de TipoVehiculo a partir de su ID.
     *
     * @param tipoVehiculoId Identificador de la instancia a consultar
     * @return Instancia de TipoVehiculoEntity con los datos del TipoVehiculo consultado.
     */
    public TipoVehiculoEntity getTipoVehiculo(Long tipoVehiculoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el tipoVehiculo con id = {0}", tipoVehiculoId);
        TipoVehiculoEntity tipoVehiculoEntity = persistence.find(tipoVehiculoId);
        if (tipoVehiculoEntity == null) {
            LOGGER.log(Level.SEVERE, "El Tipo Vehiculo con el id = {0} no existe", tipoVehiculoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el Tipo Vehiculo con id = {0}", tipoVehiculoId);
        return tipoVehiculoEntity;
    }
   
    public TipoVehiculoEntity updateTipoVehiculo(Long tipoVehiculoId, TipoVehiculoEntity tipoVehiculoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el tipoVehiculo con id = {0}", tipoVehiculoId);
        TipoVehiculoEntity newTipoVehiculoEntity = persistence.update(tipoVehiculoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el tipoVehiculo con id = {0}", tipoVehiculoId);
        return newTipoVehiculoEntity;
    }
    
    public void removeTipoVehiculo(Long tipoVehiculoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el tipoVehiculo con id = {0}", tipoVehiculoId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        VehiculoEntity vehiculo = getTipoVehiculo(tipoVehiculoId).getVehiculo();
        if(vehiculo != null){
            throw new BusinessLogicException("No se puede borrar la editorial con id = " + tipoVehiculoId + " porque tiene un Vehiculo asociados");
        }
        persistence.delete(tipoVehiculoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el tipoVehiculo con id = {0}", tipoVehiculoId);
    }
}
