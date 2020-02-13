/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.RegistroLogic;
import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.RegistroPersistence;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
 * @author Juan Andres Caycedo S
 */
@RunWith(Arquillian.class)
public class RegistroLogicTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RegistroEntity.class.getPackage())
                .addPackage(RegistroLogic.class.getPackage())
                .addPackage(RegistroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private RegistroLogic registroLogic;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createRegistro() throws BusinessLogicException {
        RegistroEntity entidad = factory.manufacturePojo(RegistroEntity.class);
        Date hoy = new Date();
        Date manana = new Date(hoy.getTime() + TimeUnit.DAYS.toMillis(1));
        entidad.setPrsc(manana);
        entidad.setPrse(manana);
        entidad.setRtm(manana);
        entidad.setSoat(manana);
        RegistroEntity resultado = registroLogic.createRegistro(entidad);
        Assert.assertNotNull(resultado);

        RegistroEntity entidad2 = em.find(RegistroEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getPrsc(), resultado.getPrsc());
    }

    @Test(expected = BusinessLogicException.class)
    public void crearRegistroFechaNull() throws BusinessLogicException {
        RegistroEntity entidad = factory.manufacturePojo(RegistroEntity.class);
        entidad.setSoat(null);
        RegistroEntity resultado = registroLogic.createRegistro(entidad);
    }

    @Test
    public void updateDocuments() throws BusinessLogicException {
        RegistroEntity entidad = factory.manufacturePojo(RegistroEntity.class);
        Date hoy = new Date();
        Date manana = new Date(hoy.getTime() + TimeUnit.DAYS.toMillis(1));
        entidad.setPrsc(manana);
        entidad.setPrse(manana);
        entidad.setRtm(manana);
        entidad.setSoat(manana);
        RegistroEntity resultado = registroLogic.createRegistro(entidad);
        Assert.assertNotNull(resultado);

        Date nuevo = new Date(manana.getTime() + TimeUnit.DAYS.toMillis(365));

        registroLogic.updateSoat(resultado, nuevo);

        RegistroEntity entidad2 = em.find(RegistroEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getSoat(), nuevo);

        Assert.assertNotEquals(entidad2.getRtm(), nuevo);
        registroLogic.updateRTM(resultado, nuevo);
        entidad2 = em.find(RegistroEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getRtm(), nuevo);

        Assert.assertNotEquals(entidad2.getPrsc(), nuevo);
        registroLogic.updatePRSC(resultado, nuevo);
        entidad2 = em.find(RegistroEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getPrsc(), nuevo);
        
        Assert.assertNotEquals(entidad2.getPrse(), nuevo);
        registroLogic.updatePRSE(resultado, nuevo);
        entidad2 = em.find(RegistroEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getPrse(), nuevo);
    }

    @Test(expected = BusinessLogicException.class)
    public void crearSoatFechaNull() throws BusinessLogicException {
        RegistroEntity entidad = factory.manufacturePojo(RegistroEntity.class);
        Date hoy = new Date();
        Date manana = new Date(hoy.getTime() + TimeUnit.DAYS.toMillis(1));
        entidad.setPrsc(manana);
        entidad.setPrse(manana);
        entidad.setRtm(manana);
        entidad.setSoat(manana);
        RegistroEntity resultado = registroLogic.createRegistro(entidad);
        Assert.assertNotNull(resultado);

        Date nuevo = new Date(manana.getTime() - TimeUnit.DAYS.toMillis(365));
        registroLogic.updateSoat(resultado, nuevo);

    }

    @Test(expected = BusinessLogicException.class)
    public void crearPRSCFechaNull() throws BusinessLogicException {
        RegistroEntity entidad = factory.manufacturePojo(RegistroEntity.class);
        Date hoy = new Date();
        Date manana = new Date(hoy.getTime() + TimeUnit.DAYS.toMillis(1));
        entidad.setPrsc(manana);
        entidad.setPrse(manana);
        entidad.setRtm(manana);
        entidad.setSoat(manana);
        RegistroEntity resultado = registroLogic.createRegistro(entidad);
        Assert.assertNotNull(resultado);

        Date nuevo = new Date(manana.getTime() - TimeUnit.DAYS.toMillis(365));
        registroLogic.updatePRSC(resultado, nuevo);

    }

    @Test(expected = BusinessLogicException.class)
    public void crearPRSEFechaNull() throws BusinessLogicException {
        RegistroEntity entidad = factory.manufacturePojo(RegistroEntity.class);
        Date hoy = new Date();
        Date manana = new Date(hoy.getTime() + TimeUnit.DAYS.toMillis(1));
        entidad.setPrsc(manana);
        entidad.setPrse(manana);
        entidad.setRtm(manana);
        entidad.setSoat(manana);
        RegistroEntity resultado = registroLogic.createRegistro(entidad);
        Assert.assertNotNull(resultado);

        Date nuevo = new Date(manana.getTime() - TimeUnit.DAYS.toMillis(365));
        registroLogic.updatePRSE(resultado, nuevo);

    }

}
