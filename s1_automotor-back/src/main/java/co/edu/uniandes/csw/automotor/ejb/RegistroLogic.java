/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
import co.edu.uniandes.csw.automotor.persistence.RegistroPersistence;
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
    public RegistroEntity createRegistro(RegistroEntity registro) throws Exception {
        if (registro.getPrsc() == null || registro.getPrse() == null
                || registro.getRtm() == null || registro.getSoat() == null) {
            throw new Exception("Las fechas del registro no pueden ser nulas");
        }
        registro = persistence.create(registro);

        return registro;
    }
}
