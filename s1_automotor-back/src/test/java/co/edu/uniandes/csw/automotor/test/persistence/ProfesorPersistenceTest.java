/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.persistence.ProfesorPersistence;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Assert;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Nestor Plata
 */
@RunWith(Arquillian.class)
public class ProfesorPersistenceTest {
    @Deployment
     public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProfesorEntity.class.getPackage())
                .addPackage(ProfesorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
    
     @Inject
     private ProfesorPersistence pp;

     @PersistenceContext
     private EntityManager em;

     @Test
     public void createTest(){
        
         PodamFactory factory=new PodamFactoryImpl();
         ProfesorEntity profesor=factory.manufacturePojo(ProfesorEntity.class);
         ProfesorEntity result=pp.create(profesor);
         Assert.assertNotNull(result);
        
         ProfesorEntity entity=em.find(ProfesorEntity.class, result.getId());
         Assert.assertEquals(profesor.getNombre(), entity.getNombre());
     }
     @Test
          public void findTest(){
        
         PodamFactory factory=new PodamFactoryImpl();
         ProfesorEntity profesor=factory.manufacturePojo(ProfesorEntity.class);
         ProfesorEntity result=pp.create(profesor);
         ProfesorEntity entity=em.find(ProfesorEntity.class, result.getId());
         Assert.assertEquals(profesor.getNombre(), entity.getNombre());
     }
          @Test
          public void findAllTest(){
        Collection<ProfesorEntity> lista = pp.findAll();
        Assert.assertFalse(lista.isEmpty());
        PodamFactory factory = new PodamFactoryImpl();

        ProfesorEntity profesor = factory.manufacturePojo(ProfesorEntity.class);
        pp.create(profesor);

        ProfesorEntity profe2 = factory.manufacturePojo(ProfesorEntity.class);
        pp.create(profe2);

        ProfesorEntity buscado1 = pp.find(profesor);
        ProfesorEntity buscado2 = pp.find(profe2);

        lista = pp.findAll();

        Assert.assertTrue(lista.contains(buscado1));
        Assert.assertTrue(lista.contains(buscado2));

       
     }
    @Test
    public void updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ProfesorEntity profesor = factory.manufacturePojo(ProfesorEntity.class);
        ProfesorEntity resultado = pp.create(profesor);

        Assert.assertEquals(resultado.getNombre(), profesor.getNombre());

        profesor.setNombre("Nestor");
        profesor.setIdentificacion(123);
        pp.update(profesor);

        ProfesorEntity prof1=pp.find(profesor);
        Assert.assertEquals("Nestor", prof1.getNombre());
        Assert.assertEquals(123, prof1.getIdentificacion());

    }
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ProfesorEntity profesor = factory.manufacturePojo(ProfesorEntity.class);
        pp.create(profesor);

        ProfesorEntity profe2 = factory.manufacturePojo(ProfesorEntity.class);
        pp.create(profe2);

        Assert.assertNotNull(pp.find(profesor));
        Assert.assertNotNull(pp.find(profe2));

        pp.delete(profesor.getId());
        Assert.assertNull(pp.find(profesor));

        pp.delete(profe2.getId());
        Assert.assertNull(pp.find(profe2));

    }
}
