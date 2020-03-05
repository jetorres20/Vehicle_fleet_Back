/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Esteban Torres
 */
public class EstudianteDetailDTO extends EstudianteDTO implements Serializable  {

    private List<ReservaDTO> reservas;
    
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
            
            if (estudianteEntity.getReservas() != null) {
                reservas = new ArrayList();
                for (ReservaEntity reserva : estudianteEntity.getReservas()) {
                     reservas.add(new ReservaDTO(reserva));
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
        
        if (getReservas() != null) {
            List<ReservaEntity> ReservaEntity = new ArrayList<>();
            for (ReservaDTO dtoReserva : getReservas()) {
                ReservaEntity.add(dtoReserva.toEntity());
            }
            estudianteEntity.setReservas(ReservaEntity);
        }
        
        return estudianteEntity;
    }

    /**
     * @return the reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
}
