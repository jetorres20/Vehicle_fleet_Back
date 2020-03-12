/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.entities.EncuestaEntity;
import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.entities.PracticaEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import co.edu.uniandes.csw.automotor.entities.TipoVehiculoEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.VehiculoPersistence;
import co.edu.uniandes.csw.automotor.persistence.ReservaPersistence;
//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
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
    
     @Inject
    private VehiculoPersistence vehiculoPersistence;
    
    private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getSimpleName());
    
    public ReservaEntity createReserva(ReservaEntity reserva) throws BusinessLogicException
    {
        if(reserva.getVehiculo()!=null)
        {
            VehiculoEntity vehiculo = vehiculoPersistence.find(reserva.getVehiculo().getId());
            reserva.setVehiculo(vehiculo);
        }
        if(reserva.getFechaReserva() == null)
        {
            throw new BusinessLogicException("La fecha de reserva está vacía.");
        }
        if(reserva.getFechaServicio() == null)
        {
            throw new BusinessLogicException("La fecha de servicio está vacía.");
        }
        else if(reserva.getFechaReserva().compareTo(reserva.getFechaServicio()) > 0)
        {
            throw new BusinessLogicException("La fecha de reserva no puede ser posterior a la fecha del servicio.");
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
    
    /**
     *
     * @return
     */
    public List<ReservaEntity> getReservas()
    {
        List<ReservaEntity> reservas = (List<ReservaEntity>) persistence.finAll();
        return reservas;
    }
    
    public ReservaEntity updateReserva(Long reservaId, ReservaEntity reservaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la reserva con id = {0}", reservaId);
        ReservaEntity newReservaEntity = persistence.update(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la reserva con id = {0}", reservaId);
        return newReservaEntity;
    }
    
    public ReservaEntity getReserva(Long reservaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la Reserva con id = {0}", reservaId);
        ReservaEntity reservaEntity = persistence.find(reservaId);
        if (reservaEntity == null) {
            LOGGER.log(Level.SEVERE, "La Reserva con el id = {0} no existe", reservaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la Reserva con id = {0}", reservaId);
        return reservaEntity;
    }
    
    public Boolean reservaDuplicada(Collection<ReservaEntity> lista, ReservaEntity pReserva)
    {
        ArrayList lista_array = new ArrayList(lista);
        for(int i=0; i < lista.size(); i++)
        {
            ReservaEntity reserva = (ReservaEntity) lista_array.get(i);
            if(reserva.getFechaServicio().compareTo(pReserva.getFechaServicio()) == 0)
            {
                if(reserva.getVehiculo()!= null && pReserva.getVehiculo()!=null)
                {
                    if(reserva.getVehiculo().equals(pReserva.getVehiculo()))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public ReservaPersistence getReservaPersistence()
    {
        return this.persistence;
    }
    
    public void removeReserva(Long reservaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Reserva con id = {0}", reservaId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        ReservaEntity reserva = getReserva(reservaId);
        VehiculoEntity vehiculo = reserva.getVehiculo();
        PracticaEntity practica = reserva.getPractica();
        ProfesorEntity profesor = reserva.getProfesor();
        EncuestaEntity encuesta = reserva.getEncuesta();
        List<EstudianteEntity> estudiantes = (List<EstudianteEntity>) reserva.getEstudiantes();
        ConductorEntity conductor = reserva.getConductor();
        
        if(vehiculo != null){
            throw new BusinessLogicException("No se puede borrar la reserva con id = " + reservaId + " porque tiene un Vehiculo asociado");
        }
        else if(practica != null){
            throw new BusinessLogicException("No se puede borrar la reserva con id = " + reservaId + " porque tiene una Practica asociada");
        }
        else if(profesor!= null){
            throw new BusinessLogicException("No se puede borrar la reserva con id = " + reservaId + " porque tiene un Profesor asociado");
        }
        else if(encuesta!= null){
            throw new BusinessLogicException("No se puede borrar la reserva con id = " + reservaId + " porque tiene una Encuesta asociada");
        }
        else if(!estudiantes.isEmpty()){
            throw new BusinessLogicException("No se puede borrar la reserva con id = " + reservaId + " porque tiene al menos un estudiante asociado");
        }
        else if(conductor!=null){
            throw new BusinessLogicException("No se puede borrar la reserva con id = " + reservaId + " porque tiene un Conductor asociado");
        }
            
        persistence.delete(reservaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la reserva con id = {0}", reservaId);
    }
    
    
}
