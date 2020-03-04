/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.PracticaEntity;
import java.io.Serializable;

/**
 *
 * @author Nestor Plata
 */
public class PracticaDTO implements Serializable {
     private String destino;
    private String descripcion;
    private Double tiempoDeDesplazamiento;
    private Double duracion;
    private Long id;
    private ProfesorDetailDTO profesor;

    
    
    
    public PracticaDTO(){
    }
    
    
    public PracticaDTO(PracticaEntity prac){
    if(prac!=null){
        this.descripcion=prac.getDescripcion();
        this.destino=prac.getDestino();
        this.duracion=prac.getDuracion();
        this.tiempoDeDesplazamiento=prac.getTiempoDeDesplazamiento();
        if(this.profesor!=null){
        this.profesor=new ProfesorDetailDTO(prac.getProfesor());
        }
    this.id=prac.getId();
    }
    
    }
    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tiempoDeDesplazamiento
     */
    public Double getTiempoDeDesplazamiento() {
        return tiempoDeDesplazamiento;
    }

    /**
     * @param tiempoDeDesplazamiento the tiempoDeDesplazamiento to set
     */
    public void setTiempoDeDesplazamiento(Double tiempoDeDesplazamiento) {
        this.tiempoDeDesplazamiento = tiempoDeDesplazamiento;
    }

    /**
     * @return the duracion
     */
    public Double getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }
    
    public PracticaEntity toEntity(){
        PracticaEntity entity=new PracticaEntity();
        entity.setDescripcion(this.getDescripcion());
        entity.setDestino(this.getDestino());
        entity.setDuracion(this.getDuracion());
        entity.setTiempoDeDesplazamiento(this.getTiempoDeDesplazamiento());
        entity.setId(this.getId());
        entity.setProfesor(this.getProfesor().toEntity());
        return entity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the profesor
     */
    public ProfesorDetailDTO getProfesor() {
        return profesor;
    }

    /**
     * @param profesor the profesor to set
     */
    public void setProfesor(ProfesorDetailDTO profesor) {
        this.profesor = profesor;
    }
    
}
