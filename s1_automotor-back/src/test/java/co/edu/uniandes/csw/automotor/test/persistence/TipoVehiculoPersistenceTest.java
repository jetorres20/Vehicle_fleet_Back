/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.TipoVehiculoEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.persistence.TipoVehiculoPersistence;
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
public class TipoVehiculoPersistenceTest 
{
    @Deployment 
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TipoVehiculoEntity.class.getPackage())
                .addPackage(TipoVehiculoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }
    
    @Inject 
    TipoVehiculoPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        TipoVehiculoEntity tp_veh = factory.manufacturePojo(TipoVehiculoEntity.class);
        TipoVehiculoEntity result = ep.create(tp_veh);
        Assert.assertNotNull(result);
        
        TipoVehiculoEntity entity = em.find(TipoVehiculoEntity.class, result.getId());
        Assert.assertEquals(tp_veh.getTipo(), entity.getTipo());
    }
    
    @Test
    public void findTest(){
        PodamFactory factory = new PodamFactoryImpl();
        TipoVehiculoEntity tp_veh = factory.manufacturePojo(TipoVehiculoEntity.class);
        TipoVehiculoEntity result = ep.create(tp_veh);
                
        TipoVehiculoEntity entity = em.find(TipoVehiculoEntity.class, result.getId());
        Assert.assertEquals(result, entity);
    }
            
    @Test 
    public void finAllTest(){
        PodamFactory factory = new PodamFactoryImpl();
        TipoVehiculoEntity tp_veh1 = factory.manufacturePojo(TipoVehiculoEntity.class);
        TipoVehiculoEntity data1 = ep.create(tp_veh1);
        
        TipoVehiculoEntity tp_veh2 = factory.manufacturePojo(TipoVehiculoEntity.class);
        TipoVehiculoEntity data2 = ep.create(tp_veh2);
        
        TipoVehiculoEntity tp_veh3 = factory.manufacturePojo(TipoVehiculoEntity.class);
        TipoVehiculoEntity data3 = ep.create(tp_veh3);
        
        Collection<TipoVehiculoEntity> datos = ep.finAll();
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
        TipoVehiculoEntity tp_veh1 = factory.manufacturePojo(TipoVehiculoEntity.class);
        TipoVehiculoEntity data1 = ep.create(tp_veh1);   
        
        TipoVehiculoEntity buscado_pre = em.find(TipoVehiculoEntity.class, data1.getId());
        String pre = buscado_pre.getTipo();
        String post = "nuevoTipo";
        Assert.assertFalse(pre.equals(post));
        buscado_pre.setTipo(post);
        ep.update(buscado_pre);
        TipoVehiculoEntity buscado_post = em.find(TipoVehiculoEntity.class, data1.getId());
        Assert.assertTrue(buscado_post.getTipo().equals(post));
    }
          
    @Test
    public void deleteTest(){
        PodamFactory factory = new PodamFactoryImpl();
        TipoVehiculoEntity tp_veh1 = factory.manufacturePojo(TipoVehiculoEntity.class);
        TipoVehiculoEntity data1 = ep.create(tp_veh1);   
        
        ep.delete(data1.getId());
        Assert.assertNull(ep.find(data1.getId()));
    }
    

}
