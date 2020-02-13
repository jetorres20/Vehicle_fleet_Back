/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.persistence.ReservaPersistence;
import co.edu.uniandes.csw.automotor.persistence.VehiculoPersistence;
import java.util.Collection;
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
 * @author Mateo Laguna
 */
@RunWith(Arquillian.class)
public class ReservaPersistenceTest 
{
    @Deployment 
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }
    
    @Inject 
    ReservaPersistence rp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity tp_veh = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = rp.create(tp_veh);
        Assert.assertNotNull(result);
        
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(tp_veh.getMotivoCancelacion(), entity.getMotivoCancelacion());
    }
    
    @Test
    public void findTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity tp_veh = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = rp.create(tp_veh);
                
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(result.getCancelada(), entity.getCancelada());
        entity.setCancelada(Boolean.FALSE);
        result.setCancelada(Boolean.TRUE);
        Assert.assertNotEquals(entity.getCancelada(), result.getCancelada());
        Assert.assertEquals(result.getAgenda(), entity.getAgenda());
        Assert.assertEquals(result.getConductor(), entity.getConductor());
        Assert.assertEquals(result.getEncuesta(), entity.getEncuesta());
        Assert.assertEquals(result.getEstadoValidacion(), entity.getEstadoValidacion());
        Assert.assertEquals(result.getEstudiantes(), entity.getEstudiantes());
        Assert.assertEquals(result.getFechaReserva(), entity.getFechaReserva());
        Assert.assertEquals(result.getFechaServicio(), entity.getFechaServicio());
        Assert.assertEquals(result.getMotivoCancelacion(), entity.getMotivoCancelacion());
        Assert.assertEquals(result.getPractica(), entity.getPractica());
        Assert.assertEquals(result.getProfesor(), entity.getProfesor());
        Assert.assertEquals(result.getReservaRecurrente(), entity.getReservaRecurrente());
        Assert.assertEquals(result.getVehiculo(), entity.getVehiculo());
    }
            
    @Test 
    public void finAllTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity tp_veh1 = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity data1 = rp.create(tp_veh1);
        
        ReservaEntity tp_veh2 = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity data2 = rp.create(tp_veh2);
        
        ReservaEntity tp_veh3 = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity data3 = rp.create(tp_veh3);
        
        Collection<ReservaEntity> datos = rp.finAll();
        Assert.assertNotNull(datos);
        
        /*
        Verifica que la lista datos contenga los tres objetos creados por la fábrica.
        */
        Assert.assertTrue(datos.contains(data1));
        Assert.assertTrue(datos.contains(data2));
        Assert.assertTrue(datos.contains(data3));
        
    }
    
    @Test
    public void updateTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity tp_veh1 = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity data1 = rp.create(tp_veh1);   
        
        ReservaEntity buscado_pre = em.find(ReservaEntity.class, data1.getId());
        String pre = buscado_pre.getMotivoCancelacion();
        String post = "nuevoMotivoCancelacion";
        Assert.assertFalse(pre.equals(post));
        buscado_pre.setMotivoCancelacion(post);
        rp.update(buscado_pre);
        ReservaEntity buscado_post = em.find(ReservaEntity.class, data1.getId());
        Assert.assertTrue(buscado_post.getMotivoCancelacion().equals(post));
    }
          
    @Test
    public void deleteTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity tp_veh1 = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity data1 = rp.create(tp_veh1);   
        
        rp.delete(data1.getId());
        Assert.assertNull(rp.find(data1.getId()));
    }
    

}

