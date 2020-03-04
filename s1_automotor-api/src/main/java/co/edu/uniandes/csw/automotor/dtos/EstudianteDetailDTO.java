/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Esteban Torres
 */
public class EstudianteDetailDTO extends EstudianteDTO implements Serializable  {

    /**
     * @return the profesores
     */
    public List<ProfesorDTO> getProfesores() {
        return profesores;
    }

    /**
     * @param profesores the profesores to set
     */
    public void setProfesores(List<ProfesorDTO> profesores) {
        this.profesores = profesores;
    }
    
    private List<ProfesorDTO> profesores;

    public EstudianteDetailDTO() {
    }
    
    public EstudianteDetailDTO(EstudianteEntity estudianteEntity) {
        super(estudianteEntity);
        if (estudianteEntity!= null) {
            if (estudianteEntity.getProfesores() != null) {
                profesores = new ArrayList();
                for (ProfesorEntity profe : estudianteEntity.getProfesores()) {
                     profesores.add(new ProfesorDTO(profe));
                }
            }
        }
    }
    
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public EstudianteEntity toEntity() {
        EstudianteEntity estudianteEntity = super.toEntity();
        if (getProfesores() != null) {
            List<ProfesorEntity> profesoresEntity = new ArrayList<>();
            for (ProfesorDTO dtoProfesor : getProfesores()) {
                profesoresEntity.add(dtoProfesor.toEntity());
            }
            estudianteEntity.setProfesores(profesoresEntity);
        }
        
        return estudianteEntity;
    }
}
