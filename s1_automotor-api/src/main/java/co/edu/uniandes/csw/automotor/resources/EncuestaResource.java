/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.EncuestaDTO;
import co.edu.uniandes.csw.automotor.ejb.EncuestaLogic;
import co.edu.uniandes.csw.automotor.entities.EncuestaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan Esteban Torres
 */
@Path("encuestas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EncuestaResource {
    private static final Logger LOGGER = Logger.getLogger(EncuestaResource.class.getName());
    
    @Inject
    private EncuestaLogic encuestaLogic;
    
      @POST
    public EncuestaDTO createEncuesta(EncuestaDTO encuesta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EncuestaResource createEncuesta: input: {0}", encuesta);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EncuestaEntity encuestaEntity = encuesta.toEntity();
        // Invoca la lógica para crear la encuesta nueva
        EncuestaEntity nuevoEncuestaEntity = encuestaLogic.createEncuesta(encuestaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        EncuestaDTO nuevoEncuestaDTO = new EncuestaDTO(nuevoEncuestaEntity);
        LOGGER.log(Level.INFO, "EncuestaResource createEncuesta: output: {0}", nuevoEncuestaDTO);
        return nuevoEncuestaDTO;
    }
    
    /**
     * Busca y devuelve todos los encuestas que existen en la aplicacion.
     *
     * @return JSONArray {@link EncuestaDTO} - Las encuestas
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EncuestaDTO> getEncuestas() {
        LOGGER.info("EditorialResource getEncuestas: input: void");
        List<EncuestaDTO> listaEncuestas = listEntity2DetailDTO(encuestaLogic.getEncuestas());
        LOGGER.log(Level.INFO, "EncuestaResource getEncuestas: output: {0}", listaEncuestas);
        return listaEncuestas;
    }
    
   
    
    @GET
    @Path("{encuestasId: \\d+}")
    public EncuestaDTO getEncuesta(@PathParam("encuestasId") Long encuestasId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "EncuestaResource getEncuesta: input: {0}", encuestasId);
        EncuestaEntity entidad = encuestaLogic.getEncuesta(encuestasId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /encuestas/ " + encuestasId + " no existe", 404);
        }

        EncuestaDTO dto = new EncuestaDTO(entidad);
        LOGGER.log(Level.INFO, "EncuestaResource getEncuesta: output: {0}", dto);

        return dto;
    }
    
    /**
     * Actualiza el encuesta con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param encuestasId Identificador del encuesta que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param encuesta {@link EncuestaDTO} El encuesta que se desea
     * guardar.
     * @return JSON {@link EncuestaDTO} - El encuesta guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el encuesta a
     * actualizar.
     */
    @PUT
    @Path("{encuestasId: \\d+}")
    public EncuestaDTO updateEncuesta(@PathParam("encuestasId") Long encuestasId, EncuestaDTO encuesta) throws WebApplicationException {
        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: input: id:{0} , editorial: {1}", new Object[]{encuestasId, encuesta});
        encuesta.setId(encuestasId);
        if (encuestaLogic.getEncuesta(encuestasId) == null) {
            throw new WebApplicationException("El recurso /encuestas/" + encuestasId + " no existe.", 404);
        }
        EncuestaDTO detailDTO = new EncuestaDTO(encuestaLogic.updateEncuesta(encuestasId, encuesta.toEntity()));
        LOGGER.log(Level.INFO, "EncuestaResource updateEncuesta: output: {0}", detailDTO);
        return detailDTO;
    }
    
      /**
     * Borra la editorial con el id asociado recibido en la URL.
     *
     * @param encuestasId Identificador del estduiante que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el encuesta.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el encuesta.
     */
    @DELETE
    @Path("{encuestasId: \\d+}")
    public void deleteEncuesta(@PathParam("encuestasId") Long encuestasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource deleteEditorial: input: {0}", encuestasId);
        if (encuestaLogic.getEncuesta(encuestasId) == null) {
            throw new WebApplicationException("El recurso /encuesta/" + encuestasId + " no existe.", 404);
        }
        encuestaLogic.deleteEncuesta(encuestasId);
        LOGGER.info("EncuestaResource deleteEncuesta: output: void");
    }
    
    
    
    
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EncuestaEntity a una lista de
     * objetos EditorialDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<EncuestaDTO> listEntity2DetailDTO(List<EncuestaEntity> entityList) {
        List<EncuestaDTO> list = new ArrayList<>();
        for (EncuestaEntity entity : entityList) {
            list.add(new EncuestaDTO(entity));
        }
        return list;
    }
    
}
