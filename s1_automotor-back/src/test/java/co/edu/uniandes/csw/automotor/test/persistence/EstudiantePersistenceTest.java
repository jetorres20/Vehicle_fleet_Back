/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.persistence.EstudiantePersistence;
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
public class EstudiantePersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstudianteEntity.class.getPackage())
                .addPackage(EstudiantePersistence.class.getPackage())
                .addAsManifestResource("Meta-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("Meta-INF/beans.xml","beans.xml");
                
    }
    
    @Inject
    private EstudiantePersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    
    @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity estudiante = factory.manufacturePojo(EstudianteEntity.class);
        EstudianteEntity result = ep.create(estudiante);
        Assert.assertNotNull(result);
        EstudianteEntity entity= em.find(EstudianteEntity.class,result.getId());
        
        Assert.assertEquals(estudiante.getName(), entity.getName());
     
    }
    
    @Test
          public void findTest(){

         PodamFactory factory=new PodamFactoryImpl();
         EstudianteEntity  estudiante=factory.manufacturePojo( EstudianteEntity.class);
         EstudianteEntity result=ep.create( estudiante);
         EstudianteEntity entity=em.find( EstudianteEntity.class, result.getId());
         Assert.assertEquals(estudiante.getName(), entity.getName());
     }
          @Test
          public void findAllTest(){
        Collection<EstudianteEntity> lista = ep.finAll();
        Assert.assertFalse(lista.isEmpty());
        PodamFactory factory = new PodamFactoryImpl();

        EstudianteEntity estudiante = factory.manufacturePojo(EstudianteEntity.class);
        ep.create(estudiante);

        EstudianteEntity estudiante2 = factory.manufacturePojo(EstudianteEntity.class);
        ep.create(estudiante2);

        EstudianteEntity buscado1 = ep.find(estudiante.getId());
        EstudianteEntity buscado2 = ep.find(estudiante2.getId());

        lista = ep.finAll();

        Assert.assertTrue(lista.contains(buscado1));
        Assert.assertTrue(lista.contains(buscado2));


     }
    @Test
    public void updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity estudiante = factory.manufacturePojo(EstudianteEntity.class);
        EstudianteEntity resultado = ep.create(estudiante);

        Assert.assertEquals(resultado.getName(), estudiante.getName());

        estudiante.setName("Juan Torres");
        estudiante.setCodigo(201516032);
        ep.update(estudiante);
        EstudianteEntity prac1=ep.find(estudiante.getId());
        Assert.assertEquals("Juan Torres", prac1.getName());
        Assert.assertEquals((Integer)201516032, prac1.getCodigo());

    }
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity estudiante = factory.manufacturePojo(EstudianteEntity.class);
        ep.create(estudiante);

        EstudianteEntity estudiante2 = factory.manufacturePojo(EstudianteEntity.class);
        ep.create(estudiante2);

        Assert.assertNotNull(ep.find(estudiante.getId()));
        Assert.assertNotNull(ep.find(estudiante2.getId()));

        ep.delete(estudiante.getId());
        Assert.assertNull(ep.find(estudiante.getId()));

        ep.delete(estudiante2.getId());
        Assert.assertNull(ep.find(estudiante2.getId()));

    }
    
    
}
