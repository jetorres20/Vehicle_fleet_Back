/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan Andrés Caycedo S.
 */

@Stateless
public class RegistroPersistence {

    @PersistenceContext(unitName = "automotorPU")
    protected EntityManager em;

    public RegistroEntity create(RegistroEntity registro) {
        em.persist(registro);
        return registro;
    }
    
    public RegistroEntity find(long id) {
        
       RegistroEntity buscado = em.find(RegistroEntity.class, id);
       return buscado;
    
    }
    
    public Collection<RegistroEntity> finAll(){
        Query query  = em.createQuery("SELECT e FROM RegistroEntity e");
        return (Collection<RegistroEntity>) query.getResultList();
    }
    
    public RegistroEntity update( RegistroEntity vehiculo){
        em.merge(vehiculo);
        return vehiculo;
    }
    
    public void delete ( long id){
        
        RegistroEntity eliminado = em.find(RegistroEntity.class, id);
        if(eliminado != null) em.remove(eliminado);
      
    }
}
