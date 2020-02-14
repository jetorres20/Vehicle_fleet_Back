/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.VehiculoLogic;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.VehiculoPersistence;
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

    @PersistenceContext
    private EntityManager em;

    @Test
    public void crearVehiculo() throws BusinessLogicException {
        VehiculoEntity nuevo = factory.manufacturePojo(VehiculoEntity.class);
        nuevo.setPlaca("GQS276");
        nuevo.setCapacidad(2);
        VehiculoEntity resultado = vehiculoLogic.createVehiculo(nuevo);
        Assert.assertNotNull(resultado);
        VehiculoEntity buscado = em.find(VehiculoEntity.class, resultado.getId());
        Assert.assertEquals(buscado, resultado);
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
}
