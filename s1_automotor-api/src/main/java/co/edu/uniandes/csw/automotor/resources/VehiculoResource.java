/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.VehiculoDTO;
import co.edu.uniandes.csw.automotor.ejb.VehiculoLogic;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import java.io.Serializable;
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
 * @author Juan Andrés Caycedo S.
 */
@Path("vehiculos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class VehiculoResource {

    private static final Logger LOGGER = Logger.getLogger(VehiculoResource.class.getName());

    @Inject
    private VehiculoLogic vehiculoLogic;

    @POST
    public VehiculoDTO crearVehiculo(VehiculoDTO vehiculo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VehiculoEditorial createVehiculo: input: {0}", vehiculo);
        VehiculoEntity vehiculoEntity = vehiculo.toEntity();

        VehiculoEntity nuevaEntidad = vehiculoLogic.createVehiculo(vehiculoEntity);

        VehiculoDTO dto = new VehiculoDTO(nuevaEntidad);
        LOGGER.log(Level.INFO, "VehiculoResource createVehiculo: output: {0}", dto);

        return dto;
    }

    @GET
    public List<VehiculoDTO> getVehiculos() {
        LOGGER.info("VehiculoResource getVehiculos: input: void");
        List<VehiculoDTO> lista = listEntity2DetailDTO(vehiculoLogic.getVehiculos());
        LOGGER.log(Level.INFO, "EditorialResource getEditorials: output: {0}", lista);
        return lista;
    }

    @GET
    @Path("{vehiculosId: \\d+}")
    public VehiculoDTO getVehiculo(@PathParam("vehiculosId") Long vehiculosId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: input: {0}", vehiculosId);
        VehiculoEntity entidad = vehiculoLogic.getVehiculo(vehiculosId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /vehiculos/ " + vehiculosId + " no existe", 404);
        }

        VehiculoDTO dto = new VehiculoDTO(entidad);
        LOGGER.log(Level.INFO, "VehiculoResource getVehiculo: output: {0}", dto);

        return dto;
    }

    @PUT
    @Path("{vehiculosId: \\d+}")
    public VehiculoDTO updateVehiculo(@PathParam("vehiculosId") Long vehiculosId, VehiculoDTO vehiculo) {
        LOGGER.log(Level.INFO, "VehiculoResource updateVehiculo: input: id:{0} , vehiculo: {1}", new Object[]{vehiculosId, vehiculo});
        vehiculo.setId(vehiculosId);
        if (vehiculoLogic.getVehiculo(vehiculosId) == null) {
            throw new WebApplicationException("El recurso /vehiculos/" + vehiculosId + " no existe.", 404);
        }
        VehiculoDTO dto = new VehiculoDTO(vehiculoLogic.updateVehiculo(vehiculosId, vehiculo.toEntity()));
        LOGGER.log(Level.INFO, "VehiculoResource updateVehiculo: output: {0}", dto);

        return dto;
    }

    
    @DELETE
    @Path("{vehiculosId: \\d+}")
    public void deleteEditorial (@PathParam("vehiculosId") Long vehiculosId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO,"VehiculoResource deteleVehiculo : input {0}", vehiculosId);
        if (vehiculoLogic.getVehiculo(vehiculosId) == null) {
            throw new WebApplicationException("El recurso /vehiculos/" + vehiculosId + " no existe.", 404);
        }
        vehiculoLogic.deleteVehiculo(vehiculosId);
        LOGGER.log(Level.INFO, "VehiculoResource deleteVehiculo: output :void");
        
    }
    
    
    private List<VehiculoDTO> listEntity2DetailDTO(List<VehiculoEntity> entityList) {
        List<VehiculoDTO> list = new ArrayList<>();
        for (VehiculoEntity entity : entityList) {
            list.add(new VehiculoDTO(entity));
        }
        return list;
    }

}
