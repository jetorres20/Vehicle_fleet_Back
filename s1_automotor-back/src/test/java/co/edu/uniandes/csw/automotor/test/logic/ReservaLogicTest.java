/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.RegistroLogic;
import co.edu.uniandes.csw.automotor.ejb.ReservaLogic;
import co.edu.uniandes.csw.automotor.ejb.TipoVehiculoLogic;
import co.edu.uniandes.csw.automotor.ejb.VehiculoLogic;
import co.edu.uniandes.csw.automotor.entities.RegistroEntity;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import co.edu.uniandes.csw.automotor.entities.TipoVehiculoEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.ReservaPersistence;
import co.edu.uniandes.csw.automotor.persistence.VehiculoPersistence;
import java.util.Date;
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
 * @author Mateo Laguna G.
 */
@RunWith(Arquillian.class)
public class ReservaLogicTest {
    
    @Deployment 
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ReservaLogic reservaLogic;
    
    @Inject
    private VehiculoLogic vehiculoLogic;
    
    @Inject
    private RegistroLogic registroLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test 
    public void createReserva() throws BusinessLogicException
    {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setFechaReserva(new Date(2020, 2, 4, 14, 30));
        newEntity.setFechaServicio(new Date(2020, 6, 4, 13, 30));
        
        ReservaEntity result = reservaLogic.createReserva(newEntity);
        Assert.assertNotNull(result);       
        
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(entity.getFechaReserva(), result.getFechaReserva());
        Assert.assertEquals(entity.getFechaServicio(), result.getFechaServicio());
        Assert.assertEquals(entity.getId(), result.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createReservaFechaReservaNull() throws BusinessLogicException
    {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setFechaReserva(null);
        ReservaEntity result = reservaLogic.createReserva(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createReservaFechaServicioNull() throws BusinessLogicException
    {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setFechaServicio(null);
        ReservaEntity result = reservaLogic.createReserva(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createReservaFechaReservaPosterior() throws BusinessLogicException
    {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setFechaReserva(new Date(2020, 6, 4, 14, 30));
        newEntity.setFechaServicio(new Date(2020, 6, 4, 13, 30));
        ReservaEntity result = reservaLogic.createReserva(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createReservaDuplicadas() throws BusinessLogicException
    {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        VehiculoEntity newVehiculo = factory.manufacturePojo(VehiculoEntity.class);
        RegistroEntity newRegistro = factory.manufacturePojo(RegistroEntity.class);
        newEntity.setFechaReserva(new Date(2020, 2, 4, 14, 30));
        newEntity.setFechaServicio(new Date(2020, 6, 4, 13, 30));
        registroLogic.createRegistro(newRegistro);
        newVehiculo.setRegistro(newRegistro);
        vehiculoLogic.createVehiculo(newVehiculo);
        newEntity.setVehiculo(newVehiculo);
            
        ReservaEntity result = reservaLogic.createReservaContext(newEntity);
        
        ReservaEntity newEntity2 = factory.manufacturePojo(ReservaEntity.class);
        newEntity2.setFechaReserva(new Date(2020, 2, 4, 14, 30));
        newEntity2.setFechaServicio(new Date(2020, 6, 4, 13, 30));
        newEntity2.setVehiculo(newVehiculo);
        
        ReservaEntity result2 = reservaLogic.createReserva(newEntity2);
    }
    
}
