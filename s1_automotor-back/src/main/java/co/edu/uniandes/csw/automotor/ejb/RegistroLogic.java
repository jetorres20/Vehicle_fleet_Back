/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.RegistroPersistence;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Andrés Caycedo Serrano.
 */
@Stateless
public class RegistroLogic {

    //Atributos
    @Inject
    private RegistroPersistence persistence;

    //Metodos
    public RegistroEntity createRegistro(RegistroEntity registro) throws BusinessLogicException {
        if (registro.getPrsc() == null || registro.getPrse() == null
                || registro.getRtm() == null || registro.getSoat() == null) {
            throw new BusinessLogicException("Las fechas del registro no pueden ser nulas");
        }
        checkDocuments(registro);
        if (!registro.isVigente()) {
            throw new BusinessLogicException("No puede crear un registro con documentos vencidos");
        }
        registro = persistence.create(registro);

        return registro;
    }

    private void checkDocuments(RegistroEntity registro) {
        Date actual = new Date();
        if (registro.getPrsc().compareTo(actual) < 0
                || registro.getPrse().compareTo(actual) < 0
                || registro.getRtm().compareTo(actual) < 0
                || registro.getPrsc().compareTo(actual) < 0
                || registro.getSoat().compareTo(actual) < 0) {
            registro.setVigente(false);
        } else {
            registro.setVigente(true);
        }
    }

    public RegistroEntity updateSoat(RegistroEntity registro, Date nuevoSoat) throws BusinessLogicException {

        Date hoy = new Date();
        if (nuevoSoat.compareTo(hoy) > 0) {
            registro.setSoat(nuevoSoat);
            persistence.update(registro);
        } else {
            throw new BusinessLogicException("No se puede actualizar un Soat con una fecha vencida");
        }

        return registro;
    }

    public RegistroEntity updateRTM(RegistroEntity registro, Date nuevaRTM) throws BusinessLogicException {

        Date hoy = new Date();
        if (nuevaRTM.compareTo(hoy) > 0) {
            registro.setRtm(nuevaRTM);
            persistence.update(registro);
        } else {
            throw new BusinessLogicException("No se puede actualizar una RTM con una fecha vencida");
        }

        return registro;
    }

    public RegistroEntity updatePRSC(RegistroEntity registro, Date nuevoPRSC) throws BusinessLogicException {

        Date hoy = new Date();
        if (nuevoPRSC.compareTo(hoy) > 0) {
            registro.setPrsc(nuevoPRSC);
            persistence.update(registro);
        } else {
            throw new BusinessLogicException("No se puede actualizar un Prsc con una fecha vencida");
        }

        return registro;
    }

    public RegistroEntity updatePRSE(RegistroEntity registro, Date nuevoPRSE) throws BusinessLogicException {

        Date hoy = new Date();
        if (nuevoPRSE.compareTo(hoy) > 0) {
            registro.setPrse(nuevoPRSE);
            persistence.update(registro);
        } else {
            throw new BusinessLogicException("No se puede actualizar un PRSE con una fecha vencida");
        }

        return registro;
    }
}
