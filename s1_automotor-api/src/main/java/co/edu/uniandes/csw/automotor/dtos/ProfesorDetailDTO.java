/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.PracticaEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Nestor Plata
 */
public class ProfesorDetailDTO extends ProfesorDTO implements Serializable{
   
    private List<PracticaDTO>practicas;
    
 
   public ProfesorDetailDTO(ProfesorEntity profesorEntity) {
        super(profesorEntity);
        if (profesorEntity!= null) {
            if (profesorEntity.getPracticas() != null&&profesorEntity.getPracticas().size()>0) {
                practicas = new ArrayList<>();
                for (int i=0;i<profesorEntity.getPracticas().size();i++) {
                     System.out.println(profesorEntity.getPracticas().size());
                     practicas.add(new PracticaDTO(profesorEntity.getPracticas().get(i)));
                }
            }
        }
    }
   
      public ProfesorDetailDTO(){
    }

    /**
     * @return the practicas
     */
    public List<PracticaDTO> getPracticas() {
        return practicas;
    }

    /**
     * @param practicas the practicas to set
     */
    public void setPracticas(List<PracticaDTO> practicas) {
        this.practicas = practicas;
    }
    
    public ProfesorEntity toEntity(){
       ProfesorEntity entity = super.toEntity();
        if (practicas != null) {
            ArrayList<PracticaEntity> booksEntity = new ArrayList<>();
            for (PracticaDTO dtoPractica : practicas) {
                booksEntity.add(dtoPractica.toEntity());
            }
            entity.setPracticas(booksEntity);
        }
     return entity;   
    }
          
}
