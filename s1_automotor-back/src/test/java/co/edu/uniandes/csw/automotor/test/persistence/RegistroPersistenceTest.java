/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.persistence.RegistroPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class RegistroPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RegistroEntity.class.getPackage())
                .addPackage(RegistroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private RegistroPersistence rp;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity resultado = rp.create(registro);
        org.junit.Assert.assertNotNull(resultado);

        RegistroEntity entity = em.find(RegistroEntity.class, resultado.getId());

        Assert.assertEquals(registro.getPrsc(), entity.getPrsc());
    }

    @Test
    public void findAllTest() {
        Collection<RegistroEntity> entidades = rp.finAll();
        Assert.assertFalse(entidades.isEmpty());

        PodamFactory factory = new PodamFactoryImpl();

        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity resultado = rp.create(registro);

        RegistroEntity registro2 = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity resultado2 = rp.create(registro2);

        RegistroEntity buscado1 = rp.find(registro.getId());
        RegistroEntity buscado2 = rp.find(registro2.getId());

        entidades = rp.finAll();

        Assert.assertTrue(entidades.contains(buscado1));
        Assert.assertTrue(entidades.contains(buscado2));

        RegistroEntity registro3 = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity resultado3 = rp.create(registro3);
        RegistroEntity buscado3 = rp.find(registro3.getId());

        entidades = rp.finAll();

        Assert.assertTrue(entidades.contains(buscado3));

    }

    @Test
    public void findTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity resultado = rp.create(registro);
        RegistroEntity nulo = rp.find(0);

        Assert.assertNotNull(resultado);
        Assert.assertNull(nulo);

        RegistroEntity entidad = rp.find(resultado.getId());
        Assert.assertEquals(registro.getPrsc(), entidad.getPrsc());
    }

    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity resultado = rp.create(registro);

        RegistroEntity registro2 = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity resultado2 = rp.create(registro2);

        RegistroEntity entidad = rp.find(resultado.getId());
        Assert.assertEquals(registro.getPrsc(), entidad.getPrsc());

        rp.delete(registro.getId());
        Assert.assertNull(rp.find(resultado.getId()));
        Assert.assertNotNull(rp.find(resultado2.getId()));

        rp.delete(registro2.getId());
        Assert.assertNull(rp.find(resultado2.getId()));

    }
    
     @Test
    public void updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity resultado = rp.create(registro);

        Assert.assertEquals(resultado.getPrsc(), registro.getPrsc());

        Date fecha1 = new Date();
        Date fecha2 = new Date();
        registro.setPrsc(fecha1);
        registro.setRtm(fecha2);
        rp.update(registro);

        Assert.assertEquals(fecha1, resultado.getPrsc());
        Assert.assertEquals(fecha2, resultado.getRtm());

    }
}
