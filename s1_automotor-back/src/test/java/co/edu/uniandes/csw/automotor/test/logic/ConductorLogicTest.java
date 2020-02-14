/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.ConductorLogic;
import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.ConductorPersistence;
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
 * @author Pablo Garzon
 */
@RunWith(Arquillian.class)
public class ConductorLogicTest {
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConductorEntity.class.getPackage())
                .addPackage(ConductorLogic.class.getPackage())
                .addPackage(ConductorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    ConductorLogic cl;
    
    @PersistenceContext
    EntityManager em;
    
    @Test
    public void createConductor()throws BusinessLogicException
    {
        ConductorEntity conductor1 = factory.manufacturePojo(ConductorEntity.class);
        ConductorEntity result = cl.createConductor(conductor1);
        Assert.assertNotNull(result);
        
        ConductorEntity ent = em.find(ConductorEntity.class, result.getId());
        Assert.assertEquals(ent.getName(), conductor1.getName());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createConductorNameNull()throws BusinessLogicException
    {
        ConductorEntity conductor1 = factory.manufacturePojo(ConductorEntity.class);
        conductor1.setName(null);
        ConductorEntity result = cl.createConductor(conductor1);
    }
}
