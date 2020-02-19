
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.EstudianteLogic;
import co.edu.uniandes.csw.automotor.entities.EstudianteEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.EstudiantePersistence;
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
public class EstudianteLogicTest {
    
     @Deployment
     public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstudianteEntity.class.getPackage())
                .addPackage(EstudianteLogic.class.getPackage())
                .addPackage(EstudiantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
     PodamFactory factory=new PodamFactoryImpl();
     @Inject
     private EstudianteLogic estlogic;
      @PersistenceContext
     private EntityManager em;
     
     @Test 
     public void createEstudiante()throws BusinessLogicException{
         EstudianteEntity est=factory.manufacturePojo(EstudianteEntity.class);
         est.setCodigo(1234);
         EstudianteEntity resul=estlogic.createEstudiante(est);
         Assert.assertNotNull(resul);
         
         EstudianteEntity buscado=em.find(EstudianteEntity.class, est.getId());
         Assert.assertEquals(resul.getCodigo(), buscado.getCodigo());
         
     }
        @Test(expected = BusinessLogicException.class)
    public void crearEstudianteNombreNull() throws BusinessLogicException {
        EstudianteEntity nuevo = factory.manufacturePojo(EstudianteEntity.class);
        nuevo.setName(null);
         nuevo.setCodigo(1234);
        EstudianteEntity resultado = estlogic.createEstudiante(nuevo);

    }
    
         @Test(expected = BusinessLogicException.class)
    public void crearEstudianteIdNull() throws BusinessLogicException {
        EstudianteEntity nuevo = factory.manufacturePojo(EstudianteEntity.class);
        nuevo.setCodigo(null);
        EstudianteEntity resultado = estlogic.createEstudiante(nuevo);

    }
    
      @Test(expected = BusinessLogicException.class)
    public void crearEstudianteIdNegativa()throws BusinessLogicException {
        EstudianteEntity nuevo = factory.manufacturePojo(EstudianteEntity.class);
        nuevo.setCodigo(-1);
        EstudianteEntity resultado = estlogic.createEstudiante(nuevo);

    }
        

             @Test(expected = BusinessLogicException.class)
    public void crearEstudianteMismoIdentficacion() throws BusinessLogicException {
        EstudianteEntity nuevo = factory.manufacturePojo(EstudianteEntity.class);
        nuevo.setCodigo(123);
        estlogic.createEstudiante(nuevo);
         EstudianteEntity nuevo1 = factory.manufacturePojo(EstudianteEntity.class);
        nuevo1.setCodigo(123);
        estlogic.createEstudiante(nuevo1);
        

    }
    
}
