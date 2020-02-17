/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.EncuestaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.EncuestaPersistence;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Juan Esteban Torres
 */
public class EncuestaLogic {
    
     @Inject
    private EncuestaPersistence persistence;
    
    public EncuestaEntity createEncuesta(EncuestaEntity encuesta) throws BusinessLogicException{
        if(encuesta.getCalificacion()==null)
            throw new BusinessLogicException("La calificaciòn de la encuesta está vacía");
        else if(encuesta.getProfesor()==null)
            throw new BusinessLogicException("No hay profesor asociado a la encuesta");
        else if(encuesta.getReserva()==null)
            throw new BusinessLogicException("No hay reserva asociada a la encuesta");
        
        return persistence.create(encuesta);
    }
    
    
    
    public List<EncuestaEntity> getEncuestas()
    {
        List<EncuestaEntity> encuestas = (List<EncuestaEntity>) persistence.finAll();
        return encuestas;
    }
    
    public EncuestaEntity getEncuesta(long id)
    {
        EncuestaEntity est = persistence.find(id);
        
        return est;
    }
    
    public EncuestaEntity updateEncuesta(long vehiID, EncuestaEntity vehiEntity)
    {
        return persistence.update(vehiEntity);
    }
    
    public void deleteEncuesta ( long idEliminar)
    {
        persistence.delete(idEliminar);
    }
}
