/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.AgendaPersistence;
import co.edu.uniandes.csw.automotor.persistence.ConductorPersistence;
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
    
    @Inject
    ConductorPersistence conductorPersistence;
    
    public AgendaEntity CreateAgenda(AgendaEntity agendaEntity)throws BusinessLogicException
    {
        if(agendaEntity.getFecha() == null)
        {
            throw new BusinessLogicException("la fecha no deberia estar vacia");
        }
        
        if(agendaEntity.getConductor()==null)
        {
            throw new BusinessLogicException("la fecha debe estar asociada a un conductor");
        }
        
        if(agendaEntity.getReserva()==null)
        {
            throw new BusinessLogicException("debe existir una reserva asociada");
        }
        
        if(!agendaPersistence.finAll().isEmpty())
            checkDate(agendaEntity);
        AgendaEntity agenda = agendaPersistence.create(agendaEntity);
        return agenda;
    }
    
    public void checkDate(AgendaEntity agendaEntity)throws BusinessLogicException
    {
        Collection<AgendaEntity> data = agendaPersistence.finAll();
        if(!data.isEmpty())
        {
        Date test = DateUtils.addMinutes(agendaEntity
                .getFecha(),agendaEntity.getReserva().getPractica().getDuracion().intValue());
        Iterator<AgendaEntity> it = data.iterator();
        while(it.hasNext())
        {
            AgendaEntity test2 = it.next();
            Date testFecha1 = test2.getFecha();
            Date testFecha2 = DateUtils.
                    addMinutes(test2.getFecha(), test2.getReserva().getPractica().getDuracion().intValue());
            if(agendaEntity.getFecha().before(testFecha1)
                    && test.after(testFecha1))
                throw new BusinessLogicException("la fecha no debe cruzarse con otra fecha");
            
            if(agendaEntity.getFecha().after(testFecha1) 
                    && agendaEntity.getFecha().before(testFecha2))
                throw new BusinessLogicException("la fecha no debe cruzarse con otra fecha");
        }
        }
    }
    
    public Collection<AgendaEntity> getDates()
    {
        return agendaPersistence.finAll();
    }
    
    public AgendaEntity getDate(long id)
    {
        return agendaPersistence.find(id);
    }
    
    public AgendaEntity updateDate(AgendaEntity ag) throws BusinessLogicException
    {
        checkDate(ag);
        if(!ag.getReserva().getFechaServicio().equals(ag.getFecha()))
            throw new BusinessLogicException();
        AgendaEntity result = agendaPersistence.update(ag);
        return result;
    }
    
    public void deleteDate(long entId)throws BusinessLogicException
    {
        AgendaEntity entity = agendaPersistence.find(entId);
        if(entity.getConductor() != null)
            throw new BusinessLogicException("la fecha tiene un conductor asociado");
        
        if(entity.getReserva()!= null)
            throw new BusinessLogicException("la fehca tiene una reserva asociada");
        
        agendaPersistence.delete(entId);
    }
    
    public void setConductor(AgendaEntity ag, ConductorEntity conductor)throws BusinessLogicException
    {
        if(ag.getConductor() != null)
        {
            throw new BusinessLogicException("la fecha tiene un conductor asociado");
        }
        ag.setConductor(conductor);
        agendaPersistence.update(ag);
    }
    
    public void removeConductor(AgendaEntity ag)
    {
        ConductorEntity conductor = conductorPersistence.find(ag.getConductor().getId());
        conductor.getAgendas().remove(ag);
        conductorPersistence.update(conductor);
        ag.setConductor(null);
        agendaPersistence.update(ag);
    }
    
    public void setReservada(AgendaEntity ag, boolean realizada)
    {
        ag.setReservada(realizada);
        agendaPersistence.update(ag);
    }
    
}
