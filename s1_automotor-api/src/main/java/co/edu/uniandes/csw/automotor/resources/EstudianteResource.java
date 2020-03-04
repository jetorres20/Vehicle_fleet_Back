/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.EstudianteDTO;
import co.edu.uniandes.csw.automotor.dtos.EstudianteDetailDTO;
import co.edu.uniandes.csw.automotor.ejb.EstudianteLogic;
import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
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
 * @author Juan Esteban Torres
 */
@Path("estudiantes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EstudianteResource {

    private static final Logger LOGGER = Logger.getLogger(EstudianteResource.class.getName());
    
    @Inject
    private EstudianteLogic estudianteLogic;
    
      @POST
    public EstudianteDTO createEstudiante(EstudianteDTO estudiante) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EstudianteResource createEstudiante: input: {0}", estudiante);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EstudianteEntity estudianteEntity = estudiante.toEntity();
        // Invoca la lógica para crear la estudiante nueva
        EstudianteEntity nuevoEstudianteEntity = estudianteLogic.createEstudiante(estudianteEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        EstudianteDTO nuevoEstudianteDTO = new EstudianteDTO(nuevoEstudianteEntity);
        LOGGER.log(Level.INFO, "EstudianteResource createEstudiante: output: {0}", nuevoEstudianteDTO);
        return nuevoEstudianteDTO;
    }
    
    /**
     * Busca y devuelve todos los estudiantes que existen en la aplicacion.
     *
     * @return JSONArray {@link EditorialDetailDTO} - Las editoriales
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EstudianteDetailDTO> getEstudiantes() {
        LOGGER.info("EditorialResource getEstudiantes: input: void");
        List<EstudianteDetailDTO> listaEstudiantes = listEntity2DetailDTO(estudianteLogic.getEstudiantes());
        LOGGER.log(Level.INFO, "EstudianteResource getEstudiantes: output: {0}", listaEstudiantes);
        return listaEstudiantes;
    }
    
   
    
    @GET
    @Path("{estudiantesId: \\d+}")
    public EstudianteDetailDTO getEstudiante(@PathParam("estudiantesId") Long estudiantesId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "EstudianteResource getEstudiante: input: {0}", estudiantesId);
        EstudianteEntity entidad = estudianteLogic.getEstudiante(estudiantesId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /estudiantes/ " + estudiantesId + " no existe", 404);
        }

        EstudianteDetailDTO dto = new EstudianteDetailDTO(entidad);
        LOGGER.log(Level.INFO, "EstudianteResource getEstudiante: output: {0}", dto);

        return dto;
    }
    
    /**
     * Actualiza el estudiante con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param estudiantesId Identificador del estudiante que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param estudiante {@link EstudianteDetailDTO} El estudiante que se desea
     * guardar.
     * @return JSON {@link EstudianteDetailDTO} - El estudiante guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el estudiante a
     * actualizar.
     */
    @PUT
    @Path("{estudiantesId: \\d+}")
    public EstudianteDetailDTO updateEstudiante(@PathParam("estudiantesId") Long estudiantesId, EstudianteDetailDTO estudiante) throws WebApplicationException {
        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: input: id:{0} , editorial: {1}", new Object[]{estudiantesId, estudiante});
        estudiante.setId(estudiantesId);
        if (estudianteLogic.getEstudiante(estudiantesId) == null) {
            throw new WebApplicationException("El recurso /estudiantes/" + estudiantesId + " no existe.", 404);
        }
        EstudianteDetailDTO detailDTO = new EstudianteDetailDTO(estudianteLogic.updateEstudiante(estudiantesId, estudiante.toEntity()));
        LOGGER.log(Level.INFO, "EstudianteResource updateEstudiante: output: {0}", detailDTO);
        return detailDTO;
    }
    
      /**
     * Borra la editorial con el id asociado recibido en la URL.
     *
     * @param estudiantesId Identificador del estduiante que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el estudiante.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el estudiante.
     */
    @DELETE
    @Path("{estudiantesId: \\d+}")
    public void deleteEstudiante(@PathParam("estudiantesId") Long estudiantesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource deleteEditorial: input: {0}", estudiantesId);
        if (estudianteLogic.getEstudiante(estudiantesId) == null) {
            throw new WebApplicationException("El recurso /estudiante/" + estudiantesId + " no existe.", 404);
        }
        estudianteLogic.deleteEstudiante(estudiantesId);
        LOGGER.info("EstudianteResource deleteEstudiante: output: void");
    }
    
    
    
    
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EstudianteEntity a una lista de
     * objetos EditorialDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<EstudianteDetailDTO> listEntity2DetailDTO(List<EstudianteEntity> entityList) {
        List<EstudianteDetailDTO> list = new ArrayList<>();
        for (EstudianteEntity entity : entityList) {
            list.add(new EstudianteDetailDTO(entity));
        }
        return list;
    }
}
