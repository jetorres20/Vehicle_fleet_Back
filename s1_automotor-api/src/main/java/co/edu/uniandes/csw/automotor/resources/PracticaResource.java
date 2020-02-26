/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.PracticaDTO;
import co.edu.uniandes.csw.automotor.dtos.ProfesorDTO;
import co.edu.uniandes.csw.automotor.ejb.PracticaLogic;
import co.edu.uniandes.csw.automotor.ejb.ProfesorLogic;
import co.edu.uniandes.csw.automotor.entities.PracticaEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Collection;
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
 * @author Nestor Plata
 */
@Path("practicas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PracticaResource {
    private static final Logger LOGGER = Logger.getLogger(PracticaResource.class.getSimpleName());
     @Inject
    private PracticaLogic practicaLogic;
    
      @POST
    public PracticaDTO createPractica(PracticaDTO practica) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PracticaResource createPractica: input: {0}", practica);
        PracticaEntity practicaEntity = practica.toEntity();
        // Invoca la lógica para crear la editorial nueva
        PracticaEntity nuevaPracticaEntity = practicaLogic.createPractica(practicaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        PracticaDTO nuevaPracticaDTO = new PracticaDTO(nuevaPracticaEntity);
        LOGGER.log(Level.INFO, "ProfesorResource createProfesor: output: {0}", nuevaPracticaDTO);
        return nuevaPracticaDTO;
    }
    
           @GET
    public Collection<PracticaDTO> getPracticas() {
        LOGGER.info("PracticaResource getPracticas: input: void");
        Collection<PracticaDTO> listaPracticas = listEntity2DTO(practicaLogic.getPracticas());
        LOGGER.log(Level.INFO, "PracticaResource getPracticas: output: {0}", listaPracticas);
        return listaPracticas;
    }
        @GET
    @Path("{practicaId: \\d+}")
    public PracticaDTO getReview( @PathParam("practicaId") Long practicaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Practica Resource getPractica: input: {0}", practicaId);
        PracticaEntity entity = practicaLogic.getPractica(practicaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /practica/" + practicaId +  " no existe.", 404);
        }
        PracticaDTO practicaDTO = new PracticaDTO(entity);
        LOGGER.log(Level.INFO, "Practica Resource getPractica: output: {0}", practicaDTO);
        return practicaDTO;
    }
    
    
     @DELETE
    @Path("{practicaId: \\d+}")
    public void deleteReview(@PathParam("practicaId") Long practicaId) throws BusinessLogicException {
        PracticaEntity entity = practicaLogic.getPractica(practicaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /practicas/" + practicaId +" no existe.", 404);
        }
        practicaLogic.deletePractica(practicaId);
    }

    @PUT
    @Path("{practicaId: \\d+}")
    public PracticaDTO updatePractica(@PathParam("practicaId") Long practicaId,PracticaDTO practica) throws BusinessLogicException {
        if (practicaId.equals(practica.getId())) {
            throw new BusinessLogicException("Los ids del Review no coinciden.");
        }
        PracticaEntity entity = practicaLogic.getPractica(practicaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + practicaId +  " no existe.", 404);

        }
        PracticaDTO practicaDTO = new PracticaDTO(practicaLogic.updatePractica(practica.toEntity()));
        LOGGER.log(Level.INFO, "ReviewResource updateReview: output:{0}", practicaDTO);
        return practicaDTO;

    }
        private List<PracticaDTO> listEntity2DTO(Collection<PracticaEntity> entityList) {
        List<PracticaDTO> list = new ArrayList<PracticaDTO>();
        for (PracticaEntity entity : entityList) {
            list.add(new PracticaDTO(entity));
        }
        return list;
    }
}
