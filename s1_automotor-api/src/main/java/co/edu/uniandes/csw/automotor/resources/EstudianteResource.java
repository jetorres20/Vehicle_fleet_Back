/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.EstudianteDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Esteban Torres
 */
@Path("estudiantes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EstudianteResource {

    private static final Logger LOG = Logger.getLogger(EstudianteResource.class.getName());
    
    @POST
    public EstudianteDTO createEstudiante(EstudianteDTO est){
        
        return est;
    }
    
    
}
