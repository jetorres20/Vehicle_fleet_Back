/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.PracticaEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nestor Plata
 */
@Stateless
public class PracticaPersistence {
     @PersistenceContext(unitName="automotorPU")
    protected EntityManager em;

    public PracticaEntity create(PracticaEntity practica){
     
        em.persist(practica);
        return practica;
    }
    public PracticaEntity find(long id){
     
        return em.find(PracticaEntity.class, id);
    }
    public PracticaEntity update(PracticaEntity practica){
     
        return em.merge(practica);
    }
     public Collection<PracticaEntity> findAll(){
     
        Collection<PracticaEntity>lista=em.createQuery("SELECT e FROM PracticaEntity e").getResultList();
        return lista;
    }
      public void delete ( long id){
        
        PracticaEntity eliminada = em.find(PracticaEntity.class, id);
        if(eliminada != null) em.remove(eliminada);
      
    }
    
}
