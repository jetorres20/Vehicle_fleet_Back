/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.ReservaDTO;
import co.edu.uniandes.csw.automotor.dtos.TipoVehiculoDTO;
import co.edu.uniandes.csw.automotor.ejb.ReservaLogic;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import co.edu.uniandes.csw.automotor.entities.TipoVehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Mateo Laguna G.
 */
@Path("reservas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReservaResource {
    
    private static final Logger LOGGER = Logger.getLogger(ReservaResource.class.getName());
    
    @Inject
    ReservaLogic reservaLogic;
    
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ReservaResource createReserva: input: {0}", reserva);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ReservaEntity reservaEntity = reserva.toEntity();
        // Invoca la lógica para crear la reserva nueva
        ReservaEntity nuevaReservaEntity = reservaLogic.createReserva(reservaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ReservaDTO nuevaReservaDTO = new ReservaDTO(nuevaReservaEntity);
        LOGGER.log(Level.INFO, "ReservaResource createReserva: output: {0}", nuevaReservaDTO);
        return nuevaReservaDTO;
    }
    
    @GET
    public List<ReservaDTO> getReservas() {
        LOGGER.info("ReservaResource getReservas: input: void");
        List<ReservaDTO> listaReservas = listEntity2DTO(reservaLogic.getReservas());
        LOGGER.log(Level.INFO, "ReservaResource getReservas: output: {0}", listaReservas);
        return listaReservas;
    }
    
    @GET
    @Path("{reservaId: \\d+}")
    public ReservaDTO getReserva(@PathParam("reservaId") Long reservaId) throws WebApplicationException {

        ReservaEntity reservaEntity = reservaLogic.getReserva(reservaId);
        if (reservaEntity == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        ReservaDTO detailDTO = new ReservaDTO(reservaEntity);
        return detailDTO;
    }
    
     private List<ReservaDTO> listEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDTO(entity));
        }
        return list;
    }
     
    @PUT
    @Path("{reservaId: \\d+}")
    public ReservaDTO updateReserva(@PathParam("reservaId") Long reservaId, ReservaDTO reserva) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ReservaResource updateReserva: input: reservaId: {0} , reserva: {1}", new Object[]{reservaId, reserva});
        reserva.setId(reservaId);
        if (reservaLogic.getReserva(reservaId) == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        ReservaDTO reservaDTO = new ReservaDTO(reservaLogic.updateReserva(reservaId, reserva.toEntity()));
        LOGGER.log(Level.INFO, "ReservaResource updateReserva: output: {0}", reservaDTO);
        return reservaDTO;
    }
    
    @DELETE
    @Path("{reservaId: \\d+}")
    public void deleteReserva(@PathParam("reservaId") Long reservaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ReservaResource deleteReserva: input: {0}", reservaId);
        ReservaEntity entity = reservaLogic.getReserva(reservaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        reservaLogic.removeReserva(reservaId);
        LOGGER.info("ReservaResource deleteReserva: output: void");
    }
    
}
