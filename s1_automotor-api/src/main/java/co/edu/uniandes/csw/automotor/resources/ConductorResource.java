/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

import co.edu.uniandes.csw.automotor.dtos.ConductorDTO;
import co.edu.uniandes.csw.automotor.dtos.ConductorDetailDTO;
import co.edu.uniandes.csw.automotor.ejb.ConductorLogic;
import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
import co.edu.uniandes.csw.automotor.resources.AgendaResource;

/**
 *
 * @author Pablo Garzon
 */
@Path("conductores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ConductorResource {
    
    @Inject
    private ConductorLogic conductorLogic;
    
    @POST
    public ConductorDTO createConductor(ConductorDTO conductor)throws BusinessLogicException
    {
        ConductorDTO createdConductorDTO = new ConductorDTO(conductorLogic.createConductor(conductor.toEntity()));
        return createdConductorDTO;
    }
    
    @GET
    public List<ConductorDetailDTO> getConductores()
    {
        List<ConductorDetailDTO> lista = listEntity2DetailDTO(conductorLogic.getConductores());
        return lista;
    }
    
    @GET
    @Path("{idConductor: \\d+}")
    public ConductorDetailDTO getConductor(@PathParam("idConductor")Long id)throws BusinessLogicException
    {
        ConductorEntity ce = conductorLogic.getConductor(id);
        if(ce==null)
        {
            throw new WebApplicationException();
        }
        //ConductorDetailDTO dto = new ConductorDetailDTO(ce.get(0));
        ConductorDetailDTO dto = new ConductorDetailDTO(ce);
        return dto;
    }
    
    @PUT
    @Path("{idConductor: \\d+}")
    public ConductorDetailDTO updateConductor(@PathParam("idConductor")Long id, ConductorDetailDTO cdto) throws WebApplicationException
    {
        ConductorEntity ce = conductorLogic.getConductor(id);   
        cdto.setIdConductor(id);
        if(ce == null)
        {
            throw new WebApplicationException();
        }
        ConductorDetailDTO conductorDet = new ConductorDetailDTO(conductorLogic.updateConductor(cdto.toEntity()));
        return conductorDet;
    }
    
    @DELETE
    @Path("{idConductor: \\d+}")
    public void deleteConductor(@PathParam("idConductor")Long id)throws BusinessLogicException
    {
        //ConductorEntity ce = conductorLogic.getConductorFromId(id).get(0);
        ConductorEntity ce = conductorLogic.getConductor(id);
        if(ce == null)
        {
            throw new WebApplicationException();
        }
        conductorLogic.deleteConductor(ce.getId());
    }
    
    
    
    private List<ConductorDetailDTO> listEntity2DetailDTO(List<ConductorEntity> entityList) {
        List<ConductorDetailDTO> list = new ArrayList<>();
        for (ConductorEntity entity : entityList) {
            list.add(new ConductorDetailDTO(entity));
        }
        return list;
    }
    
    @Path("{idConductor: \\d+}/agendas")
    public Class<AgendaResource> getAgendaResource(@PathParam("idConductor") Long idCond) 
    {
        if(conductorLogic.getConductor(idCond) == null)
        {
            throw new WebApplicationException("el recurso de /conductor/"+idCond+"/agendas no existe, error 404");
        }
        return AgendaResource.class;
    }
    
    @Path("{idConductor: \\d+}/franja")
    public Class<FranjaHorariaSemanalResource> getFranjaResource(@PathParam("idConductor") Long idCond)
    {
        if(conductorLogic.getConductor(idCond) == null)
        {
            throw new WebApplicationException("el recurso de /conductor/"+idCond+"/franjas no existe, error 404");
        }
        return FranjaHorariaSemanalResource.class;
    }
}
