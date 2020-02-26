/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.dtos;

import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Juan Andres Caycedo S
 */
public class RegistroDTO implements Serializable {

    private Long id;
    private Date rtm;
    private Date soat;
    private Date prsc;
    private Date prse;
    private Boolean vigente;

    public RegistroDTO() {

    }

    public RegistroDTO(RegistroEntity registro) {
        if (registro != null) {
            this.soat = registro.getSoat();
            this.prsc = registro.getPrsc();
            this.prse = registro.getPrse();
            this.rtm = registro.getRtm();
            this.vigente = registro.isVigente();
            this.id = registro.getId();
        }
    }

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
    public Boolean getVigente() {
        return vigente;
    }

    /**
     * @param vigente the vigente to set
     */
    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public RegistroEntity toEntity() {
        RegistroEntity entidad = new RegistroEntity();
        entidad.setSoat(this.getSoat());
        entidad.setPrsc(this.getPrsc());
        entidad.setPrse(this.getPrse());
        entidad.setRtm(this.getRtm());
        entidad.setVigente(this.getVigente());
        entidad.setId(this.getId());
        
        return entidad;
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
}
