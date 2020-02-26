/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.RegistroDTO;
import co.edu.uniandes.csw.automotor.ejb.RegistroLogic;
import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
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
 * @author Juan Andrés Caycedo S
 */
@Path("registros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RegistroResource {

    private static final Logger LOGGER = Logger.getLogger(RegistroResource.class.getName());

    @Inject
    private RegistroLogic registroLogic;

    @POST
    public RegistroDTO crearRegistro(RegistroDTO registro) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VehiculoEditorial createVehiculo: input: {0}", registro);
        RegistroEntity registroEntity = registro.toEntity();

        RegistroEntity nuevaEntidad = registroLogic.createRegistro(registroEntity);

        RegistroDTO dto = new RegistroDTO(nuevaEntidad);
        LOGGER.log(Level.INFO, "VehiculoResource createVehiculo: output: {0}", dto);

        return dto;
    }

    @GET
    public List<RegistroDTO> getRegistros() {
        LOGGER.info("RegistroResource getRegistros: input: void");
        List<RegistroDTO> lista = listEntity2DetailDTO(registroLogic.getRegistros());
        LOGGER.log(Level.INFO, "RegistroResource getRegistros: output: {0}", lista);
        return lista;
    }

    @GET
    @Path("{registrosId: \\d+}")
    public RegistroDTO getRegistro(@PathParam("registrosId") Long registrosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RegistroResource getRegistro: input: {0}", registrosId);
        RegistroEntity entidad = registroLogic.getRegistro(registrosId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /registros/ " + registrosId + " no existe", 404);
        }
        RegistroDTO dto = new RegistroDTO(entidad);
        LOGGER.log(Level.INFO, "VehiculoResource getVehiculo: output: {0}", dto);

        return dto;
    }

    @DELETE
    @Path("{registrosId: \\d+}")
    public void deleteEditorial(@PathParam("registrosId") Long registrosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VehiculoResource deteleVehiculo : input {0}", registrosId);
        if (registroLogic.getRegistro(registrosId) == null) {
            throw new WebApplicationException("El recurso /registros/" + registrosId + " no existe.", 404);
        }
        registroLogic.deleteRegistro(registrosId);
        LOGGER.log(Level.INFO, "VehiculoResource deleteVehiculo: output :void");

    }

    private List<RegistroDTO> listEntity2DetailDTO(List<RegistroEntity> entityList) {
        List<RegistroDTO> list = new ArrayList<>();
        for (RegistroEntity entity : entityList) {
            list.add(new RegistroDTO(entity));
        }
        return list;
    }

    @PUT
    @Path("{registrosId: \\d+}")
    public RegistroDTO updateEditorial(@PathParam("registrosId") Long registrosId, RegistroDTO registro) throws WebApplicationException {
        LOGGER.log(Level.INFO, "RegistroResource updateRegistro: input: id:{0} , registro: {1}", new Object[]{registrosId, registro});
        registro.setId(registrosId);
        if (registroLogic.getRegistro(registrosId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + registrosId + " no existe.", 404);
        }
        RegistroDTO detailDTO = new RegistroDTO(registroLogic.updateRegistro(registrosId, registro.toEntity()));
        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: output: {0}", detailDTO);
        return detailDTO;

    }

}
