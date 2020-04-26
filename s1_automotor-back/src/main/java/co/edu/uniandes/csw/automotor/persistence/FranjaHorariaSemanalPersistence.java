/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.FranjaHorariaSemanalEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Villamarin
 */
@Stateless
public class FranjaHorariaSemanalPersistence {
    
    @PersistenceContext(unitName = "automotorPU")
    private EntityManager em;
    
    public FranjaHorariaSemanalEntity create(FranjaHorariaSemanalEntity franja){
        em.persist(franja);
        return franja;
    }
    
    public FranjaHorariaSemanalEntity find(long id){
        return em.find(FranjaHorariaSemanalEntity.class, id);
    }
    
    public FranjaHorariaSemanalEntity update(FranjaHorariaSemanalEntity franja){
        return em.merge(franja);
    }
    
    public Collection<FranjaHorariaSemanalEntity> findAll(){
        Collection<FranjaHorariaSemanalEntity> list=em.createQuery("SELECT e FROM FranjaHorariaSemanalEntity e").getResultList();
        return list;
    }
    
    public Collection<FranjaHorariaSemanalEntity> findAllConductor(long id)
    {
        Collection<FranjaHorariaSemanalEntity> list=em.createQuery("SELECT e FROM FranjaHorariaSemanalEntity e WHERE e.conductor.id = " + id, FranjaHorariaSemanalEntity.class).getResultList();
        return list;
    }
    
    public void delete(long id){
        FranjaHorariaSemanalEntity franja=em.find(FranjaHorariaSemanalEntity.class,id);
        if(franja!=null){
            em.remove(franja);
        }
    }
}
