/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.ConductorLogic;
import co.edu.uniandes.csw.automotor.ejb.ReservaLogic;
import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.ConductorPersistence;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.time.DateUtils;
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
    
    @Inject
    ReservaLogic reservaLogic;
    
    @PersistenceContext
    EntityManager em;
    
    @Test
    public void createConductorTest()throws BusinessLogicException
    {
        ConductorEntity conductor1 = factory.manufacturePojo(ConductorEntity.class);
        ConductorEntity result = cl.createConductor(conductor1);
        Assert.assertNotNull(result);
        
        ConductorEntity ent = em.find(ConductorEntity.class, result.getId());
        Assert.assertEquals(ent.getName(), conductor1.getName());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createConductorNameNullTest()throws BusinessLogicException
    {
        ConductorEntity conductor1 = factory.manufacturePojo(ConductorEntity.class);
        conductor1.setName(null);
        ConductorEntity result = cl.createConductor(conductor1);
    }
    
    @Test
    public void updateConductorTest()throws BusinessLogicException
    {
        ConductorEntity conductor1 = factory.manufacturePojo(ConductorEntity.class);
        ConductorEntity result = cl.createConductor(conductor1);
        Assert.assertNotNull(result);
        ConductorEntity ent = em.find(ConductorEntity.class, result.getId());
        Assert.assertEquals(ent.getName(), conductor1.getName());
        
        ConductorEntity cond = factory.manufacturePojo(ConductorEntity.class);
        cond.setId(ent.getId());
        cl.updateConductor(cond);
        
        conductor1 = em.find(ConductorEntity.class,ent.getId());
        Assert.assertEquals(conductor1.getName(), cond.getName());
        Assert.assertEquals(conductor1.getAgendas(), cond.getAgendas());
        Assert.assertEquals(conductor1.getFranjasHorariasSemanales(), cond.getFranjasHorariasSemanales());
        Assert.assertEquals(conductor1.getReservas(), cond.getReservas());
    }
    
    @Test
    public void deleteConductorTest() throws BusinessLogicException
    {
        ConductorEntity ce = factory.manufacturePojo(ConductorEntity.class);
        ce.setAgendas(null);
        ce.setFranjasHorariasSemanales(null);
        ce.setReservas(null);
        ConductorEntity result = cl.createConductor(ce);
        Assert.assertNotNull(result);
        cl.deleteConductor(result.getId());
        Assert.assertNull(em.find(ConductorEntity.class, result.getId()));
    }
    
    @Test (expected = BusinessLogicException.class)
    public void deleteConductorTestNotNull() throws BusinessLogicException
    {
        ConductorEntity ce = factory.manufacturePojo(ConductorEntity.class);
        ReservaEntity re = factory.manufacturePojo(ReservaEntity.class);
        re.setFechaReserva(DateUtils.addMinutes(re.getFechaServicio(), -30));
        reservaLogic.createReserva(re);
        
        ce.setAgendas(null);
        ce.setFranjasHorariasSemanales(null);
        Collection<ReservaEntity> reservas = new ArrayList<ReservaEntity>();
        reservas.add(re);
        ce.setReservas(reservas);
        ConductorEntity result = cl.createConductor(ce);
        ReservaEntity reserv = em.find(ReservaEntity.class, re.getId());
        reserv.setConductor(result);
        reservaLogic.getRservaPersitence().update(reserv);
        //cl.updateConductor(result);
        Assert.assertNotNull(result);
        cl.deleteConductor(result.getId());
        //Assert.assertNull(em.find(ConductorEntity.class, result.getId()));
    }
}
