/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo Laguna G
 */
@Stateless
public class ReservaLogic {
 
    @Inject
    private ReservaPersistence persistence;
    
    public ReservaEntity createReserva(ReservaEntity reserva) throws BusinessLogicException
    {
        if(reserva.getFechaReserva() == null)
        {
            throw new BusinessLogicException("La fecha de reserva está vacía.");
        }
        else if(reserva.getFechaServicio() == null)
        {
            throw new BusinessLogicException("La fecha de servicio está vacía.");
        }
        else if(reserva.getFechaReserva().compareTo(reserva.getFechaServicio()) > 0)
        {
            throw new BusinessLogicException("La fecha de rserva no puede ser posterior a la fecha del servicio.");
        }
        else if(persistence.numberOfElements() >= 1)
        {
            if(reservaDuplicada(persistence.finAll(), reserva))
            {
                throw new BusinessLogicException("No se puede hacer una reserva de dos servicios para el mismo día, la misma hora y con el mismo vehículo.");
            }
        }
        
        reserva = persistence.create(reserva);
        return reserva;
    }
    
    public ReservaEntity createReservaContext(ReservaEntity reserva)
    {
        reserva = persistence.create(reserva);
        return reserva;
    }
    
    public Boolean reservaDuplicada(Collection<ReservaEntity> lista, ReservaEntity pReserva)
    {
        ArrayList lista_array = new ArrayList(lista);
        for(int i=0; i < lista.size(); i++)
        {
            ReservaEntity reserva = (ReservaEntity) lista_array.get(i);
            if(reserva.getFechaServicio().compareTo(pReserva.getFechaServicio()) == 0 && reserva.getVehiculo().equals(pReserva.getVehiculo()))
            {
                return true;
            }
        }
        return false;
    }
    
    public ReservaPersistence getReservaPersistence()
    {
        return this.persistence;
    }
}
