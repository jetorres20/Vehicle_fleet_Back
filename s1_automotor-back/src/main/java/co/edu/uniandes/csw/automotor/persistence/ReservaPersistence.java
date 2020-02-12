/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mateo Laguna
 */
@Stateless
public class ReservaPersistence {
    @PersistenceContext(unitName = "automotorPU")
    private EntityManager em;
    
    public ReservaEntity create(ReservaEntity reserva)
    {
        em.persist(reserva);
        return reserva;        
    }
    
    public ReservaEntity find(long id)
    {
        ReservaEntity tp_veh = em.find(ReservaEntity.class, id);
        return tp_veh;
    }
    
    public Collection<ReservaEntity> finAll()
    {
        Collection<ReservaEntity> lista = em.createQuery("SELECT e FROM ReservaEntity e").getResultList();
        return lista;
    }
    
    public ReservaEntity update(ReservaEntity reserva)
    {
        em.merge(reserva);
        return reserva;
    }
    
    public void delete(long id)
    {    
        ReservaEntity elim = em.find(ReservaEntity.class, id);
        if(elim != null) em.remove(elim);
      
    }
}
