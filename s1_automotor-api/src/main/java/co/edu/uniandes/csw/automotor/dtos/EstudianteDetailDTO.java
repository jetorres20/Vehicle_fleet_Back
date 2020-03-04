/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Esteban Torres
 */
public class EstudianteDetailDTO extends EstudianteDTO {
    
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
    
    
}
