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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
        // Invoca la lógica para crear la editorial nueva
        TipoVehiculoEntity nuevoTipoVehiculoEntity = tipoVehiculoLogic.createTipoVehiculo(tipoVehiculoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        TipoVehiculoDTO nuevoTipoVehiculoDTO = new TipoVehiculoDTO(nuevoTipoVehiculoEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoTipoVehiculoDTO);
        return nuevoTipoVehiculoDTO;
    }
    
    /**
     * Busca y devuelve todas los TipoVehiculo que existen en la aplicacion.
     *
     * @return JSONArray {@link EditorialDetailDTO} - Las editoriales
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EditorialDetailDTO> getEditorials() {
        LOGGER.info("EditorialResource getEditorials: input: void");
        List<EditorialDetailDTO> listaEditoriales = listEntity2DetailDTO(editorialLogic.getEditorials());
        LOGGER.log(Level.INFO, "EditorialResource getEditorials: output: {0}", listaEditoriales);
        return listaEditoriales;
    }
}
