/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.AgendaDTO;
import co.edu.uniandes.csw.automotor.ejb.AgendaLogic;
import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
 * @author Pablo Garzon
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AgendaResource {
    
    @Inject
    private AgendaLogic agendaLogic;
    
    @POST
    public AgendaDTO createAgenda(AgendaDTO agenda)throws BusinessLogicException
    {
        AgendaDTO ag = new AgendaDTO(agendaLogic.CreateAgenda(agenda.toEntity()));
        return ag;
    }
    
    @GET
    public List<AgendaDTO> getAgendas(@PathParam("idConductor")Long id)
    {
        List<AgendaDTO> listaNueva = listEntities2DTO(agendaLogic.getDates(id));
        return listaNueva;
    }
    
    @GET
    @Path("{idAgenda: \\d+}")
    public AgendaDTO getAgenda(@PathParam("idAgenda")Long id)throws WebApplicationException
    {
        AgendaEntity ae = agendaLogic.getDate(id);
        if(ae == null)
        {
            throw new WebApplicationException();
        }
        return new AgendaDTO(ae);
    }
    
    @PUT
    @Path("{idAgenda: \\d+}")
    public AgendaDTO updateAgenda(@PathParam("idAgenda")Long id, AgendaDTO agenda)throws BusinessLogicException
    {
        AgendaEntity ae = agendaLogic.getDate(id);
        agenda.setId(id);
        if(ae == null)
        {
            throw new WebApplicationException();
        }
        AgendaDTO dto = new AgendaDTO(agendaLogic.updateDate(agenda.toEntity()));
        return dto;
    }
    @DELETE
    @Path("{idAgenda: \\d+}")
    public void deleteAgenda(@PathParam("idAgenda")Long id)throws BusinessLogicException
    {
        if(agendaLogic.getDate(id) == null)
        {
            throw new BusinessLogicException();
        }
        agendaLogic.deleteDate(id);
    }
    
    private List<AgendaDTO> listEntities2DTO(Collection<AgendaEntity> entityList)
    {
        List<AgendaDTO> lista = new ArrayList<AgendaDTO>();
        for(AgendaEntity ae : entityList)
        {
            lista.add(new AgendaDTO(ae));
        }
        return lista;
    }
}
