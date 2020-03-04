/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.entities.UniversidadEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Villamarin
 */
public class UniversidadDetailDTO extends UniversidadDTO implements Serializable{
    
    private List<ProfesorDTO> profesores;
    
    private List<EstudianteDTO> estudiantes;
    
    public UniversidadDetailDTO(UniversidadEntity universidad){
        super(universidad);
        if(universidad!=null){
            if(universidad.getProfesores()!=null){
                profesores=new ArrayList<>();
                for(ProfesorEntity profesor:universidad.getProfesores()){
                    profesores.add(new ProfesorDTO(profesor));
                }
            }
            if(universidad.getEstudiantes()!=null){
                estudiantes=new ArrayList<>();
                for(EstudianteEntity estudiante:universidad.getEstudiantes()){
                    estudiantes.add(new EstudianteDTO(estudiante));
                }
            }
        }
    }
    
    public UniversidadDetailDTO(){
    }
    
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
    
    public UniversidadEntity toEntity(){
        UniversidadEntity uni=super.toEntity();
        if(profesores!=null){
            List<ProfesorEntity> booksEntity=new ArrayList<>();
            for(ProfesorDTO profe:profesores){
                booksEntity.add(profe.toEntity());
            }
            uni.setProfesores(booksEntity);
        }
        if(estudiantes!=null){
            List<EstudianteEntity> bookEn=new ArrayList<>();
            for(EstudianteDTO estu:estudiantes){
                bookEn.add(estu.toEntity());
            }
            uni.setEstudiantes(bookEn);
        }
        return uni;
    }
    
    
    
}
