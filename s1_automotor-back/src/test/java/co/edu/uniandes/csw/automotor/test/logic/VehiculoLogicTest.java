/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.VehiculoLogic;
import co.edu.uniandes.csw.automotor.ejb.RegistroLogic;
import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.VehiculoPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
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
 * @author Juan Andres Caycedo S.
 */
@Stateless

@RunWith(Arquillian.class)
public class VehiculoLogicTest {

    @Deployment
    public static JavaArchive createDeployjment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VehiculoEntity.class.getPackage())
                .addPackage(VehiculoPersistence.class.getPackage())
                .addPackage(VehiculoLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private VehiculoLogic vehiculoLogic;

    @Inject
    private RegistroLogic registroLogic;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void crearVehiculo() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);

        nuevo.setPlaca("GQS276");
        nuevo.setCapacidad(2);

        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        Date manana = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        registro.setPrsc(manana);
        registro.setPrse(manana);
        registro.setRtm(manana);
        registro.setSoat(manana);

        registro = registroLogic.createRegistro(registro);
        nuevo.setRegistro(registro);

        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
        Assert.assertNotNull(resultado);
        VehiculoEntity buscado = em.find(VehiculoEntity.class, resultado.getId());
        Assert.assertEquals(buscado, resultado);
    }

    @Test(expected = BusinessLogicException.class)
    public void crearVehiculoRegistroInvalido() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca("GQS271");
        nuevo.setCapacidad(2);

        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        Date manana = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        registro.setPrsc(manana);
        registro.setPrse(manana);
        registro.setRtm(manana);
        registro.setSoat(manana);

        registro = registroLogic.createRegistro(registro);
        nuevo.setRegistro(registro);

        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
        Assert.assertNotNull(resultado);

        VehiculoEntity fraude = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca("GQS273");
        nuevo.setCapacidad(2);
        VehiculoEntity fraudulento = vehiculoLogic.createVehiculo(fraude);

    }

    @Test(expected = BusinessLogicException.class)
    public void crearVehiculoRegistroNull() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setRegistro(null);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
    }

    @Test(expected = BusinessLogicException.class)
    public void crearVehiculoCapacidadNull() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setCapacidad(null);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);

    }

    @Test(expected = BusinessLogicException.class)
    public void crearVehiculoMarcaNull() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setMarca(null);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
    }

    @Test(expected = BusinessLogicException.class)
    public void crearVehiculoModeloNull() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setModelo(null);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);

    }

    @Test(expected = BusinessLogicException.class)
    public void crearVehiculoPlacaNull() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca(null);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);

    }

    @Test(expected = BusinessLogicException.class)
    public void crearVehiculoPlacaSinFormato() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca("1AA111");
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        Date manana = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        registro.setPrsc(manana);
        registro.setPrse(manana);
        registro.setRtm(manana);
        registro.setSoat(manana);
        nuevo.setRegistro(registro);
        try {
            VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
        } catch (BusinessLogicException e) {
            nuevo.setPlaca("A1A111");
            try {
                VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
            } catch (BusinessLogicException r) {
                nuevo.setPlaca("AA1111");
                try {
                    VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
                } catch (BusinessLogicException g) {
                    nuevo.setPlaca("AAAB11");
                    try {
                        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
                    } catch (BusinessLogicException h) {
                        nuevo.setPlaca("AAA1B1");
                        try {
                            VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
                        } catch (BusinessLogicException j) {
                            nuevo.setPlaca("AAA11B");
                            try {
                                VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
                            } catch (BusinessLogicException k) {
                                nuevo.setPlaca("AAA1111");
                                VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(expected = BusinessLogicException.class)
    public void crearVehiculoSinCapacidad() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setCapacidad(0);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
    }

    @Test(expected = BusinessLogicException.class)
    public void crearVehiculoPlacaRepetida() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setCapacidad(2);
        nuevo.setPlaca("AAA111");
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);

        VehiculoEntity duplicado = factory.manufacturePojo(VehiculoEntity.class);
        duplicado.setCapacidad(Integer.MAX_VALUE);
        duplicado.setPlaca("AAA111");
        VehiculoEntity resultado2 = vehiculoLogic.createVehiculo(duplicado);

    }

    @Test
    public void getVehiculosTest() throws BusinessLogicException {

        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca("GQS275");
        nuevo.setCapacidad(2);
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        Date manana = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        registro.setPrsc(manana);
        registro.setPrse(manana);
        registro.setRtm(manana);
        registro.setSoat(manana);
        registro = registroLogic.createRegistro(registro);
        nuevo.setRegistro(registro);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);

        VehiculoEntity nuevo2 = factory.manufacturePojo(VehiculoEntity.class);
        nuevo2.setPlaca("GQS274");
        nuevo2.setCapacidad(2);
        RegistroEntity registro2 = factory.manufacturePojo(RegistroEntity.class);
        registro2.setPrsc(manana);
        registro2.setPrse(manana);
        registro2.setRtm(manana);
        registro2.setSoat(manana);
        registro2 = registroLogic.createRegistro(registro2);
        nuevo2.setRegistro(registro2);
        VehiculoEntity resultado2 = vehiculoLogic.createVehiculo(nuevo2);

        List<VehiculoEntity> prueba = vehiculoLogic.getVehiculos();
        Assert.assertTrue(prueba.contains(resultado));
        Assert.assertTrue(prueba.contains(resultado));

    }

    @Test
    public void getVehiculoTest() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca("GQS345");
        nuevo.setCapacidad(2);
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        Date manana = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        registro.setPrsc(manana);
        registro.setPrse(manana);
        registro.setRtm(manana);
        registro.setSoat(manana);
        registro = registroLogic.createRegistro(registro);
        nuevo.setRegistro(registro);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);

        VehiculoEntity buscado = vehiculoLogic.getVehiculo(resultado.getId());

        Assert.assertEquals(buscado.getCapacidad(), resultado.getCapacidad());
        Assert.assertEquals(buscado.getMarca(), resultado.getMarca());
        Assert.assertEquals(buscado.getModelo(), resultado.getModelo());
        Assert.assertEquals(buscado.getPlaca(), resultado.getPlaca());
    }

    @Test
    public void deleteVehiculoTest() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca("GQS123");
        nuevo.setCapacidad(2);
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        Date manana = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        registro.setPrsc(manana);
        registro.setPrse(manana);
        registro.setRtm(manana);
        registro.setSoat(manana);
        registro = registroLogic.createRegistro(registro);
        nuevo.setRegistro(registro);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);

        vehiculoLogic.deleteVehiculo(resultado.getId());
        Assert.assertNull(vehiculoLogic.getVehiculo(resultado.getId()));

    }

    @Test
    public void updateVehiculo() throws BusinessLogicException {

        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca("GQS395");
        nuevo.setCapacidad(2);
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        Date manana = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        registro.setPrsc(manana);
        registro.setPrse(manana);
        registro.setRtm(manana);
        registro.setSoat(manana);
        registro = registroLogic.createRegistro(registro);
        nuevo.setRegistro(registro);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);

        VehiculoEntity buscado = vehiculoLogic.getVehiculo(resultado.getId());

        Assert.assertEquals(buscado.getCapacidad(), resultado.getCapacidad());
        Assert.assertEquals(buscado.getMarca(), resultado.getMarca());
        Assert.assertEquals(buscado.getModelo(), resultado.getModelo());
        Assert.assertEquals(buscado.getPlaca(), resultado.getPlaca());
    }

    @Test
    public void updateVehiculoTest() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca("GQQ123");
        nuevo.setCapacidad(2);
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        Date manana = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        registro.setPrsc(manana);
        registro.setPrse(manana);
        registro.setRtm(manana);
        registro.setSoat(manana);
        registro = registroLogic.createRegistro(registro);
        nuevo.setRegistro(registro);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
        Assert.assertEquals(resultado.getCapacidad(), nuevo.getCapacidad());

        nuevo.setCapacidad(12334);
        nuevo.setPlaca("JAC918");
        vehiculoLogic.updateVehiculo(1, nuevo);
        resultado = vehiculoLogic.getVehiculo(nuevo.getId());
        Assert.assertEquals((Integer)12334, resultado.getCapacidad());
        Assert.assertEquals("JAC918", resultado.getPlaca());

    }
}
