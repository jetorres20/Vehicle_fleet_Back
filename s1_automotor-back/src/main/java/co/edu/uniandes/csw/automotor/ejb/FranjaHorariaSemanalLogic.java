/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.entities.FranjaHorariaSemanalEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.FranjaHorariaSemanalPersistence;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Villamarin
 */
@Stateless
public class FranjaHorariaSemanalLogic {
    @Inject
    private FranjaHorariaSemanalPersistence persitence;
    
    public FranjaHorariaSemanalEntity createFranjaHorariaSemanal(FranjaHorariaSemanalEntity franja) throws BusinessLogicException{
        
        if(verifyRules(franja)){
            franja=persitence.create(franja);
            return franja;
        }
        return null;
    }
    
    public FranjaHorariaSemanalEntity upDateFranjaFinishHour(FranjaHorariaSemanalEntity franja,Date date) throws BusinessLogicException{
        franja.setFinishHour(date);
        if(verifyRules(franja)){
            franja=persitence.update(franja);
            return franja;
        }
        return null;
    }
    
    public FranjaHorariaSemanalEntity upDateFranjaStartHour(FranjaHorariaSemanalEntity franaja,Date date) throws BusinessLogicException{
        franaja.setStartHour(date);
        if(verifyRules(franaja)){
            franaja=persitence.update(franaja);
            return franaja;
        }
        return null;
    }
    
    public FranjaHorariaSemanalEntity upDateFranajaConductor(FranjaHorariaSemanalEntity franja,ConductorEntity conductor) throws BusinessLogicException{
        franja.setConductor(conductor);
        if(verifyRules(franja)){
            franja=persitence.update(franja);
            return franja;
        }
        return null;
    }
    
    public List<FranjaHorariaSemanalEntity> getFranjasCond(long id){
        List<FranjaHorariaSemanalEntity> res=(List<FranjaHorariaSemanalEntity>)persitence.findAllConductor(id);
        return res;
    }
    
    public FranjaHorariaSemanalEntity find(long id){
        return persitence.find(id);
    }
    
    public FranjaHorariaSemanalEntity upDateFranja(FranjaHorariaSemanalEntity franja) throws BusinessLogicException{
        if(verifyRules(franja)){
            franja=persitence.update(franja);
            return franja;
        }
        return null;
    }
    
    public void deleteFranja(long id){
        persitence.delete(id);
    }
    
    private boolean verifyRules(FranjaHorariaSemanalEntity franja)throws BusinessLogicException{
        if(franja.getFinishHour()==null){
            throw new BusinessLogicException("La Hora de fin no puede ser null");
        }
        if(franja.getStartHour()==null){
            throw new BusinessLogicException("La Hora de inicio no puede ser null");
        }
        if(franja.getWeekDay()==null){
            throw new BusinessLogicException("El dia de la semana no pude ser null");
        }
        if(franja.getConductor()==null){
            throw new BusinessLogicException("El conductor no pude ser null");
        }
        
        Collection<FranjaHorariaSemanalEntity> franjas=persitence.findAll();
        Iterator<FranjaHorariaSemanalEntity> it=franjas.iterator();
        while(it.hasNext()){
            FranjaHorariaSemanalEntity act=it.next();
            if(act.getFinishHour().compareTo(franja.getFinishHour())==0 && act.getStartHour().compareTo(franja.getFinishHour())==0 ){
                //&& act.getConductor().equals(franja.getConductor() )&& act.getWeekDay()==franja.getWeekDay()
                throw new BusinessLogicException("No puede haber dos frnajas iguales"); 
            }
        }
        //if(franja.getFinishHour().compareTo(franja.getStartHour())<=0){   
        //}
        return true;
    }
}
