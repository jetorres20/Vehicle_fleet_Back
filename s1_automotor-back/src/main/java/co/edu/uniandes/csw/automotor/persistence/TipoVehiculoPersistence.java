/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.TipoVehiculoEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mateo Laguna
 */
@Stateless
public class TipoVehiculoPersistence 
{
    @PersistenceContext(unitName = "automotorPU")
    private EntityManager em;
    
    public TipoVehiculoEntity create(TipoVehiculoEntity tipoVehiculo)
    {
        em.persist(tipoVehiculo);
        return tipoVehiculo;        
    }
    
    public TipoVehiculoEntity find(long id)
    {
        TipoVehiculoEntity tp_veh = em.find(TipoVehiculoEntity.class, id);
        return tp_veh;
    }
    
    public Collection<TipoVehiculoEntity> finAll()
    {
        Collection<TipoVehiculoEntity> lista = em.createQuery("SELECT e FROM TipoVehiculoEntity e").getResultList();
        return lista;
    }
    
    public TipoVehiculoEntity update(TipoVehiculoEntity tipoVehiculo)
    {
        em.merge(tipoVehiculo);
        return tipoVehiculo;
    }
    
    public void delete(long id)
    {    
        TipoVehiculoEntity elim = em.find(TipoVehiculoEntity.class, id);
        if(elim != null) em.remove(elim);
      
    }
}
