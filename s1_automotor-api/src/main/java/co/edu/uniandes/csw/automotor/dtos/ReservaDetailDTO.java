/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateo Laguna G.
 */
public class ReservaDetailDTO extends ReservaDTO implements Serializable{
    
    /**
     * relación  cero o muchos estudiantes 
     **/
    private List<EstudianteDTO> estudiantes;
    
     public ReservaDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param reservaEntity La entidad de la cual se construye el DTO
     */
    public ReservaDetailDTO(ReservaEntity reservaEntity) {
        super(reservaEntity);
        if (reservaEntity.getEstudiantes() != null) {
            ArrayList estudiantes = new ArrayList<>();
            for (EstudianteEntity entityEstudiante : reservaEntity.getEstudiantes()) {
                estudiantes.add(new EstudianteDTO(entityEstudiante));
            }
        }
    }
    
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa la reserva
     */
    @Override
    public ReservaEntity toEntity() {
        ReservaEntity reservaEntity = super.toEntity();
        if (estudiantes != null) {
            List<EstudianteEntity> estudiantesEntity = new ArrayList<>();
            for (EstudianteDTO dtoEstudiante : getEstudiantes()) {
                estudiantesEntity.add(dtoEstudiante.toEntity());
            }
            reservaEntity.setEstudiantes(estudiantesEntity);
        }
        return reservaEntity;
    }
    
    /**
     * Devuelve los estudiantes asociadas a esta Reserva
     *
     * @return Lista de DTOs de Estudiantes
     */
    public List<EstudianteDTO> getEstudiantes() {
        return estudiantes;
    }

    /**
     * Modifica los estudiantes de esta reserva.
     *
     * @param estudiantes Los nuevos estudiantes.
     */
    public void setEstudiantes(List<EstudianteDTO> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
