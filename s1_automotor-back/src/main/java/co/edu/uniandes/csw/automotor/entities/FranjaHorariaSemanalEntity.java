/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.entities;

import co.edu.uniandes.csw.automotor.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Juan Villamarin
 */
@Entity
public class FranjaHorariaSemanalEntity extends BaseEntity implements Serializable{
    
    private Integer weekDay;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date startHour;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date finishHour;
   
    @PodamExclude
    @ManyToOne()
    private ConductorEntity conductor;

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
    public ConductorEntity getConductor() {
        return conductor;
    }

    /**
     * @param conductor the conductor to set
     */
    public void setConductor(ConductorEntity conductor) {
        this.conductor = conductor;
    }
    
}
