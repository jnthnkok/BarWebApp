package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.FoodSupplier;
import com.jonathan.barweb.respository.FoodSupplierRepository;
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
public class FoodSupplierRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private FoodSupplierRepository repo;
    
    public FoodSupplierRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(FoodSupplierRepository.class);
         FoodSupplier foodSupplier = new FoodSupplier(1).("Best Food Supply").build();
         repo.save(foodSupplier);
		 id = foodSupplier.getId();
		 Assert.assertNotNull(foodSupplier);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readFoodSupplier()
	 {
         repo = ctx.getBean(FoodSupplierRepository.class);
         FoodSupplier foodSupplier = repo.findOne(id);
         Assert.assertEquals(FoodSupplier.getName(), "Best Food Supply");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readFoodSupplier")
     private void updateFoodSupplier(){
         repo = ctx.getBean(FoodSupplierRepository.class);
         FoodSupplier foodSupplier = repo.findOne(id);
         FoodSupplier updatedFoodSupplier = new FoodSupplier.Builder(1).name("Best Food Supply Co.").build();
         repo.save(updatedFoodSupplier);
         
         FoodSupplier newFoodSupplier = repo.findOne(id);
         Assert.assertEquals(newFoodSupplier.getName(), "Best Food Supply Co."); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateFoodSupplier")
     private void deleteFoodSupplier(){
         repo = ctx.getBean(FoodSupplierRepository.class);
         FoodSupplier foodSupplier = repo.findOne(id);
         repo.delete(foodSupplier);
         
         FoodSupplier deletedFoodSupplier = repo.findOne(id);
		 
         Assert.assertNull(deletedFoodSupplier);
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