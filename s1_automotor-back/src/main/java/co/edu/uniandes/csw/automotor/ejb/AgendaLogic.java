/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.AgendaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Pablo Garzon
 */
@Stateless
public class AgendaLogic {
    @Inject
    AgendaPersistence agendaPersistence;
    
    public AgendaEntity CreateAgenda(AgendaEntity agendaEntity)throws BusinessLogicException
    {
        if(agendaEntity.getFecha() == null)
        {
            throw new BusinessLogicException("la fecha no deberia estar vacia");
        }
        AgendaEntity agenda = agendaPersistence.create(agendaEntity);
        return agenda;
    }
}
