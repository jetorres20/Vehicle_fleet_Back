/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.persistence;

import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
@Stateless
public class EstudiantePersistence {
    
     @PersistenceContext(unitName = "automotorPU")
    private EntityManager em;
    
    public EstudianteEntity create (EstudianteEntity estudiante){
    em.persist(estudiante);
    
    return estudiante;
            }
    
    public List<EstudianteEntity> finAll (){
    return em.createQuery("SELECT a FROM EstudianteEntity a").getResultList();
            }
    
    public EstudianteEntity update(EstudianteEntity estudiante){
        
       return em.merge(estudiante);
    }
    
    public void delete(int id){
        EstudianteEntity estudiante = em.find(EstudianteEntity.class, id);
        em.remove(estudiante);
    }
    
    public EstudianteEntity find(long id ){
        EstudianteEntity estudiante = em.find(EstudianteEntity.class, id);
        return estudiante;
        
    }
    
    public void delete ( long id){
        
        EstudianteEntity eliminado = em.find(EstudianteEntity.class, id);
        if(eliminado != null) em.remove(eliminado);
      
    }
    
    
    
}
