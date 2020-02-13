/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.persistence.ConductorPersistence;
import java.util.Collection;
import java.util.Iterator;
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
public class ConductorPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConductorEntity.class.getPackage())
                .addPackage(ConductorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    ConductorPersistence cp;
        
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ConductorEntity cond = factory.manufacturePojo(ConductorEntity.class);
        ConductorEntity result = cp.create(cond);
        Assert.assertNotNull(result);
        
        ConductorEntity entity = em.find(ConductorEntity.class, result.getId());
        
        Assert.assertEquals(cond.getName(), entity.getName());
        
    }
    
    @Test
    public void findTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ConductorEntity conductor = factory.manufacturePojo(ConductorEntity.class);
        ConductorEntity result = cp.create(conductor);
        long id = result.getId();
        Assert.assertNotNull(result);
        
        ConductorEntity entity = cp.find(id);
        
        Assert.assertEquals(entity.getName(), conductor.getName());
        Assert.assertEquals(entity.getAgenda(), conductor.getAgenda());
        Assert.assertEquals(entity.getFranjasHorariasSemanales(), conductor.getFranjasHorariasSemanales());
        Assert.assertEquals(entity.getReservas(), conductor.getReservas());
    }
    
    @Test
    public void updateTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ConductorEntity conductor = factory.manufacturePojo(ConductorEntity.class);
        ConductorEntity result = cp.create(conductor);
        Assert.assertNotNull(result);        
        
        ConductorEntity entity = result;
        long idR = entity.getId();
        entity.setName("nice");
        cp.update(entity);
        entity = cp.find(idR);
        Assert.assertEquals("nice", entity.getName());
        entity.setName("NICE");
        cp.update(entity);
        entity = em.find(ConductorEntity.class, result.getId());
        Assert.assertEquals("NICE", entity.getName());
        entity = cp.find(idR);
        Assert.assertNull(entity.getAgenda());
    }
    
    @Test
    public void deleteTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ConductorEntity agenda = factory.manufacturePojo(ConductorEntity.class);
        ConductorEntity result = cp.create(agenda);
        Assert.assertNotNull(result);        
        cp.delete(result.getId());
        
        ConductorEntity entity = em.find(ConductorEntity.class, result.getId());
        Assert.assertNull(entity);
    }
    
    @Test
    public void finAllTest()
    {
        Collection<ConductorEntity> col = cp.finAll();
        Iterator<ConductorEntity> it = col.iterator();
        while(it.hasNext())
        {
            Assert.assertEquals(ConductorEntity.class, it.next().getClass());
        }
    }
}
