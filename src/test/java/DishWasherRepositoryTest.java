package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.DishWasher;
import com.jonathan.barweb.respository.DishWasherRepository;
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
public class DishWasherRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private DishWasherRepository repo;
    
    public DishWasherRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(DishWasherRepository.class);
         DishWasher dishWasher = new DishWasher(1).("Rebecca").build();
         repo.save(dishWasher);
		 id = dishWasher.getId();
		 Assert.assertNotNull(dishWasher);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readDishWasher()
	 {
         repo = ctx.getBean(DishWasherRepository.class);
         DishWasher dishWasher = repo.findOne(id);
         Assert.assertEquals(DishWasher.getName(), "Rebecca");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readDishWasher")
     private void updateDishWasher(){
         repo = ctx.getBean(DishWasherRepository.class);
         DishWasher dishWasher = repo.findOne(id);
         DishWasher updatedDishWasher = new DishWasher.Builder(1).name("Erza").build();
         repo.save(updatedDishWasher);
         
         DishWasher newDishWasher = repo.findOne(id);
         Assert.assertEquals(newDishWasher.getName(), "Erza"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateDishWasher")
     private void deleteDishWasher(){
         repo = ctx.getBean(DishWasherRepository.class);
         DishWasher dishWasher = repo.findOne(id);
         repo.delete(dishWasher);
         
         DishWasher deletedDishWasher = repo.findOne(id);
		 
         Assert.assertNull(deletedDishWasher);
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