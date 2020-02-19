/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.entities.UniversidadEntity;
import co.edu.uniandes.csw.automotor.persistence.UniversidadPersistence;
import co.edu.uniandes.csw.automotor.ejb.UniversidadLogic;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Villamarin
 */
@RunWith(Arquillian.class)
public class UniversidadLogicTest {
    
    @Inject
    private UniversidadLogic ul;
    
    private final PodamFactory factory= new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UniversidadEntity.class.getPackage())
                .addPackage(UniversidadPersistence.class.getPackage())
                .addPackage(UniversidadLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createUniversity() throws BusinessLogicException{
        UniversidadEntity university=factory.manufacturePojo(UniversidadEntity.class);
        UniversidadEntity result=ul.createUniversidad(university);
        Assert.assertNotNull(result);
        
        UniversidadEntity entity=em.find(UniversidadEntity.class, result.getId());
        Assert.assertEquals(entity, result);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createUniversityNameNull() throws BusinessLogicException{
        UniversidadEntity university=factory.manufacturePojo(UniversidadEntity.class);
        university.setName(null);
        ul.createUniversidad(university);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createUniversityCityNull()throws BusinessLogicException{
        UniversidadEntity university=factory.manufacturePojo(UniversidadEntity.class);
        university.setCity(null);
        ul.createUniversidad(university);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createUniversityIsPrivateNull()throws BusinessLogicException{
        UniversidadEntity university=factory.manufacturePojo(UniversidadEntity.class);
        university.setCity(null);
        ul.createUniversidad(university);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createUniversityDuplicate()throws BusinessLogicException{
        UniversidadEntity university=factory.manufacturePojo(UniversidadEntity.class);
        UniversidadEntity copy=university;
        ul.createUniversidad(university);
        ul.createUniversidad(copy);
    }
    
}
