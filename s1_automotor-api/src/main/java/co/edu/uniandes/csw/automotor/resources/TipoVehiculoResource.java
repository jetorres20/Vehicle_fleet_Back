/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.TipoVehiculoDTO;
import co.edu.uniandes.csw.automotor.ejb.TipoVehiculoLogic;
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
@Path("tipoVehiculos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TipoVehiculoResource {
     
    private static final Logger LOGGER = Logger.getLogger(TipoVehiculoResource.class.getName());
    
    @Inject
    TipoVehiculoLogic tipoVehiculoLogic;
    
    /**
     * Crea un nuevo TipoVehiculo con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param tipoVehiculo {@link TipoVehiculoDTO} - El TipoVehiculo que se desea
     * guardar.
     * @return JSON {@link TipoVehiculoDTO} - El TipoVehiculo guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el TipoVehiculo.
     */
    @POST
    public TipoVehiculoDTO createTipoVehiculo(TipoVehiculoDTO tipoVehiculo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TipoVehiculoResource createTipoVehiculo: input: {0}", tipoVehiculo);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        TipoVehiculoEntity tipoVehiculoEntity = tipoVehiculo.toEntity();
        // Invoca la lógica para crear el tipoVehiculo nuevo
        TipoVehiculoEntity nuevoTipoVehiculoEntity = tipoVehiculoLogic.createTipoVehiculo(tipoVehiculoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        TipoVehiculoDTO nuevoTipoVehiculoDTO = new TipoVehiculoDTO(nuevoTipoVehiculoEntity);
        LOGGER.log(Level.INFO, "TipoVehiculoResource createTipoVehiculo: output: {0}", nuevoTipoVehiculoDTO);
        return nuevoTipoVehiculoDTO;
    }
    
    /**
     * Busca y devuelve todas los TipoVehiculo que existen en la aplicacion.
     *
     * @return JSONArray {@link TipoVehiculoDTO} - Los TipoVehiculo
     * encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<TipoVehiculoDTO> getTipoVehiculos() {
        LOGGER.info("TipoVehiculoResource getTipoVehiculos: input: void");
        List<TipoVehiculoDTO> listaTipoVehiculos = listEntity2DTO(tipoVehiculoLogic.getTipoVehiculos());
        LOGGER.log(Level.INFO, "TipoVehiculoResource getTipoVehiculos: output: {0}", listaTipoVehiculos);
        return listaTipoVehiculos;
    }
    
     /**
     * Busca el tipoVehiculo con el Id asociado recibido en la URL y la devuelve.
     *
     * @param tipoVehiculoId Identificador del tipoVehiculo que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link TipoVehiculoDTO} - El TipoVehiculo buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tipoVehiculo.
     */
    @GET
    @Path("{tipoVehiculoId: \\d+}")
    public TipoVehiculoDTO getTipoVehiculo(@PathParam("tipoVehiculoId") Long tipoVehiculoId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "TipoVehiculoResource getTipoVehiculo: input: {0}", tipoVehiculoId);
        TipoVehiculoEntity tipoVehiculoEntity = tipoVehiculoLogic.getTipoVehiculo(tipoVehiculoId);
        if (tipoVehiculoEntity == null) {
            throw new WebApplicationException("El recurso /tipoVehiculos/" + tipoVehiculoId + " no existe.", 404);
        }
        TipoVehiculoDTO detailDTO = new TipoVehiculoDTO(tipoVehiculoEntity);
        LOGGER.log(Level.INFO, "TipoVehiculoResource getTipoVehiculo: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Actualiza el TipoVehiculo con el id recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     *
     * @param tipoVehiculoId Identificador del tipo Vehiculo que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param tipoVehiculo {@link TipoVehiculoDTO} El Tipo Vehiculo que se desea guardar.
     * @return JSON {@link TipoVehiculoDTO} - El tipoVehiculo guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tipoVehiculo a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el tipoVehiculo.
     */
    @PUT
    @Path("{tipoVehiculoId: \\d+}")
    public TipoVehiculoDTO updateTipoVehiculo(@PathParam("tipoVehiculoId") Long tipoVehiculoId, TipoVehiculoDTO tipoVehiculo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TipoVehiculoResource updateTipoVehiculo: input: tipoVehiculoId: {0} , tipoVehiculo: {1}", new Object[]{tipoVehiculoId, tipoVehiculo});
        tipoVehiculo.setId(tipoVehiculoId);
        if (tipoVehiculoLogic.getTipoVehiculo(tipoVehiculoId) == null) {
            throw new WebApplicationException("El recurso /tipoVehiculos/" + tipoVehiculoId + " no existe.", 404);
        }
        TipoVehiculoDTO detailDTO = new TipoVehiculoDTO(tipoVehiculoLogic.updateTipoVehiculo(tipoVehiculoId, tipoVehiculo.toEntity()));
        LOGGER.log(Level.INFO, "TipoVehiculoResource updateTipoVehiculo: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @DELETE
    @Path("{tipoVehiculoId: \\d+}")
    public void deleteTipoVehiculo(@PathParam("tipoVehiculoId") Long tipoVehiculoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TipoVehiculoResource deleteTipoVehiculo: input: {0}", tipoVehiculoId);
        TipoVehiculoEntity entity = tipoVehiculoLogic.getTipoVehiculo(tipoVehiculoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tipoVehiculos/" + tipoVehiculoId + " no existe.", 404);
        }
        tipoVehiculoLogic.removeTipoVehiculo(tipoVehiculoId);
        LOGGER.info("TipoVehiculoResource deleteTipoVehiculo: output: void");
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TipoVehiculoEntity a una lista de
     * objetos TipoVehiculoDTO (json)
     * @param entityList corresponde a la lista de tipoVehiculos de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de tipoVehiculo en forma DTO (json)
     */
    private List<TipoVehiculoDTO> listEntity2DTO(List<TipoVehiculoEntity> entityList) {
        List<TipoVehiculoDTO> list = new ArrayList<>();
        for (TipoVehiculoEntity entity : entityList) {
            list.add(new TipoVehiculoDTO(entity));
        }
        return list;
    }
}
