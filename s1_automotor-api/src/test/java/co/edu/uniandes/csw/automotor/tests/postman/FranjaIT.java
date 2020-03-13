/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.automotor.tests.postman;

import co.edu.uniandes.csw.automotor.dtos.FranjaHorariaSemanalDTO;
import co.edu.uniandes.csw.automotor.dtos.UniversidadDTO;
import co.edu.uniandes.csw.automotor.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.automotor.resources.FranjaHorariaSemanalResource;
import co.edu.uniandes.csw.automotor.resources.UniversidadResource;
import co.edu.uniandes.csw.postman.tests.PostmanTestBuilder;
import java.io.File;
import java.io.IOException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Juan Villamarin
 */
@RunWith(Arquillian.class)
public class FranjaIT {
    private static final String COLLECTION = "FranjaResourceTest.postman_collection";

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "s1_automotor-api.war")
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                .addPackage(FranjaHorariaSemanalResource.class.getPackage())
                .addPackage(FranjaHorariaSemanalDTO.class.getPackage())
                .addPackage(BusinessLogicExceptionMapper.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/glassfish-resources.xml"));
           
    }
    
    @Test
    @RunAsClient
    public void postman() throws IOException {
        PostmanTestBuilder tp = new PostmanTestBuilder();
        tp.setTestWithoutLogin(COLLECTION, "Entorno-IT.postman_environment");
        String desiredResult = "0";
        Assert.assertEquals("Error en Requests de: " + COLLECTION, desiredResult, tp.getRequests_failed());
        Assert.assertEquals("Error en Assertions de: " + COLLECTION, desiredResult, tp.getAssertions_failed());
    }
}
