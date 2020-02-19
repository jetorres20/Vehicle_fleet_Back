/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.EncuestaLogic;
import co.edu.uniandes.csw.automotor.ejb.ProfesorLogic;
import co.edu.uniandes.csw.automotor.ejb.ReservaLogic;
import co.edu.uniandes.csw.automotor.entities.EncuestaEntity;
import co.edu.uniandes.csw.automotor.entities.EncuestaEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.entities.ReservaEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.EncuestaPersistence;
import co.edu.uniandes.csw.automotor.persistence.ProfesorPersistence;
import co.edu.uniandes.csw.automotor.persistence.ReservaPersistence;
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
 * @author Juan Esteban Torres
 */
@RunWith(Arquillian.class)
public class EncuestaLogicTest {
    
    @Deployment
     public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EncuestaEntity.class.getPackage())
                .addPackage(EncuestaLogic.class.getPackage())
                .addPackage(EncuestaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
     PodamFactory factory=new PodamFactoryImpl();
     @Inject
     private EncuestaLogic estlogic;
      @PersistenceContext
     private EntityManager em;
      @Inject
     private ProfesorPersistence profe;
     @Inject
     private ReservaPersistence reser;
      
      @Test 
     public void createEncuesta()throws BusinessLogicException{
         EncuestaEntity encu=factory.manufacturePojo(EncuestaEntity.class);
         encu.setProfesor(profe.create(factory.manufacturePojo(ProfesorEntity.class)));
         encu.setReserva(reser.create(factory.manufacturePojo(ReservaEntity.class)));
         
         EncuestaEntity resul=estlogic.createEncuesta(encu);
         Assert.assertNotNull(resul);
         
         EncuestaEntity buscado=em.find(EncuestaEntity.class, encu.getId());
         Assert.assertEquals(resul.getId(), buscado.getId());
         
     }
     
     @Test(expected = BusinessLogicException.class)
    public void crearEncuestaRegistroNull() throws BusinessLogicException {
        EncuestaEntity nuevo = factory.manufacturePojo(EncuestaEntity.class);
        nuevo.setProfesor(profe.create(factory.manufacturePojo(ProfesorEntity.class)));
        nuevo.setReserva(reser.create(factory.manufacturePojo(ReservaEntity.class)));
        nuevo.setCalificacion(null);
        EncuestaEntity resultado = estlogic.createEncuesta(nuevo);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void crearEncuestaProfesorNull() throws BusinessLogicException {
        EncuestaEntity nuevo = factory.manufacturePojo(EncuestaEntity.class);
        nuevo.setReserva(reser.create(factory.manufacturePojo(ReservaEntity.class)));
        EncuestaEntity resultado = estlogic.createEncuesta(nuevo);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void crearEncuestaReservaNull() throws BusinessLogicException {
        EncuestaEntity nuevo = factory.manufacturePojo(EncuestaEntity.class);
        nuevo.setProfesor(profe.create(factory.manufacturePojo(ProfesorEntity.class)));
        EncuestaEntity resultado = estlogic.createEncuesta(nuevo);
    }
    
     
     
    
    
}
