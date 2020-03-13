/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.EncuestaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Esteban Torres
 */
@Stateless
public class EncuestaPersistence {
    
    @PersistenceContext(unitName = "automotorPU")
    private EntityManager em;
    
    public EncuestaEntity create (EncuestaEntity encuesta){
    em.persist(encuesta);
    
    return encuesta;
            }
    
    public List<EncuestaEntity> finAll (){
    return em.createQuery("SELECT a FROM EncuestaEntity a").getResultList();
            }
    
    public EncuestaEntity update(EncuestaEntity encuesta){
        
       return em.merge(encuesta);
    }
    
    public void delete(int id){
        EncuestaEntity encuesta = em.find(EncuestaEntity.class, id);
        em.remove(encuesta);
    }
    
    public EncuestaEntity find(long id ){
        EncuestaEntity encuesta = em.find(EncuestaEntity.class, id);
        return encuesta;
        
    }
    
    public void delete ( long id){
        
        EncuestaEntity eliminado = em.find(EncuestaEntity.class, id);
        if(eliminado != null) em.remove(eliminado);
      
    }
}
