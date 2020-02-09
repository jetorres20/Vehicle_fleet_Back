/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

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
public class ProfesorPersistence {
    @PersistenceContext(unitName="automotorPU")
    protected EntityManager em;

    public ProfesorEntity create(ProfesorEntity profesor){
     
        em.persist(profesor);
        return profesor;
    }
    public ProfesorEntity find(ProfesorEntity profesor){
     
        return em.find(ProfesorEntity.class, profesor.getId());
    }
    public ProfesorEntity update(ProfesorEntity profesor){
     
        return em.merge(profesor);
    }
     public Collection<ProfesorEntity> findAll(){
     
        Collection<ProfesorEntity>lista=em.createQuery("SELECT e FROM ProfesorEntity e").getResultList();
        return lista;
    }
      public void delete ( long id){
        
        ProfesorEntity eliminado = em.find(ProfesorEntity.class, id);
        if(eliminado != null) em.remove(eliminado);
      
    }
}
