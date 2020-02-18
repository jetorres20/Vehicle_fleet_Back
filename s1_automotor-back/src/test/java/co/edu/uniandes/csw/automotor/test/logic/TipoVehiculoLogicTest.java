/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.TipoVehiculoLogic;
import co.edu.uniandes.csw.automotor.entities.TipoVehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.TipoVehiculoPersistence;
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
public class TipoVehiculoLogicTest {
    
    @Deployment 
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TipoVehiculoEntity.class.getPackage())
                .addPackage(TipoVehiculoLogic.class.getPackage())
                .addPackage(TipoVehiculoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TipoVehiculoLogic tipoVehiculoLogic;    
    
    @PersistenceContext
    private EntityManager em;
    
    @Test 
    public void createTipoVehiculo() throws BusinessLogicException
    {
        TipoVehiculoEntity newEntity = factory.manufacturePojo(TipoVehiculoEntity.class);
        TipoVehiculoEntity result = tipoVehiculoLogic.createTipoVehiculo(newEntity);
        Assert.assertNotNull(result);       
        
        TipoVehiculoEntity entity = em.find(TipoVehiculoEntity.class, result.getId());
        Assert.assertEquals(entity.getTipo(), result.getTipo());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createTipoVehiculoTipoNull() throws BusinessLogicException
    {
        TipoVehiculoEntity newEntity = factory.manufacturePojo(TipoVehiculoEntity.class);
        newEntity.setTipo(null);
        TipoVehiculoEntity result = tipoVehiculoLogic.createTipoVehiculo(newEntity);
    }
    
}
