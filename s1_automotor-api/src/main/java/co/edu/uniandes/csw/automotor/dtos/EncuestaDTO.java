/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.EncuestaEntity;
import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;

/**
 *
 * @author Juan Esteban Torres
 */
public class EncuestaDTO {

    private ReservaDTO reserva;
    
    public EncuestaDTO() {
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param estudianteEntity: Es la entidad que se va a convertir a DTO
     */
    public EncuestaDTO(EncuestaEntity encuestaEntity) {
//        if (encuestaEntity != null) {
//            this.reserva = new ReservaDTO(encuestaEntity.getReserva());
//        }
    }
    
    
}
