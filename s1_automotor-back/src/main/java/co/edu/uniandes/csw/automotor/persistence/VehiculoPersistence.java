/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan Andres Caycedo S.
 */
@Stateless
public class VehiculoPersistence {

    @PersistenceContext(unitName = "automotorPU")
    protected EntityManager em;

    public VehiculoEntity create(VehiculoEntity vehiculo) {
        em.persist(vehiculo);
        return vehiculo;
    }

    public VehiculoEntity find(long id) {
        
       VehiculoEntity buscado = em.find(VehiculoEntity.class, id);
       return  buscado;
    
    }
    
    public Collection<VehiculoEntity> finAll(){
       Collection<VehiculoEntity> lista = em.createQuery("SELECT e FROM VehiculoEntity e").getResultList();
       return lista;
    }
    
    public VehiculoEntity update( VehiculoEntity vehiculo){
        em.merge(vehiculo);
        return vehiculo;
    }
    
    public void delete ( long id){
        
        VehiculoEntity eliminado = em.find(VehiculoEntity.class, id);
        if(eliminado != null) em.remove(eliminado);
      
    }
}
