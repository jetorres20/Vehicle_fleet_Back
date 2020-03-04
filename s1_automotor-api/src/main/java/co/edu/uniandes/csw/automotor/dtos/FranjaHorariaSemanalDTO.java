/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.FranjaHorariaSemanalEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Juan Villamarin
 */
public class FranjaHorariaSemanalDTO implements Serializable{
    
    private Integer weekDay;
    
    private Date startHour;
    
    private Date finishHour;
    
    private ConductorDTO conductor;
    
    public FranjaHorariaSemanalDTO(){
    }
    
    public FranjaHorariaSemanalDTO(FranjaHorariaSemanalEntity franja){
        if(franja!=null){
            this.weekDay=franja.getWeekDay();
            this.startHour=franja.getStartHour();
            this.finishHour=franja.getFinishHour();
            this.conductor=new ConductorDTO(franja.getConductor());
        }
    }
    
    public FranjaHorariaSemanalEntity toEntity(){
        FranjaHorariaSemanalEntity entity=new FranjaHorariaSemanalEntity();
        entity.setConductor(conductor.toEntity());
        entity.setFinishHour(finishHour);
        entity.setStartHour(startHour);
        entity.setWeekDay(weekDay);
        
        return entity;
    }
    
    /**
     * @return the weekDay
     */
    public Integer getWeekDay() {
        return weekDay;
    }

    /**
     * @param weekDay the weekDay to set
     */
    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    /**
     * @return the startHour
     */
    public Date getStartHour() {
        return startHour;
    }

    /**
     * @param startHour the startHour to set
     */
    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }

    /**
     * @return the finishHour
     */
    public Date getFinishHour() {
        return finishHour;
    }

    /**
     * @param finishHour the finishHour to set
     */
    public void setFinishHour(Date finishHour) {
        this.finishHour = finishHour;
    }

    /**
     * @return the conductor
     */
    public ConductorDTO getConductor() {
        return conductor;
    }

    /**
     * @param conductor the conductor to set
     */
    public void setConductor(ConductorDTO conductor) {
        this.conductor = conductor;
    }
}
