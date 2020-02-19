/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.AgendaLogic;
import co.edu.uniandes.csw.automotor.ejb.ConductorLogic;
import co.edu.uniandes.csw.automotor.ejb.PracticaLogic;
import co.edu.uniandes.csw.automotor.ejb.ReservaLogic;
import co.edu.uniandes.csw.automotor.entities.AgendaEntity;
import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.entities.PracticaEntity;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.AgendaPersistence;
import java.util.Collection;
import java.util.Iterator;
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
    
    @Inject
    ConductorLogic conductorLogic;
    
    @Inject
    PracticaLogic practicaLogic;
    
    @Inject
    ReservaLogic reservaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createAgenda()throws BusinessLogicException
    {
        Collection<AgendaEntity> col = agendaLogic.getDates();
        Iterator<AgendaEntity> it = col.iterator();
        while(it.hasNext())
        {
            AgendaEntity ag = it.next();
            agendaLogic.removeConductor(ag);
            agendaLogic.deleteDate(ag.getId());
        }
        
        ConductorEntity ce = factory.manufacturePojo(ConductorEntity.class);
        conductorLogic.createConductor(ce);
        AgendaEntity agenda1 = factory.manufacturePojo(AgendaEntity.class);
        agenda1.setConductor(ce);
        ReservaEntity re = factory.manufacturePojo(ReservaEntity.class);
        re.setFechaReserva(DateUtils.addMinutes(re.getFechaServicio(), -30));
        reservaLogic.createReserva(re);
        agenda1.setReserva(re);
        AgendaEntity result = agendaLogic.CreateAgenda(agenda1);
        Assert.assertNotNull(result);
        
        AgendaEntity ent = em.find(AgendaEntity.class, result.getId());
        Assert.assertEquals(ent.getFecha(), agenda1.getFecha());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createAgendaDuracionNeg()throws BusinessLogicException
    {
        AgendaEntity agenda1 = factory.manufacturePojo(AgendaEntity.class);
        ReservaEntity re = factory.manufacturePojo(ReservaEntity.class);
        PracticaEntity pc = factory.manufacturePojo(PracticaEntity.class);
        reservaLogic.createReserva(re);
        pc.setDuracion(-20.0);
        practicaLogic.createPractica(pc);
        agenda1.setReserva(re);
        agendaLogic.CreateAgenda(agenda1);
    }
    
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
        ReservaEntity re = factory.manufacturePojo(ReservaEntity.class);
        PracticaEntity pc = factory.manufacturePojo(PracticaEntity.class);
        pc.setDuracion(60.0);
        practicaLogic.createPractica(pc);
        re.setPractica(pc);
        reservaLogic.createReserva(re);
        agenda.setReserva(re);
        
        AgendaEntity agenda1 = factory.manufacturePojo(AgendaEntity.class);
        ReservaEntity re1 = factory.manufacturePojo(ReservaEntity.class);
        PracticaEntity pc1 = factory.manufacturePojo(PracticaEntity.class);
        pc1.setDuracion(10.0);
        practicaLogic.createPractica(pc1);
        re1.setPractica(pc1);
        reservaLogic.createReserva(re1);
        agenda1.setReserva(re1);
        
        agenda1.setFecha(DateUtils.addMinutes(agenda.getFecha(),20));
        AgendaEntity result = agendaLogic.CreateAgenda(agenda);
        Assert.assertNotNull(result);
        AgendaEntity ent = em.find(AgendaEntity.class, result.getId());
        Assert.assertEquals(ent.getFecha(), agenda.getFecha());
        AgendaEntity result2 = agendaLogic.CreateAgenda(agenda1);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void setConductorTestNotNull()throws BusinessLogicException
    {
        ConductorEntity ce = factory.manufacturePojo(ConductorEntity.class);
        conductorLogic.createConductor(ce);
        
        ConductorEntity ce1 = factory.manufacturePojo(ConductorEntity.class);
        conductorLogic.createConductor(ce1);
        
        AgendaEntity agenda = factory.manufacturePojo(AgendaEntity.class);
        
        ReservaEntity re = factory.manufacturePojo(ReservaEntity.class);
        
        reservaLogic.createReserva(re);
        agenda.setReserva(re);
        
        agenda.setConductor(ce);
        
        AgendaEntity ag= agendaLogic.CreateAgenda(agenda);
        
        agendaLogic.setConductor(ag, ce1);
    }
    
    @Test
    public void setConductorTestNull()throws BusinessLogicException
    {
        Collection<AgendaEntity> col = agendaLogic.getDates();
        Iterator<AgendaEntity> it = col.iterator();
        while(it.hasNext())
        {
            AgendaEntity ag = it.next();
            agendaLogic.removeConductor(ag);
            agendaLogic.deleteDate(ag.getId());
        }
        
        ConductorEntity ce = factory.manufacturePojo(ConductorEntity.class);
        conductorLogic.createConductor(ce);
        
        ConductorEntity ce1 = factory.manufacturePojo(ConductorEntity.class);
        conductorLogic.createConductor(ce1);
        
        AgendaEntity agenda = factory.manufacturePojo(AgendaEntity.class);
        
        ReservaEntity re = factory.manufacturePojo(ReservaEntity.class);
        
        re.setFechaReserva(DateUtils.addMinutes(re.getFechaServicio(), -30));
        
        reservaLogic.createReserva(re);
        agenda.setReserva(re);
        
        agenda.setConductor(ce1);
        
        AgendaEntity ag= agendaLogic.CreateAgenda(agenda);
        agendaLogic.removeConductor(ag);
        agendaLogic.setConductor(ag, ce);
        
        AgendaEntity result = em.find(AgendaEntity.class, ag.getId());
        Assert.assertEquals(ce, result.getConductor());
    }
}
