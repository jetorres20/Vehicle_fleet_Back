/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pablo Garzon
 */
@Stateless
public class ConductorPersistence {
    
    @PersistenceContext(unitName = "automotorPU")
    protected EntityManager em;
    public ConductorEntity create(ConductorEntity conductor)
    {
        em.persist(conductor);
        return conductor;
    }
    
    public ConductorEntity find(long id)
    {
        return em.find(ConductorEntity.class, id);
    }
    
     public Collection<ConductorEntity> finAll()
    {
        return em.createQuery("SELECT e FROM ConductorEntity e").getResultList();
    }
    
    public ConductorEntity update(ConductorEntity conductorEn)
    {
       em.merge(conductorEn);
       return conductorEn;
    }
    
    public void delete(long id)
    {
        em.remove(find(id));
    }
}
