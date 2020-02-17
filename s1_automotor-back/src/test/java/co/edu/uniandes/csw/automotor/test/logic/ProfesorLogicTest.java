/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.ProfesorLogic;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.entities.VehiculoEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.ProfesorPersistence;
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
 * @author Nestor Plata
 */
@RunWith(Arquillian.class)
public class ProfesorLogicTest {
        @Deployment
     public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProfesorEntity.class.getPackage())
                .addPackage(ProfesorLogic.class.getPackage())
                .addPackage(ProfesorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
     PodamFactory factory=new PodamFactoryImpl();
     @Inject
     private ProfesorLogic proflo;
      @PersistenceContext
     private EntityManager em;
     
     @Test 
     public void createProfesor()throws BusinessLogicException{
         ProfesorEntity pro=factory.manufacturePojo(ProfesorEntity.class);
         pro.setIdentificacion(1234);
         ProfesorEntity resul=proflo.createProfesor(pro);
         Assert.assertNotNull(resul);
         
         ProfesorEntity buscado=em.find(ProfesorEntity.class, pro.getId());
         Assert.assertEquals(resul.getNombre(), buscado.getNombre());
         
     }
        @Test(expected = BusinessLogicException.class)
    public void crearProfesorNombreNull() throws BusinessLogicException {
        ProfesorEntity nuevo = factory.manufacturePojo(ProfesorEntity.class);
        nuevo.setNombre(null);
         nuevo.setIdentificacion(123);
        ProfesorEntity resultado = proflo.createProfesor(nuevo);

    }
    
         @Test(expected = BusinessLogicException.class)
    public void crearProfesorIdNull() throws BusinessLogicException {
        ProfesorEntity nuevo = factory.manufacturePojo(ProfesorEntity.class);
        nuevo.setIdentificacion(null);
        ProfesorEntity resultado = proflo.createProfesor(nuevo);

    }
    
      @Test(expected = BusinessLogicException.class)
    public void crearProfesorIdNegativa()throws BusinessLogicException {
        ProfesorEntity nuevo = factory.manufacturePojo(ProfesorEntity.class);
        nuevo.setIdentificacion(-4);
        ProfesorEntity resultado = proflo.createProfesor(nuevo);

    }
        

             @Test(expected = BusinessLogicException.class)
    public void crearProfesorMismoIdentficacion() throws BusinessLogicException {
        ProfesorEntity nuevo = factory.manufacturePojo(ProfesorEntity.class);
        nuevo.setIdentificacion(123);
        proflo.createProfesor(nuevo);
         ProfesorEntity nuevo1 = factory.manufacturePojo(ProfesorEntity.class);
        nuevo1.setIdentificacion(123);
        proflo.createProfesor(nuevo1);
        

    }
}
