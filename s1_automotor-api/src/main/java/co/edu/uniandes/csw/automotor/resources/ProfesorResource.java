/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.ProfesorDTO;
import co.edu.uniandes.csw.automotor.dtos.ProfesorDetailDTO;
import co.edu.uniandes.csw.automotor.ejb.ProfesorLogic;
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
@Path("profesores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProfesorResource {
     private static final Logger LOGGER = Logger.getLogger(ProfesorResource.class.getSimpleName());
      @Inject
    private ProfesorLogic profesorLogic;
      
         @POST
    public ProfesorDTO createProfesor(ProfesorDTO profesor) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProfesorResource createProfesor: input: {0}", profesor);
        ProfesorEntity profesorEntity = profesor.toEntity();
        // Invoca la lógica para crear la editorial nueva
        ProfesorEntity nuevoProfesorEntity = profesorLogic.createProfesor(profesorEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ProfesorDTO nuevoProfesorDTO = new ProfesorDTO(nuevoProfesorEntity);
        LOGGER.log(Level.INFO, "ProfesorResource createProfesor: output: {0}", nuevoProfesorDTO);
        return nuevoProfesorDTO;
    }
    
    
       @GET
    public Collection<ProfesorDetailDTO> getProfesores() {
        LOGGER.info("ProfesorResource getProfesor: input: void");
        Collection<ProfesorDetailDTO> listaEditoriales = listEntity2DetailDTO(profesorLogic.getProfesores());
        LOGGER.log(Level.INFO, "EditorialResource getEditorials: output: {0}", listaEditoriales);
        return listaEditoriales;
    }
    
    @GET
    @Path("{profesorsId: \\d+}")
    public ProfesorDetailDTO getEditorial(@PathParam("profesorsId") Long profesorsId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "ProfesorResource getProfesor: input: {0}", profesorsId);
        ProfesorEntity profesorEntity = profesorLogic.find(profesorsId);
        if (profesorEntity == null) {
            throw new WebApplicationException("El recurso /profesors/" + profesorsId + " no existe.", 404);
        }
        ProfesorDetailDTO detailDTO = new ProfesorDetailDTO(profesorEntity);
        LOGGER.log(Level.INFO,"Profesor Resource getProfesor: output: {0}", detailDTO);
        return detailDTO;
    }

    @PUT
    @Path("{ProfesorsId: \\d+}")
    public ProfesorDetailDTO updateProfesor(@PathParam("profesorsId") Long profesorsId, ProfesorDetailDTO profesor) throws WebApplicationException {
        LOGGER.log(Level.INFO, "ProfesorResource updateProfesor: input: id:{0} , profesor: {1}", new Object[]{profesorsId, profesor});
        profesor.setId(profesorsId);
        if (profesorLogic.find(profesorsId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + profesorsId + " no existe.", 404);
        }
        ProfesorDetailDTO detailDTO = new ProfesorDetailDTO(profesorLogic.updateProfesor(profesorsId, profesor.toEntity()));
        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: output: {0}", detailDTO);
        return detailDTO;

    }
      
      
          private Collection<ProfesorDetailDTO> listEntity2DetailDTO(Collection<ProfesorEntity> entityList) {
        List<ProfesorDetailDTO> list = new ArrayList<>();
        for (ProfesorEntity entity : entityList) {
            list.add(new ProfesorDetailDTO(entity));
        }
        return list;
    }
    @DELETE
    @Path("{profesorsId: \\d+}")
    public void deleteProfesor(@PathParam("profesorsId") Long profesorsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource deleteEditorial: input: {0}", profesorsId);
        if (profesorLogic.find(profesorsId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + profesorsId + " no existe.", 404);
        }
        profesorLogic.deleteProfesor(profesorsId);
        LOGGER.info("EditorialResource deleteEditorial: output: void");
    }
    
    
      
}
