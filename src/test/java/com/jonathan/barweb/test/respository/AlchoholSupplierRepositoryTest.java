package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.conf.ConnectionConfig;
import com.jonathan.barweb.app.domain.Supplier.AlcoholSupplier;
import com.jonathan.barweb.app.respository.AlchoholSupplierRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jonathan
 */
public class AlchoholSupplierRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private AlchoholSupplierRepository repo;
    
    public AlchoholSupplierRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(AlchoholSupplierRepository.class);
         AlcoholSupplier alchoholSupplier = new AlchoholSupplier(1).("Supreme Liquors Co.").build();
         repo.save(alchoholSupplier);
		 id = alchoholSupplier.getId();
		 Assert.assertNotNull(alchoholSupplier);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readAlchoholSupplier()
	 {
         repo = ctx.getBean(AlchoholSupplierRepository.class);
         AlchoholSupplier alchoholSupplier = repo.findOne(id);
         Assert.assertEquals(AlchoholSupplier.getName(), "Supreme Liquors Co.");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readAlchoholSupplier")
     private void updateAlchoholSupplier(){
         repo = ctx.getBean(AlchoholSupplierRepository.class);
         AlchoholSupplier alchoholSupplier = repo.findOne(id);
         AlchoholSupplier updatedAlchoholSupplier = new AlchoholSupplier.Builder(1).name("Supreme Liquors and Co.").build();
         repo.save(updatedAlchoholSupplier);
         
         AlchoholSupplier newAlchoholSupplier = repo.findOne(id);
         Assert.assertEquals(newAlchoholSupplier.getName(), "Supreme Liquors and Co."); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateAlchoholSupplier")
     private void deleteAlchoholSupplier(){
         repo = ctx.getBean(AlchoholSupplierRepository.class);
         AlchoholSupplier alchoholSupplier = repo.findOne(id);
         repo.delete(alchoholSupplier);
         
         AlchoholSupplier deletedAlchoholSupplier = repo.findOne(id);
		 
         Assert.assertNull(deletedAlchoholSupplier);
     }
	 
	 
	 
    @BeforeClass
    public static void setUpClass() throws Exception {
         ctx = new AnnotationConfigApplicationContext(ConnectionConfig.class);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}