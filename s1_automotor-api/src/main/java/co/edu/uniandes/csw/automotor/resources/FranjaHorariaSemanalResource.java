/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.FranjaHorariaSemanalDTO;
import co.edu.uniandes.csw.automotor.ejb.FranjaHorariaSemanalLogic;
import co.edu.uniandes.csw.automotor.entities.FranjaHorariaSemanalEntity;
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
@Path("franja")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FranjaHorariaSemanalResource {
    
    private static final Logger LOGGER = Logger.getLogger(FranjaHorariaSemanalResource.class.getSimpleName());
  
    @Inject
    private FranjaHorariaSemanalLogic franjaLogic;
      
    @POST
    public FranjaHorariaSemanalDTO createFranja(FranjaHorariaSemanalDTO franaja) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FranjaHorariaSemanalResource createFranja: input: {0}", franaja);
        FranjaHorariaSemanalEntity franjaEntity = franaja.toEntity();
        // Invoca la lógica para crear la franja nueva
        FranjaHorariaSemanalEntity nuevoFranjaEntity = franjaLogic.createFranjaHorariaSemanal(franjaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        FranjaHorariaSemanalDTO nuevoFranjaDTO = new FranjaHorariaSemanalDTO(nuevoFranjaEntity);
        LOGGER.log(Level.INFO, "FranjaHorariaSemanalResource createFranja: output: {0}", nuevoFranjaDTO);
        return nuevoFranjaDTO;
    }
    
    
    @GET
    public Collection<FranjaHorariaSemanalDTO> getFranjas() {
        LOGGER.info("FranjaHorariaSemanalResource getFranjas: input: void");
        Collection<FranjaHorariaSemanalDTO> listaFranjaDTOs = listEntity2DetailDTO(franjaLogic.getFranjas());
        LOGGER.log(Level.INFO, "FranjaHorariaSemanalResource getFranjas: output: {0}", listaFranjaDTOs);
        return listaFranjaDTOs;
    }
    
    @GET
    @Path("{franjaId: \\d+}")
    public FranjaHorariaSemanalDTO getFranja(@PathParam("franjaId") Long franjaId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "FranjaHorariaSemanalResource getFranja: input: {0}", franjaId);
        FranjaHorariaSemanalEntity franjaEntity = franjaLogic.find(franjaId);
        if (franjaEntity == null) {
            throw new WebApplicationException("El recurso /franja/" + franjaId + " no existe.", 404);
        }
        FranjaHorariaSemanalDTO detailDTO = new FranjaHorariaSemanalDTO(franjaEntity);
        LOGGER.log(Level.INFO,"FranjaHorariaSemanalResource getFranja: output: {0}", detailDTO);
        return detailDTO;
    }

    @PUT
    @Path("{franjaId: \\d+}")
    public FranjaHorariaSemanalDTO upDateFranja(@PathParam("franjaId") Long franjaId, FranjaHorariaSemanalDTO franja) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "FranjaHorariaSemanalResource upDateFranja: input: id:{0} , franja: {1}", new Object[]{franjaId, franja});
        franja.setId(franjaId);
        if (franjaLogic.find(franjaId) == null) {
            throw new WebApplicationException("El recurso /universidad/" + franjaId + " no existe.", 404);
        }
        FranjaHorariaSemanalDTO detailDTO = new FranjaHorariaSemanalDTO(franjaLogic.upDateFranja(franja.toEntity()));
        LOGGER.log(Level.INFO, "FranjaHorariaSemanalResource upDateFranja: output: {0}", detailDTO);
        return detailDTO;

    }
      
      
    private Collection<FranjaHorariaSemanalDTO> listEntity2DetailDTO(Collection<FranjaHorariaSemanalEntity> entityList) {
        List<FranjaHorariaSemanalDTO> list = new ArrayList<>();
        for (FranjaHorariaSemanalEntity entity : entityList) {
            list.add(new FranjaHorariaSemanalDTO(entity));
        }
        return list;
    }
    
    @DELETE
    @Path("{franjaId: \\d+}")
    public void deleteFranja(@PathParam("franjaId") Long franjaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FranjaHorariaSemanalResource deleteFranja: input: {0}", franjaId);
        if (franjaLogic.find(franjaId) == null) {
            throw new WebApplicationException("El recurso /franja/" + franjaId + " no existe.", 404);
        }
        franjaLogic.deleteFranja(franjaId);
        LOGGER.info("FranjaHorariaSemanalResource deleteFranja: output: void");
    }
}
