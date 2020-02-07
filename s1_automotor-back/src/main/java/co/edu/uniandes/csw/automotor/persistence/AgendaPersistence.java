/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pablo Garzon
 */
@Stateless
public class AgendaPersistence {
    @PersistenceContext(unitName = "automotorPU")
    protected EntityManager em;
    public AgendaEntity create(AgendaEntity agenda)
    {
        em.persist(agenda);
        return agenda;
    }
    
    public AgendaEntity find(long id)
    {
        AgendaEntity agendaB = em.find(AgendaEntity.class, id);
        return agendaB;
    }
    
    public Collection<AgendaEntity> finAll()
    {
        return em.createQuery("SELECT e FROM AgendaEntity e").getResultList();
    }
    
    public AgendaEntity update(AgendaEntity agenda)
    {
        em.merge(agenda);
        return agenda;
    }
    
    public void delete(long id)
    {
       em.remove(find(id));
    }
}
