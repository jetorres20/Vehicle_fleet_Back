/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.AgendaLogic;
import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.AgendaPersistence;
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
public class AgendaLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AgendaEntity.class.getPackage())
                .addPackage(AgendaLogic.class.getPackage())
                .addPackage(AgendaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    AgendaLogic agendaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
//    @Test
//    public void createAgenda()throws BusinessLogicException
//    {
//        AgendaEntity agenda1 = factory.manufacturePojo(AgendaEntity.class);
//        AgendaEntity result = agendaLogic.CreateAgenda(agenda1);
//        Assert.assertNotNull(result);
//        
//        AgendaEntity ent = em.find(AgendaEntity.class, result.getId());
//        Assert.assertEquals(ent.getFecha(), agenda1.getFecha());
//        em.remove(result.getId());
//    }
    @Test (expected = BusinessLogicException.class)
    public void createAgendaFechaNull()throws BusinessLogicException
    {
        AgendaEntity agenda = factory.manufacturePojo(AgendaEntity.class);
        agenda.setFecha(null);
        AgendaEntity result = agendaLogic.CreateAgenda(agenda);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createAgendaYcreateFechaCruzada()throws BusinessLogicException
    {
        AgendaEntity agenda = factory.manufacturePojo(AgendaEntity.class);
        agenda.setDuracionEnMin(60);
        AgendaEntity agenda1 = factory.manufacturePojo(AgendaEntity.class);
        agenda1.setFecha(DateUtils.addMinutes(agenda.getFecha(),20));
        agenda1.setDuracionEnMin(10);
        AgendaEntity result = agendaLogic.CreateAgenda(agenda);
        Assert.assertNotNull(result);
        AgendaEntity ent = em.find(AgendaEntity.class, result.getId());
        Assert.assertEquals(ent.getFecha(), agenda.getFecha());
        AgendaEntity result2 = agendaLogic.CreateAgenda(agenda1);
    }
}
