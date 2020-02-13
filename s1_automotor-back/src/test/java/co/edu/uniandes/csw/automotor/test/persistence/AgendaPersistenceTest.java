/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import co.edu.uniandes.csw.automotor.persistence.AgendaPersistence;
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
public class AgendaPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AgendaEntity.class.getPackage())
                .addPackage(AgendaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    AgendaPersistence ap;
        
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        AgendaEntity agenda = factory.manufacturePojo(AgendaEntity.class);
        AgendaEntity result = ap.create(agenda);
        Assert.assertNotNull(result);
        
        AgendaEntity entity = em.find(AgendaEntity.class, result.getId());
        
        Assert.assertEquals(agenda.getFecha(), entity.getFecha());
        Assert.assertEquals(agenda.getDuracionEnMin(), entity.getDuracionEnMin());
        Assert.assertNull(entity.getConductor());
    }
    
    @Test
    public void findTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        AgendaEntity agenda = factory.manufacturePojo(AgendaEntity.class);
        AgendaEntity result = ap.create(agenda);
        long id = result.getId();
        Assert.assertNotNull(result);
        
        AgendaEntity entity = ap.find(id);
        
        Assert.assertEquals(entity.getFecha(), agenda.getFecha());
        Assert.assertEquals(entity.getDuracionEnMin(), agenda.getDuracionEnMin());
        Assert.assertEquals(entity.getConductor(), agenda.getConductor());
        Assert.assertEquals(entity.getReservas(), agenda.getReservas());
    }
    
    @Test
    public void deleteTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        AgendaEntity agenda = factory.manufacturePojo(AgendaEntity.class);
        AgendaEntity result = ap.create(agenda);
        Assert.assertNotNull(result);        
        ap.delete(result.getId());
        
        AgendaEntity entity = em.find(AgendaEntity.class, result.getId());
        Assert.assertNull(entity);
    }
    @Test
    public void updateTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        AgendaEntity agenda = factory.manufacturePojo(AgendaEntity.class);
        AgendaEntity result = ap.create(agenda);
        Assert.assertNotNull(result);        
        
        AgendaEntity entity = em.find(AgendaEntity.class, result.getId());
        entity.setReservada(true);
        ap.update(entity);
        entity = em.find(AgendaEntity.class, result.getId());
        Assert.assertTrue(entity.isReservada());
        entity.setReservada(false);
        ap.update(entity);
        entity = em.find(AgendaEntity.class, result.getId());
        Assert.assertFalse(entity.isReservada());
        entity = em.find(AgendaEntity.class, result.getId());
        Assert.assertNull(entity.getConductor());
    }
    @Test
    public void finAllTest()
    {
        Collection<AgendaEntity> col = ap.finAll();
        Iterator<AgendaEntity> it = col.iterator();
        while(it.hasNext())
        {
            Assert.assertEquals(AgendaEntity.class, it.next().getClass());
        }
    }
}
