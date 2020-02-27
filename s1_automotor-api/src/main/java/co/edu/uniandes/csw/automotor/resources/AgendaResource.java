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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Pablo Garzon
 */
@Path("fechasAg")
@Produces("application/json")
@Consumes("application/json")
//@RequestScoped
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
    public List<AgendaDTO> getAgendas()
    {
        List<AgendaDTO> listaNueva = listEntities2DTO(agendaLogic.getDates());
        return listaNueva;
    }
    
    @GET
    public AgendaDTO getAgenda(long id)throws BusinessLogicException
    {
        AgendaEntity ae = agendaLogic.getDate(id);
        if(ae == null)
        {
            throw new BusinessLogicException();
        }
        return new AgendaDTO(ae);
    }
    
    @PUT
    public AgendaDTO updateAgenda(long id, AgendaDTO agenda)throws BusinessLogicException
    {
        AgendaEntity ae = agendaLogic.getDate(id);
        agenda.setId(id);
        if(ae == null)
        {
            throw new BusinessLogicException();
        }
        AgendaDTO dto = new AgendaDTO(agendaLogic.updateDate(agenda.toEntity()));
        return dto;
    }
    
    public void deleteAgenda(long id)throws BusinessLogicException
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
