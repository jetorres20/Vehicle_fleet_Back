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
  
   pro=persis.create(pro);
   return pro;
    }
 
}
