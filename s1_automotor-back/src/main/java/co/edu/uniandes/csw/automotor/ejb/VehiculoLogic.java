/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.RegistroPersistence;
import co.edu.uniandes.csw.automotor.persistence.VehiculoPersistence;
import static java.lang.Character.isLetter;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Andres Caycedo S.
 */
@Stateless
public class VehiculoLogic {

    @Inject
    private VehiculoPersistence vehiculoPersistence;
    
    @Inject
    private RegistroPersistence registroPersistence;

    public VehiculoEntity createVehiculo(VehiculoEntity vehiculo) throws BusinessLogicException {
        
        if( vehiculo.getRegistro()== null)
        {
            throw new BusinessLogicException("El registro es invalido");
        }
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

        Collection<VehiculoEntity> todos = vehiculoPersistence.finAll();
        for (VehiculoEntity entidad : todos) {
            if (entidad.getPlaca().equals(placa)) {
                throw new BusinessLogicException("No puede haber placas Repetidas");
            }
        }
        if (vehiculo.getCapacidad() <= 0) {
            throw new BusinessLogicException("No puede haber carros con capacidades negativas");

        }
        RegistroEntity regitro =registroPersistence.find(vehiculo.getRegistro().getId());
        if(regitro.getVehiculo()!= null)
        {
            throw new BusinessLogicException("El registro ya esta asociado a un vehiculo");
        }
        vehiculo.setRegistro(regitro);
        regitro.setVehiculo(vehiculo);
        return vehiculoPersistence.create(vehiculo);
    }
    
    private VehiculoEntity crearRegistro(long idVehiculo, long idRegistro)
    {
        RegistroEntity regitro = registroPersistence.find(idRegistro);
        VehiculoEntity vehiculo = vehiculoPersistence.find(idVehiculo);
        vehiculo.setRegistro(regitro);
        return vehiculoPersistence.update(vehiculo);
    }

    public List<VehiculoEntity> getVehiculos()
    {
        List<VehiculoEntity> vehiculos = (List<VehiculoEntity>) vehiculoPersistence.finAll();
        return vehiculos;
    }
    
    public VehiculoEntity getVehiculo(long id)
    {
        VehiculoEntity vehi = vehiculoPersistence.find(id);
        
        return vehi;
    }
    
    public VehiculoEntity updateVehiculo(long vehiID, VehiculoEntity vehiEntity)
    {
        return vehiculoPersistence.update(vehiEntity);
    }
    
    public void deleteVehiculo ( long idEliminable)
    {
        VehiculoEntity eliminado = vehiculoPersistence.find(idEliminable);
        registroPersistence.delete(eliminado.getRegistro().getId());
        vehiculoPersistence.delete(idEliminable);
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
