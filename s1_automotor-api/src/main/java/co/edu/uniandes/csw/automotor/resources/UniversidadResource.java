/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.UniversidadDTO;
import co.edu.uniandes.csw.automotor.dtos.UniversidadDetailDTO;
import co.edu.uniandes.csw.automotor.ejb.UniversidadLogic;
import co.edu.uniandes.csw.automotor.entities.UniversidadEntity;
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
 * @author Juan Villamarin
 */
@Path("universidad")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UniversidadResource {
    
    private static final Logger LOGGER = Logger.getLogger(UniversidadResource.class.getSimpleName());
  
    @Inject
    private UniversidadLogic universidadLogic;
      
    @POST
    public UniversidadDTO createUniversidad(UniversidadDTO universidad) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UniversidadResource createUniversidad: input: {0}", universidad);
        UniversidadEntity universidadEntity = universidad.toEntity();
        // Invoca la lógica para crear la universdida nueva
        UniversidadEntity nuevoUniversidadEntity = universidadLogic.createUniversidad(universidadEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        UniversidadDTO nuevoUniversidadDTO = new UniversidadDTO(nuevoUniversidadEntity);
        LOGGER.log(Level.INFO, "UniversidadResource createUniversidad: output: {0}", nuevoUniversidadDTO);
        return nuevoUniversidadDTO;
    }
    
    
    @GET
    public Collection<UniversidadDetailDTO> getUniversidades() {
        LOGGER.info("UniversidadResource getUniversidades: input: void");
        Collection<UniversidadDetailDTO> listaUniversidadDTOs = listEntity2DetailDTO(universidadLogic.getUniversities());
        LOGGER.log(Level.INFO, "UniversidadResource getUniversidades: output: {0}", listaUniversidadDTOs);
        return listaUniversidadDTOs;
    }
    
    @GET
    @Path("{universidadId: \\d+}")
    public UniversidadDetailDTO getUniversidad(@PathParam("universidadId") Long universidadId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "UniversidadResource getUniversidad: input: {0}", universidadId);
        UniversidadEntity universidadEntity = universidadLogic.find(universidadId);
        if (universidadEntity == null) {
            throw new WebApplicationException("El recurso /univesidad/" + universidadId + " no existe.", 404);
        }
        UniversidadDetailDTO detailDTO = new UniversidadDetailDTO(universidadEntity);
        LOGGER.log(Level.INFO,"UniversidadResource getUniversidad: output: {0}", detailDTO);
        return detailDTO;
    }

    @PUT
    @Path("{universidadId: \\d+}")
    public UniversidadDetailDTO upDateUniversidad(@PathParam("universidadId") Long universidadId, UniversidadDetailDTO universidad) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "UniversidadResource upDateUniversidad: input: id:{0} , universidad: {1}", new Object[]{universidadId, universidad});
        universidad.setId(universidadId);
        if (universidadLogic.find(universidadId) == null) {
            throw new WebApplicationException("El recurso /universidad/" + universidadId + " no existe.", 404);
        }
        UniversidadDetailDTO detailDTO = new UniversidadDetailDTO(universidadLogic.upDateUniversity(universidad.toEntity()));
        LOGGER.log(Level.INFO, "UniversidadResource upDateUniversidad: output: {0}", detailDTO);
        return detailDTO;

    }
      
      
    private Collection<UniversidadDetailDTO> listEntity2DetailDTO(Collection<UniversidadEntity> entityList) {
        List<UniversidadDetailDTO> list = new ArrayList<>();
        for (UniversidadEntity entity : entityList) {
            list.add(new UniversidadDetailDTO(entity));
        }
        return list;
    }
    
    @DELETE
    @Path("{universidadId: \\d+}")
    public void deleteFranja(@PathParam("universidadId") Long universidadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UniversidadResource deleteEditorial: input: {0}", universidadId);
        if (universidadLogic.find(universidadId) == null) {
            throw new WebApplicationException("El recurso /universidad/" + universidadId + " no existe.", 404);
        }
        universidadLogic.deleteUniversity(universidadId);
        LOGGER.info("UniversidadResource deleteUniversidad: output: void");
    }
    
}
