/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;


import co.edu.uniandes.csw.automotor.entities.PracticaEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.persistence.PracticaPersistence;
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
 * @author Nestor Plata
 */
@RunWith(Arquillian.class)
public class PracticaPersistenceTest {
    @Deployment
     public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PracticaEntity.class.getPackage())
                .addPackage(PracticaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
    
    @Inject
     private PracticaPersistence pp;

     @PersistenceContext
     private EntityManager em;
     
     @Test
     public void createTest(){
        
         PodamFactory factory=new PodamFactoryImpl();
         PracticaEntity practica=factory.manufacturePojo(PracticaEntity.class);
         PracticaEntity result=pp.create(practica);
         Assert.assertNotNull(result);
        
         PracticaEntity entity=em.find(PracticaEntity.class, result.getId());
         Assert.assertEquals(practica.getDestino(), entity.getDestino());
     }
     @Test
          public void findTest(){
        
         PodamFactory factory=new PodamFactoryImpl();
         PracticaEntity  practica=factory.manufacturePojo( PracticaEntity.class);
         PracticaEntity result=pp.create( practica);
         PracticaEntity entity=em.find( PracticaEntity.class, result.getId());
         Assert.assertEquals(practica.getDescripcion(), entity.getDescripcion());
     }
          @Test
          public void findAllTest(){
        Collection<PracticaEntity> lista = pp.findAll();
        Assert.assertFalse(lista.isEmpty());
        PodamFactory factory = new PodamFactoryImpl();

        PracticaEntity practica = factory.manufacturePojo(PracticaEntity.class);
        pp.create(practica);

        PracticaEntity practica2 = factory.manufacturePojo(PracticaEntity.class);
        pp.create(practica2);

        PracticaEntity buscado1 = pp.find(practica);
        PracticaEntity buscado2 = pp.find(practica2);

        lista = pp.findAll();

        Assert.assertTrue(lista.contains(buscado1));
        Assert.assertTrue(lista.contains(buscado2));

       
     }
    @Test
    public void updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
       PracticaEntity practica = factory.manufacturePojo(PracticaEntity.class);
        PracticaEntity resultado = pp.create(practica);

        Assert.assertEquals(resultado.getDescripcion(), practica.getDescripcion());

        practica.setDescripcion("aloha");
        practica.setDestino("CALI");
        pp.update(practica);
        PracticaEntity prac1=pp.find(practica);
        Assert.assertEquals("aloha", resultado.getDescripcion());
        Assert.assertEquals("CALI", resultado.getDestino());

    }
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PracticaEntity practica = factory.manufacturePojo(PracticaEntity.class);
        pp.create(practica);

        PracticaEntity practica2 = factory.manufacturePojo(PracticaEntity.class);
        pp.create(practica2);

        Assert.assertNotNull(pp.find(practica));
        Assert.assertNotNull(pp.find(practica2));

        pp.delete(practica.getId());
        Assert.assertNull(pp.find(practica));

        pp.delete(practica2.getId());
        Assert.assertNull(pp.find(practica2));

    }
     
}
