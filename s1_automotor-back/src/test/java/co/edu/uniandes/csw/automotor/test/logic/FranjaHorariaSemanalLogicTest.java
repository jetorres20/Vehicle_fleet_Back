/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.FranjaHorariaSemanalLogic;
import co.edu.uniandes.csw.automotor.entities.ConductorEntity;
import co.edu.uniandes.csw.automotor.entities.FranjaHorariaSemanalEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.ConductorPersistence;
import co.edu.uniandes.csw.automotor.persistence.FranjaHorariaSemanalPersistence;
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
public class FranjaHorariaSemanalLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FranjaHorariaSemanalEntity.class.getPackage())
                .addPackage(FranjaHorariaSemanalPersistence.class.getPackage())
                .addPackage(FranjaHorariaSemanalLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private FranjaHorariaSemanalLogic fl;
    
    private final PodamFactory factory= new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private ConductorPersistence cp;
    
    @Test
    public void createFranja() throws BusinessLogicException{
        FranjaHorariaSemanalEntity franja=factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        ConductorEntity conductor=factory.manufacturePojo(ConductorEntity.class);
        franja.setConductor(cp.create(conductor));
        FranjaHorariaSemanalEntity result=fl.createFranjaHorariaSemanal(franja);
        Assert.assertNotNull(result);
        
        FranjaHorariaSemanalEntity entity=em.find(FranjaHorariaSemanalEntity.class, result.getId());
        Assert.assertEquals(entity, result);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createFranjaStartDateNull() throws BusinessLogicException{
        FranjaHorariaSemanalEntity franja=factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        franja.setStartHour(null);
        fl.createFranjaHorariaSemanal(franja);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createFranjaFinishDateNull()throws BusinessLogicException{
        FranjaHorariaSemanalEntity franja=factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        franja.setFinishHour(null);
        fl.createFranjaHorariaSemanal(franja);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createFranjaConductorNull()throws BusinessLogicException{
        FranjaHorariaSemanalEntity franja=factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        franja.setConductor(null);
        fl.createFranjaHorariaSemanal(franja);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createFranjaDuplicate()throws BusinessLogicException{
        FranjaHorariaSemanalEntity franja=factory.manufacturePojo(FranjaHorariaSemanalEntity.class);
        FranjaHorariaSemanalEntity copy=franja;
        fl.createFranjaHorariaSemanal(franja);
        fl.createFranjaHorariaSemanal(copy);
    }
}
