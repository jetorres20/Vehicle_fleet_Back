/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.ConductorPersistence;
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
}
