/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.ConductorPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author Pablo Garzon
 */
@Stateless
public class ConductorLogic {
    
    @Inject
    ConductorPersistence conductorPersistence;
    
    public ConductorEntity createConductor(ConductorEntity conductor)throws BusinessLogicException
    {
        if(conductor.getName() == null)
        {
            throw new  BusinessLogicException("el nombre del conductor no debe ser null");
        }
        ConductorEntity creado = conductorPersistence.create(conductor);
        return creado;
    }
    
    public List<ConductorEntity> getConductores()
    {
       return (List<ConductorEntity>)conductorPersistence.finAll();
    }
    
    public ConductorEntity getConductor(long id)
    {
        return conductorPersistence.find(id);
    }
    
    public ConductorEntity updateConductor(ConductorEntity conductor)
    {
       return conductorPersistence.update(conductor);
    }
    
    public void deleteConductor(long condId)throws BusinessLogicException
    {
        ConductorEntity entity = conductorPersistence.find(condId);
        if(!entity.getAgendas().isEmpty() || !entity.getReservas().isEmpty() || !entity.getFranjasHorariasSemanales().isEmpty())
        {
            throw new BusinessLogicException("el conductor no se puede borrar porque tiene datos asociados a el");
        }
        conductorPersistence.delete(condId);
    }
}
