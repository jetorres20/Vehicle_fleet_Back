/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.EncuestaEntity;
import co.edu.uniandes.csw.automotor.persistence.EncuestaPersistence;
import java.util.Collection;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Esteban Torres
 */
@RunWith(Arquillian.class)
public class EncuestaPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EncuestaEntity.class.getPackage())
                .addPackage(EncuestaPersistence.class.getPackage())
                .addAsManifestResource("Meta-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("Meta-INF/beans.xml","beans.xml");
                
    }
    
    @Inject
    private EncuestaPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    
    @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        EncuestaEntity encuesta = factory.manufacturePojo(EncuestaEntity.class);
        EncuestaEntity result = ep.create(encuesta);
        Assert.assertNotNull(result);
        EncuestaEntity entity= em.find(EncuestaEntity.class,result.getId());
        
        Assert.assertEquals(encuesta.getId(), entity.getId());
     
    }
    
    @Test
          public void findTest(){

         PodamFactory factory=new PodamFactoryImpl();
         EncuestaEntity encuesta= factory.manufacturePojo( EncuestaEntity.class);
         EncuestaEntity result=ep.create(encuesta);
         EncuestaEntity entity=em.find( EncuestaEntity.class, result.getId());
         Assert.assertEquals(encuesta.getCalificacion(), entity.getCalificacion());
     }
          @Test
          public void findAllTest(){
        Collection<EncuestaEntity> lista = ep.finAll();
        Assert.assertFalse(lista.isEmpty());
        PodamFactory factory = new PodamFactoryImpl();

        EncuestaEntity encuesta = factory.manufacturePojo(EncuestaEntity.class);
        ep.create(encuesta);

        EncuestaEntity encuesta2 = factory.manufacturePojo(EncuestaEntity.class);
        ep.create(encuesta2);

        EncuestaEntity buscado1 = ep.find(encuesta.getId());
        EncuestaEntity buscado2 = ep.find(encuesta2.getId());

        lista = ep.finAll();

        Assert.assertTrue(lista.contains(buscado1));
        Assert.assertTrue(lista.contains(buscado2));


     }
    @Test
    public void updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EncuestaEntity encuesta = factory.manufacturePojo(EncuestaEntity.class);
        EncuestaEntity resultado = ep.create(encuesta);

        Assert.assertEquals(resultado.getId(), encuesta.getId());

        encuesta.setComentario("Perfecto");
        encuesta.setCalificacion(5);
        ep.update(encuesta);
        EncuestaEntity prac1=ep.find(encuesta.getId());
        Assert.assertEquals("Perfecto", prac1.getComentario());
        Assert.assertEquals(5, prac1.getCalificacion());

    }
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EncuestaEntity encuesta = factory.manufacturePojo(EncuestaEntity.class);
        ep.create(encuesta);

        EncuestaEntity encuesta2 = factory.manufacturePojo(EncuestaEntity.class);
        ep.create(encuesta2);

        Assert.assertNotNull(ep.find(encuesta.getId()));
        Assert.assertNotNull(ep.find(encuesta2.getId()));

        ep.delete(encuesta.getId());
        Assert.assertNull(ep.find(encuesta.getId()));

        ep.delete(encuesta2.getId());
        Assert.assertNull(ep.find(encuesta2.getId()));

    }
    
}

