/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.persistence;

import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.persistence.VehiculoPersistence;
import com.sun.corba.ee.spi.trace.Poa;
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
import uk.co.jemos.podam.common.PodamBooleanValue;

/**
 *
 * @author Juan Andres Caycedo S.
 */
@RunWith(Arquillian.class)
public class VehiculoPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VehiculoEntity.class.getPackage())
                .addPackage(VehiculoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }

    @Inject
    private VehiculoPersistence vp;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        VehiculoEntity vehiculo = factory.manufacturePojo(VehiculoEntity.class);
        VehiculoEntity resultado = vp.create(vehiculo);
        Assert.assertNotNull(resultado);

        VehiculoEntity entidad = em.find(VehiculoEntity.class, resultado.getId());

        Assert.assertEquals(vehiculo.getMarca(), entidad.getMarca());
    }

    @Test
    public void findAllTest() {
        Collection<VehiculoEntity> entidades = vp.finAll();
        Assert.assertFalse(entidades.isEmpty());

        PodamFactory factory = new PodamFactoryImpl();

        VehiculoEntity vehiculo = factory.manufacturePojo(VehiculoEntity.class);
        VehiculoEntity resultado = vp.create(vehiculo);

        VehiculoEntity vehiculo2 = factory.manufacturePojo(VehiculoEntity.class);
        VehiculoEntity resultado2 = vp.create(vehiculo2);

        VehiculoEntity buscado1 = vp.find(vehiculo.getId());
        VehiculoEntity buscado2 = vp.find(vehiculo2.getId());

        entidades = vp.finAll();

        Assert.assertTrue(entidades.contains(buscado1));
        Assert.assertTrue(entidades.contains(buscado2));

        VehiculoEntity vehiculo3 = factory.manufacturePojo(VehiculoEntity.class);
        VehiculoEntity resultado3 = vp.create(vehiculo3);
        VehiculoEntity buscado3 = vp.find(vehiculo3.getId());

        entidades = vp.finAll();

        Assert.assertTrue(entidades.contains(buscado3));

    }

    @Test
    public void findTest() {
        PodamFactory factory = new PodamFactoryImpl();
        VehiculoEntity vehiculoa = factory.manufacturePojo(VehiculoEntity.class);
        VehiculoEntity resultadoA = vp.create(vehiculoa);
        VehiculoEntity nulo = vp.find(0);

        Assert.assertNotNull(resultadoA);
        Assert.assertNull(nulo);

        VehiculoEntity entidad = vp.find(resultadoA.getId());
        Assert.assertEquals(vehiculoa.getMarca(), entidad.getMarca());
    }

    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        VehiculoEntity vehiculo = factory.manufacturePojo(VehiculoEntity.class);
        VehiculoEntity resultado = vp.create(vehiculo);

        VehiculoEntity vehiculo2 = factory.manufacturePojo(VehiculoEntity.class);
        VehiculoEntity resultado2 = vp.create(vehiculo2);

        VehiculoEntity entidad = vp.find(resultado.getId());
        Assert.assertEquals(vehiculo.getMarca(), entidad.getMarca());

        vp.delete(vehiculo.getId());
        Assert.assertNull(vp.find(resultado.getId()));
        Assert.assertNotNull(vp.find(resultado2.getId()));

        vp.delete(vehiculo2.getId());
        Assert.assertNull(vp.find(resultado2.getId()));

    }

    @Test
    public void updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        VehiculoEntity vehiculo = factory.manufacturePojo(VehiculoEntity.class);
        VehiculoEntity resultado = vp.create(vehiculo);

        Assert.assertEquals(resultado.getMarca(), vehiculo.getMarca());

        vehiculo.setMarca("Renault");
        vehiculo.setModelo("Kwid");
        vp.update(vehiculo);

        Assert.assertEquals("Renault", resultado.getMarca());
        Assert.assertEquals("Kwid", resultado.getModelo());

    }


}
