/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.ProfesorPersistence;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nestor Plata
 */
@Stateless
public class ProfesorLogic {
    @Inject
    ProfesorPersistence persis;
    
    public ProfesorEntity createProfesor(ProfesorEntity pro)throws BusinessLogicException{
   if(pro.getNombre()==null){
       throw new BusinessLogicException("El nombre de profesor no puede ser null");
   }
   if(pro.getIdentificacion()==null){
       throw new BusinessLogicException("El documento del profesor no puede ser null");
   }
   if(findProfesorById(pro.getIdentificacion())){
        throw new BusinessLogicException("Ya hay un profesor registrado con este numero de identificacion");
    }
   if(pro.getIdentificacion()<0){
        throw new BusinessLogicException("La identificacion debe ser positiva");
    }
   
   pro=persis.create(pro);
   return pro;
    }
    
    public boolean findProfesorById(Integer id){
        boolean existe=false;
        Collection<ProfesorEntity>lista=persis.findAll();
        for (ProfesorEntity profesorEntity : lista) {
            if(profesorEntity.getIdentificacion()==id){
                existe=true;
            }
        }
        return existe;
    }
    
       public Collection<ProfesorEntity> getProfesores() {
        Collection<ProfesorEntity> editorials = persis.findAll();
         return editorials;
    }
       
       public ProfesorEntity find(Long id){
           ProfesorEntity pro=persis.find(id);
           return pro;
           
       }
       
          public ProfesorEntity updateProfesor(Long profesorId, ProfesorEntity profesorEntity) {
         ProfesorEntity newEntity = persis.update(profesorEntity);
         return newEntity;
    }
          public void deleteProfesor(Long profesorid){
         persis.delete(profesorid);
          }
          

    
}
