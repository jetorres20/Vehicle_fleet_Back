/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.PracticaEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.PracticaPersistence;
import co.edu.uniandes.csw.automotor.persistence.ProfesorPersistence;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nestor Plata
 */
@Stateless
public class PracticaLogic {
    @Inject
    PracticaPersistence persis;
    @Inject
    ProfesorPersistence persis2;
    
    public PracticaEntity createPractica(PracticaEntity pro)throws BusinessLogicException{
   if(pro.getDescripcion()==null){
       throw new BusinessLogicException("La descripcion de la practica no puede ser null");
   }
   if(pro.getDestino()==null){
       throw new BusinessLogicException("El destino de la practica no puede ser null");
   }
   if(pro.getProfesor()==null){
        throw new BusinessLogicException("La practica debe tener un profesor asignado");
    }
   if(pro.getDuracion()<0){
        throw new BusinessLogicException("La duracion de la practica debe ser positiva");
    }
   if(pro.getDuracion()==null){
        throw new BusinessLogicException("La practica debe tener una duracion estipulada");
    }
   
    if(pro.getDuracion()==null){
        throw new BusinessLogicException("La practica debe tener una duracion estipulada");
    }
    ProfesorEntity buscado=persis2.find(pro.getProfesor().getId());
    if(buscado==null){
        throw new BusinessLogicException("No existe este profesor asociado");
  
    }
  
  
   pro=persis.create(pro);
   return pro;
    }
    
    
       public List<PracticaEntity> getPracticas()
    {
        List<PracticaEntity> practicas = (List<PracticaEntity>) persis.findAll();
        return practicas;
    }
    
    public PracticaEntity getPractica(long id)
    {
        PracticaEntity practica = persis.find(id);
        
        return practica;
    }
    
    public PracticaEntity updatePractica(PracticaEntity prac)
    {
        return persis.update(prac);
    }
    
    public void deletePractica ( long id)
    {
        PracticaEntity eliminado =persis.find(id);
        persis.delete(eliminado.getId());
    }
 
}
