package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.SoftDrinksSupplier;
import com.jonathan.barweb.respository.SoftDrinksSupplierRepository;
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
public class SoftDrinksSupplierRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private SoftDrinksSupplierRepository repo;
    
    public SoftDrinksSupplierRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(SoftDrinksSupplierRepository.class);
         SoftDrinksSupplier softDrinksSupplier = new SoftDrinksSupplier(1).("Soft Drink Supply").build();
         repo.save(softDrinksSupplier);
		 id = softDrinksSupplier.getId();
		 Assert.assertNotNull(softDrinksSupplier);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readSoftDrinksSupplier()
	 {
         repo = ctx.getBean(SoftDrinksSupplierRepository.class);
         SoftDrinksSupplier softDrinksSupplier = repo.findOne(id);
         Assert.assertEquals(SoftDrinksSupplier.getName(), "Soft Drink Supply");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readSoftDrinksSupplier")
     private void updateSoftDrinksSupplier(){
         repo = ctx.getBean(SoftDrinksSupplierRepository.class);
         SoftDrinksSupplier softDrinksSupplier = repo.findOne(id);
         SoftDrinksSupplier updatedSoftDrinksSupplier = new SoftDrinksSupplier.Builder(1).name("Soft Drink Supply + Co.").build();
         repo.save(updatedSoftDrinksSupplier);
         
         SoftDrinksSupplier newSoftDrinksSupplier = repo.findOne(id);
         Assert.assertEquals(newSoftDrinksSupplier.getName(), "Soft Drink Supply + Co."); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateSoftDrinksSupplier")
     private void deleteSoftDrinksSupplier(){
         repo = ctx.getBean(SoftDrinksSupplierRepository.class);
         SoftDrinksSupplier softDrinksSupplier = repo.findOne(id);
         repo.delete(softDrinksSupplier);
         
         SoftDrinksSupplier deletedSoftDrinksSupplier = repo.findOne(id);
		 
         Assert.assertNull(deletedSoftDrinksSupplier);
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