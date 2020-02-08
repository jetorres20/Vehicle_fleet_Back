/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.UniversidadEntity;
import co.edu.uniandes.csw.automotor.persistence.UniversidadPersistence;
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
 * @author Juan Villamarin
 */
@RunWith(Arquillian.class)
public class UniversidadPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UniversidadEntity.class.getPackage())
                .addPackage(UniversidadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
    
    @Inject
    private UniversidadPersistence up;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createTest(){
        PodamFactory factory=new PodamFactoryImpl();
        UniversidadEntity universidad=factory.manufacturePojo(UniversidadEntity.class);
        UniversidadEntity result=up.create(universidad);         Assert.assertNotNull(result);
        UniversidadEntity entity=em.find(UniversidadEntity.class, result.getId());
        Assert.assertEquals(universidad.getNombre(), entity.getNombre());
    }
    
    @Test
    public void findTest(){
        
        PodamFactory factory=new PodamFactoryImpl();
        UniversidadEntity universidad=factory.manufacturePojo(UniversidadEntity.class);
        UniversidadEntity result=up.create(universidad);
        UniversidadEntity entity=em.find(UniversidadEntity.class, result.getId());
        Assert.assertEquals(universidad.getNombre(), entity.getNombre());
    }
    
    
    @Test
    public void findAllTest(){
        Collection<UniversidadEntity> lista = up.findAll();
        Assert.assertFalse(lista.isEmpty());
        PodamFactory factory = new PodamFactoryImpl();

        UniversidadEntity universidad = factory.manufacturePojo(UniversidadEntity.class);
        up.create(universidad);

        UniversidadEntity universidad2 = factory.manufacturePojo(UniversidadEntity.class);
        up.create(universidad2);

        UniversidadEntity buscado1 = up.find(universidad.getId());
        UniversidadEntity buscado2 = up.find(universidad2.getId());

        lista = up.findAll();

        Assert.assertTrue(lista.contains(buscado1));
        Assert.assertTrue(lista.contains(buscado2));

       
    }
    @Test
    public void updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        UniversidadEntity universidad = factory.manufacturePojo(UniversidadEntity.class);
        UniversidadEntity resultado = up.create(universidad);

        Assert.assertEquals(resultado.getNombre(), universidad.getNombre());

        universidad.setNombre("Universidad de los Andes");
        universidad.setCiudad("Bogota");
        universidad.setPrivada(true);
        up.update(universidad);

        UniversidadEntity universidad2=up.find(universidad.getId());
        Assert.assertEquals("Universidad de los Andes", universidad2.getNombre());
        Assert.assertEquals("Bogota", universidad2.getCiudad());
        Assert.assertEquals(true, universidad2.getPrivada());
    }
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        UniversidadEntity universidad = factory.manufacturePojo(UniversidadEntity.class);
        up.create(universidad);

        UniversidadEntity universidad2 = factory.manufacturePojo(UniversidadEntity.class);
        up.create(universidad2);

        Assert.assertNotNull(up.find(universidad.getId()));
        Assert.assertNotNull(up.find(universidad2.getId()));

        up.delete(universidad.getId());
        Assert.assertNull(up.find(universidad.getId()));

        up.delete(universidad2.getId());
        Assert.assertNull(up.find(universidad2.getId()));

    }
}
