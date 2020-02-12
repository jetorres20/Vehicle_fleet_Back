/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.FranjaHorariaSemanalEntity;
import co.edu.uniandes.csw.automotor.persistence.FranjaHorariaSemanalPersistence;
import java.util.Collection;
import java.util.Date;
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
public class FranjaHorariaSemanalPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FranjaHorariaSemanalPersistence.class.getPackage())
                .addPackage(FranjaHorariaSemanalEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
    
    @Inject
    private FranjaHorariaSemanalPersistence fp;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createTest(){
        PodamFactory factory=new PodamFactoryImpl();
        FranjaHorariaSemanalEntity franja=factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        FranjaHorariaSemanalEntity result=fp.create(franja);         
        Assert.assertNotNull(result);
        FranjaHorariaSemanalEntity entity=em.find(FranjaHorariaSemanalEntity.class, result.getId());
        Assert.assertEquals(franja.getDia(), entity.getDia());
    }
    
    @Test
    public void findTest(){
        
        PodamFactory factory=new PodamFactoryImpl();
        FranjaHorariaSemanalEntity franja=factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        FranjaHorariaSemanalEntity result=fp.create(franja);
        FranjaHorariaSemanalEntity entity=em.find(FranjaHorariaSemanalEntity.class, result.getId());
        Assert.assertEquals(franja.getDia(), entity.getDia());
    }
    
    
    @Test
    public void findAllTest(){
        Collection<FranjaHorariaSemanalEntity> lista = fp.findAll();
        Assert.assertFalse(lista.isEmpty());
        PodamFactory factory = new PodamFactoryImpl();

        FranjaHorariaSemanalEntity franja = factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        fp.create(franja);

        FranjaHorariaSemanalEntity franaja2 = factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        fp.create(franaja2);

        FranjaHorariaSemanalEntity buscado1 = fp.find(franja.getId());
        FranjaHorariaSemanalEntity buscado2 = fp.find(franaja2.getId());

        lista = fp.findAll();

        Assert.assertTrue(lista.contains(buscado1));
        Assert.assertTrue(lista.contains(buscado2));

       
    }
    @Test
    public void updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FranjaHorariaSemanalEntity franja = factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        FranjaHorariaSemanalEntity resultado = fp.create(franja);

        Assert.assertEquals(resultado.getId(), franja.getId());

        franja.setDia(1);
        franja.setHoraInicio(new Date(0, 0, 0, 0, 0));
        franja.setHoraFin(new Date(1, 0, 0, 0, 0));
        fp.update(franja);

        FranjaHorariaSemanalEntity franja2=fp.find(franja.getId());
        Assert.assertEquals(new Date(0, 0, 0, 0, 0), franja2.getHoraInicio());
        Assert.assertEquals(new Date(1, 0, 0, 0, 0), franja2.getHoraFin());
    }
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FranjaHorariaSemanalEntity franaja = factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        fp.create(franaja);

        FranjaHorariaSemanalEntity franja2 = factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        fp.create(franja2);

        Assert.assertNotNull(fp.find(franaja.getId()));
        Assert.assertNotNull(fp.find(franja2.getId()));

        fp.delete(franaja.getId());
        Assert.assertNull(fp.find(franaja.getId()));

        fp.delete(franja2.getId());
        Assert.assertNull(fp.find(franja2.getId()));

    }
}
