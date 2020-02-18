/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.AgendaPersistence;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.time.DateUtils;

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
        
        if(agendaEntity.getDuracionEnMin() < 0)
        {
            throw new BusinessLogicException("la duracion no puede ser negativa");
        }
        
        Collection<AgendaEntity> data = agendaPersistence.finAll();
        if(!data.isEmpty())
        {
        Date test = DateUtils.addMinutes(agendaEntity
                .getFecha(),agendaEntity.getDuracionEnMin());
        Iterator<AgendaEntity> it = data.iterator();
        while(it.hasNext())
        {
            AgendaEntity test2 = it.next();
            Date testFecha1 = test2.getFecha();
            Date testFecha2 = DateUtils.
                    addMinutes(test2.getFecha(), test2.getDuracionEnMin());
            if(agendaEntity.getFecha().before(testFecha1)
                    && test.after(testFecha1))
                throw new BusinessLogicException("la fecha no debe cruzarse con otra fecha");
            
            if(agendaEntity.getFecha().after(testFecha1) 
                    && agendaEntity.getFecha().before(testFecha2))
                throw new BusinessLogicException("la fecha no debe cruzarse con otra fecha");
        }
        }
        AgendaEntity agenda = agendaPersistence.create(agendaEntity);
        return agenda;
    }
    
    public Collection<AgendaEntity> getfechas()
    {
        return agendaPersistence.finAll();
    }
    
    public AgendaEntity getFecha(long id)
    {
        return agendaPersistence.find(id);
    }
    
    public AgendaEntity updateFecha(AgendaEntity ag)
    {
        AgendaEntity result = agendaPersistence.update(ag);
        return result;
    }
    
    public void deleteFecha(long entId)throws BusinessLogicException
    {
        AgendaEntity entity = agendaPersistence.find(entId);
        if(entity.getConductor() != null)
            throw new BusinessLogicException("la fecha tiene un conductor asociado");
        
        if(!entity.getReservas().isEmpty())
            throw new BusinessLogicException("la fehca tien una reserva asociada");
        
        agendaPersistence.delete(entId);
    }
        
}
