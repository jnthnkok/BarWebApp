package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.VegetarianDish;
import com.jonathan.barweb.respository.VegetarianDishRepository;
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
public class VegetarianDishRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private VegetarianDishRepository repo;
    
    public VegetarianDishRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(VegetarianDishRepository.class);
         VegetarianDish vegetarianDish = new VegetarianDish(1).("Vegetarian Pasta").build();
         repo.save(vegetarianDish);
		 id = vegetarianDish.getId();
		 Assert.assertNotNull(vegetarianDish);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readVegetarianDish()
	 {
         repo = ctx.getBean(VegetarianDishRepository.class);
         VegetarianDish vegetarianDish = repo.findOne(id);
         Assert.assertEquals(VegetarianDish.getName(), "Vegetarian Pasta");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readVegetarianDish")
     private void updateVegetarianDish(){
         repo = ctx.getBean(VegetarianDishRepository.class);
         VegetarianDish vegetarianDish = repo.findOne(id);
         VegetarianDish updatedVegetarianDish = new VegetarianDish.Builder(1).name("Salad").build();
         repo.save(updatedVegetarianDish);
         
         VegetarianDish newVegetarianDish = repo.findOne(id);
         Assert.assertEquals(newVegetarianDish.getName(), "Salad"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateVegetarianDish")
     private void deleteVegetarianDish(){
         repo = ctx.getBean(VegetarianDishRepository.class);
         VegetarianDish vegetarianDish = repo.findOne(id);
         repo.delete(vegetarianDish);
         
         VegetarianDish deletedVegetarianDish = repo.findOne(id);
		 
         Assert.assertNull(deletedVegetarianDish);
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