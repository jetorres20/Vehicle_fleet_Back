/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.test.logic;

import co.edu.uniandes.csw.automotor.ejb.PracticaLogic;
import co.edu.uniandes.csw.automotor.ejb.ProfesorLogic;
import co.edu.uniandes.csw.automotor.entities.PracticaEntity;
import co.edu.uniandes.csw.automotor.entities.ProfesorEntity;
import co.edu.uniandes.csw.automotor.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.automotor.persistence.PracticaPersistence;
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
public class PracticaLogicTest {
            @Deployment
     public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PracticaEntity.class.getPackage())
                .addPackage(PracticaLogic.class.getPackage())
                .addPackage(PracticaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
     PodamFactory factory=new PodamFactoryImpl();
     @Inject
     private PracticaLogic proflo;
     @Inject
     private ProfesorLogic proflo1;
      @PersistenceContext
     private EntityManager em;
      
        @Test 
     public void createPractica()throws BusinessLogicException{
         
         PracticaEntity pro=factory.manufacturePojo(PracticaEntity.class);
         ProfesorEntity prof=factory.manufacturePojo(ProfesorEntity.class);
         
         pro.setDuracion(200.0);
         prof.setIdentificacion(1234001);
         pro.setProfesor(prof);
         
         proflo1.createProfesor(prof);
         PracticaEntity resul=proflo.createPractica(pro);
         Assert.assertNotNull(resul);
         
         PracticaEntity buscado=em.find(PracticaEntity.class, pro.getId());
         Assert.assertEquals(resul.getDestino(), buscado.getDestino());
         
     }
     
     
           @Test(expected = BusinessLogicException.class)
     public void createPracticaDescripcioNull()throws BusinessLogicException{
         
         PracticaEntity pro=factory.manufacturePojo(PracticaEntity.class);
         ProfesorEntity prof=factory.manufacturePojo(ProfesorEntity.class);
         
         pro.setDuracion(200.0);
         prof.setIdentificacion(12342444);
         pro.setProfesor(prof);
         ProfesorEntity resulpro=proflo1.createProfesor(prof);
         pro.setDescripcion(null);
         PracticaEntity resul=proflo.createPractica(pro);
    
         
     }
     
                @Test(expected = BusinessLogicException.class)
     public void createPracticaDestinoNull()throws BusinessLogicException{
         
         PracticaEntity pro=factory.manufacturePojo(PracticaEntity.class);
         ProfesorEntity prof=factory.manufacturePojo(ProfesorEntity.class);
         
         pro.setDuracion(200.0);
         prof.setIdentificacion(12340292);
         pro.setProfesor(prof);
         ProfesorEntity resulpro=proflo1.createProfesor(prof);
         pro.setDestino(null);
         PracticaEntity resul=proflo.createPractica(pro);
    
         
     }
                @Test(expected = BusinessLogicException.class)
     public void createProfesorNull()throws BusinessLogicException{
         
         PracticaEntity pro=factory.manufacturePojo(PracticaEntity.class);
         ProfesorEntity prof=factory.manufacturePojo(ProfesorEntity.class);
         
         pro.setDuracion(200.0);
         prof.setIdentificacion(1234001);
         pro.setProfesor(prof);
         ProfesorEntity resulpro=proflo1.createProfesor(prof);
         pro.setProfesor(null);
         PracticaEntity resul=proflo.createPractica(pro);
    
         
     }
     
                @Test(expected = Exception.class)
     public void createPracticaDuracionNull()throws BusinessLogicException{
         
         PracticaEntity pro=factory.manufacturePojo(PracticaEntity.class);
         ProfesorEntity prof=factory.manufacturePojo(ProfesorEntity.class);
         
         pro.setDuracion(200.0);
         prof.setIdentificacion(123476);
         pro.setProfesor(prof);
         ProfesorEntity resulpro=proflo1.createProfesor(prof);
         pro.setDuracion(null);
         PracticaEntity resul=proflo.createPractica(pro);
    
         
     }
     
  
              
      
      
      
    
}
