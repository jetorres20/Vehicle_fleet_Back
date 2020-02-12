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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Juan Andres Caycedo S.
 */
@Entity
public class RegistroEntity extends BaseEntity implements Serializable  {
    
    //Atributos
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date rtm;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date soat;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date prsc;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date prse;
    
    private Boolean vigente;

    //Asociaciones
    @PodamExclude
    @javax.persistence.OneToOne
    private VehiculoEntity vehiculo;
    //Metodos
    /**
     * @return the rtm
     */
    public Date getRtm() {
        return rtm;
    }

    /**
     * @param rtm the rtm to set
     */
    public void setRtm(Date rtm) {
        this.rtm = rtm;
    }

    /**
     * @return the soat
     */
    public Date getSoat() {
        return soat;
    }

    /**
     * @param soat the soat to set
     */
    public void setSoat(Date soat) {
        this.soat = soat;
    }

    /**
     * @return the prsc
     */
    public Date getPrsc() {
        return prsc;
    }

    /**
     * @param prsc the prsc to set
     */
    public void setPrsc(Date prsc) {
        this.prsc = prsc;
    }

    /**
     * @return the prse
     */
    public Date getPrse() {
        return prse;
    }

    /**
     * @param prse the prse to set
     */
    public void setPrse(Date prse) {
        this.prse = prse;
    }

    /**
     * @return the vigente
     */
    public Boolean isVigente() {
        return vigente;
    }

    /**
     * @param vigente the vigente to set
     */
    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    /**
     * @return the vehiculo
     */
    public VehiculoEntity getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(VehiculoEntity vehiculo) {
        this.vehiculo = vehiculo;
    }
    
}
