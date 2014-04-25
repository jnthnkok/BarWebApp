package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.BreakfastDish;
import com.jonathan.barweb.respository.BreakfastDishRepository;
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
public class BreakfastDishRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private BreakfastDishRepository repo;
    
    public BreakfastDishRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(BreakfastDishRepository.class);
         BreakfastDish breakfastDish = new BreakfastDish(1).("Toast").build();
         repo.save(breakfastDish);
		 id = breakfastDish.getId();
		 Assert.assertNotNull(breakfastDish);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readBreakfastDish()
	 {
         repo = ctx.getBean(BreakfastDishRepository.class);
         BreakfastDish breakfastDish = repo.findOne(id);
         Assert.assertEquals(BreakfastDish.getName(), "Toast");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readBreakfastDish")
     private void updateBreakfastDish(){
         repo = ctx.getBean(BreakfastDishRepository.class);
         BreakfastDish breakfastDish = repo.findOne(id);
         BreakfastDish updatedBreakfastDish = new BreakfastDish.Builder(1).name("Bacon and Eggs").build();
         repo.save(updatedBreakfastDish);
         
         BreakfastDish newBreakfastDish = repo.findOne(id);
         Assert.assertEquals(newBreakfastDish.getName(), "Bacon and Eggs"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateBreakfastDish")
     private void deleteBreakfastDish(){
         repo = ctx.getBean(BreakfastDishRepository.class);
         BreakfastDish breakfastDish = repo.findOne(id);
         repo.delete(breakfastDish);
         
         BreakfastDish deletedBreakfastDish = repo.findOne(id);
		 
         Assert.assertNull(deletedBreakfastDish);
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