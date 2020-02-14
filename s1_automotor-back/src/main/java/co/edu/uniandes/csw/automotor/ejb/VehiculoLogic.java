/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.VehiculoPersistence;
import static java.lang.Character.isLetter;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Andres Caycedo S.
 */
@Stateless
public class VehiculoLogic {

    @Inject
    private VehiculoPersistence persistence;

    public VehiculoEntity createVehiculo(VehiculoEntity vehiculo) throws BusinessLogicException {
        if (vehiculo.getMarca() == null | vehiculo.getModelo() == null
                || vehiculo.getCapacidad() == null || vehiculo.getPlaca() == null) {
            throw new BusinessLogicException("No puedes crear un vehiculo con valores Nulos");
        }
        String placa = vehiculo.getPlaca();
        if (placa.length() != 6) {
            throw new BusinessLogicException("La placa debe tener 6 caracteres");
        }
        if (!isLetter(placa.charAt(0)) || !isLetter(placa.charAt(1)) || !isLetter(placa.charAt(2))) {
            throw new BusinessLogicException("La placa debe tener tres letras al inicio");
        }
        if (!isNumber("" + placa.charAt(3)) || !isNumber("" + placa.charAt(4)) || !isNumber("" + placa.charAt(5))) {
            throw new BusinessLogicException("La placa debe tener tres numero al final");
        }

        Collection<VehiculoEntity> todos = persistence.finAll();
        for (VehiculoEntity entidad : todos) {
            if (entidad.getPlaca().equals(placa)) {
                throw new BusinessLogicException("No puede haber placas Repetidas");
            }
        }
        if (vehiculo.getCapacidad() <= 0) {
            throw new BusinessLogicException("No puede haber carros con capacidades negativas");

        }
        return persistence.create(vehiculo);
    }

    private boolean isNumber(String intento) {
        try {
            Integer.parseInt(intento);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}