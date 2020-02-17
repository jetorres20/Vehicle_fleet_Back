/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.persistence.EstudiantePersistence;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Juan Esteban Torres
 */
public class EstudianteLogic {
    
    @Inject
    private EstudiantePersistence persistence;
    
    public EstudianteEntity createEstudiante(EstudianteEntity estudiante) throws BusinessLogicException{
        if(estudiante.getCodigo()==null)
            throw new BusinessLogicException("El codigo del estudiante está vacío");
        else if(estudiante.getName()==null)
            throw new BusinessLogicException("El nombre del estudiante está vacío");
        
        
        Collection<EstudianteEntity> todos = persistence.finAll();
        for (EstudianteEntity entidad : todos) {
            if (entidad.getCodigo() == estudiante.getCodigo()) {
                throw new BusinessLogicException("No puede haber estudiantes con el mismo código");
            }
        }
        
        return persistence.create(estudiante);
    }
    
    
    
    public List<EstudianteEntity> getEstudiantes()
    {
        List<EstudianteEntity> estudiantes = (List<EstudianteEntity>) persistence.finAll();
        return estudiantes;
    }
    
    public EstudianteEntity getEstudiante(long id)
    {
        EstudianteEntity est = persistence.find(id);
        
        return est;
    }
    
    public EstudianteEntity updateEstudiante(long vehiID, EstudianteEntity vehiEntity)
    {
        return persistence.update(vehiEntity);
    }
    
    public void deleteEstudiante ( long idEliminar)
    {
        persistence.delete(idEliminar);
    }
    
    
    
    
    
    
}
