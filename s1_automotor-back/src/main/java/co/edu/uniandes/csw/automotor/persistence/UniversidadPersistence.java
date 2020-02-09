/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.UniversidadEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Villamarin
 */
@Stateless
public class UniversidadPersistence {
    
    @PersistenceContext(unitName = "automotorPU")
    protected EntityManager em;
    
    
    public UniversidadEntity create(UniversidadEntity universidad){
        em.persist(universidad);
        return universidad;
    }
    
    public UniversidadEntity update(UniversidadEntity universidad){
        return em.merge(universidad);
    }
    
    public UniversidadEntity find(long id){
        return em.find(UniversidadEntity.class, id);
    }
    
    public Collection<UniversidadEntity> findAll(){
        Collection<UniversidadEntity> list=em.createQuery("SELECT e FROM UniversidadEntity e").getResultList();
        return list;
    }
    
    public void delete(long id){
        UniversidadEntity universidad=em.find(UniversidadEntity.class, id);
        if(universidad!=null){
            em.remove(universidad);
        }
    }
}
