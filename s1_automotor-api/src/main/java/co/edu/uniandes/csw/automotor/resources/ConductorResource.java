/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.resources;

//import co.edu.uniandes.csw.automotor.dtos.ConductorDTO;
//import co.edu.uniandes.csw.automotor.dtos.ConductorDetailDTO;
//import co.edu.uniandes.csw.automotor.ejb.ConductorLogic;
//import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
//import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;

/**
 *
 * @author Pablo Garzon
 */
//@Path("conductores")
//@Produces("application/json")
//@Consumes("application/json")
//@RequestScoped
public class ConductorResource {
    
//    @Inject
//    private ConductorLogic conductorLogic;
//    
//    @POST
//    public ConductorDTO createConductor(ConductorDTO conductor)throws BusinessLogicException
//    {
//        ConductorDTO createdConductorDTO = new ConductorDTO(conductorLogic.createConductor(conductor.toEntity()));
//        return createdConductorDTO;
//    }
//    
//    @GET
//    public List<ConductorDetailDTO> getConductores()
//    {
//        List<ConductorDetailDTO> lista = listEntity2DetailDTO(conductorLogic.getConductores());
//        return lista;
//    }
//    
//    @GET
//    public ConductorDetailDTO getConductor(long id)throws BusinessLogicException
//    {
//        ConductorEntity ce = conductorLogic.getConductor(id);
//        if(ce == null)
//        {
//            throw new BusinessLogicException();
//        }
//        ConductorDetailDTO dto = new ConductorDetailDTO(ce);
//        return dto;
//    }
//    
//    @PUT
//    public ConductorDetailDTO updateConductor(long id, ConductorDetailDTO cdto) throws BusinessLogicException
//    {
//        ConductorEntity ce = conductorLogic.getConductor(id);
//        cdto.setIdConductor(id);
//        if(ce == null)
//        {
//            throw new BusinessLogicException();
//        }
//        ConductorDetailDTO conductorDet = new ConductorDetailDTO(conductorLogic.updateConductor(cdto.toEntity()));
//        return conductorDet;
//    }
//    
//    public void deleteConductor(long id)throws BusinessLogicException
//    {
//        ConductorEntity ce = conductorLogic.getConductor(id);
//        if(ce == null)
//        {
//            throw new BusinessLogicException();
//        }
//        conductorLogic.deleteConductor(id);
//    }
//    
//    
//    
//    private List<ConductorDetailDTO> listEntity2DetailDTO(List<ConductorEntity> entityList) {
//        List<ConductorDetailDTO> list = new ArrayList<>();
//        for (ConductorEntity entity : entityList) {
//            list.add(new ConductorDetailDTO(entity));
//        }
//        return list;
//    }
    
}
