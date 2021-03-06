/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.ejb;

import co.edu.uniandes.csw.automotor.entities.UniversidadEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.UniversidadPersistence;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Villamarin
 */
@Stateless
public class UniversidadLogic {
    
    @Inject
    private UniversidadPersistence persitence;
    
    public UniversidadEntity createUniversidad(UniversidadEntity universidad) throws BusinessLogicException{
        
        if(verifyRules(universidad)){
            universidad=persitence.create(universidad);
            return universidad;
        }
        return null;
    }
    
    public UniversidadEntity upDateUniversityName(UniversidadEntity universidad,String name) throws BusinessLogicException{
        universidad.setName(name);
        if(verifyRules(universidad)){
            universidad=persitence.update(universidad);
            return universidad;
        }
        return null;
    }
    
    public UniversidadEntity upDateUniversityCity(UniversidadEntity universidad,String city) throws BusinessLogicException{
        universidad.setCity(city);
        if(verifyRules(universidad)){
            universidad=persitence.update(universidad);
            return universidad;
        }
        return null;
    }
    
    public UniversidadEntity upDateUniversityPrivateStatus(UniversidadEntity universidad,Boolean isPrivate) throws BusinessLogicException{
        universidad.setIsPrivate(isPrivate);
        if(verifyRules(universidad)){
            universidad=persitence.update(universidad);
            return universidad;
        }
        return null;
    }
    
    public List<UniversidadEntity> getUniversities(){
        List<UniversidadEntity> res=(List<UniversidadEntity>)persitence.findAll();
        return res;
    }
    
    public UniversidadEntity find(Long id){
        UniversidadEntity res=persitence.find((long)id);
        return res;
    }
    
    public void deleteUniversity(Long id){
        persitence.delete(id);
    }
    
    public UniversidadEntity upDateUniversity(UniversidadEntity universidad) throws BusinessLogicException{
        
        if(verifyRules(universidad)){
            universidad=persitence.update(universidad);
            return universidad;
        }
        return null;
    }
    
    private boolean verifyRules(UniversidadEntity universidad)throws BusinessLogicException{
        if(universidad.getName()==null){
            throw new BusinessLogicException("El nombre de la universidad no puede ser null");
        }
        if(universidad.getCity()==null){
            throw new BusinessLogicException("La ciudad no puede ser null");
        }
        if(universidad.getIsPrivate()==null){
            throw new BusinessLogicException("Se debe tener informacion del tipo de universidad(privada o publica)");
        }
        
        Collection<UniversidadEntity> unis=persitence.findAll();
        Iterator<UniversidadEntity> it=unis.iterator();
        while(it.hasNext()){
            UniversidadEntity act=it.next();
            if(act.getName().equals(universidad.getName())&&act.getCity().equals(universidad.getCity())&&(act.getIsPrivate()== universidad.getIsPrivate())){
                throw new BusinessLogicException("No puede haber dos universidades iguales"); 
            }
        }
        
        return true;
    }
}
