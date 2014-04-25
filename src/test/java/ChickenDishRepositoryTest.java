package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.ChickenDish;
import com.jonathan.barweb.respository.ChickenDishRepository;
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
public class ChickenDishRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private ChickenDishRepository repo;
    
    public ChickenDishRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(ChickenDishRepository.class);
         ChickenDish chickenDish = new ChickenDish(1).("Chicken Nuggets").build();
         repo.save(chickenDish);
		 id = chickenDish.getId();
		 Assert.assertNotNull(chickenDish);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readChickenDish()
	 {
         repo = ctx.getBean(ChickenDishRepository.class);
         ChickenDish chickenDish = repo.findOne(id);
         Assert.assertEquals(ChickenDish.getName(), "Chicken Nuggets");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readChickenDish")
     private void updateChickenDish(){
         repo = ctx.getBean(ChickenDishRepository.class);
         ChickenDish chickenDish = repo.findOne(id);
         ChickenDish updatedChickenDish = new ChickenDish.Builder(1).name("Fried Chicken").build();
         repo.save(updatedChickenDish);
         
         ChickenDish newChickenDish = repo.findOne(id);
         Assert.assertEquals(newChickenDish.getName(), "Fried Chicken"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateChickenDish")
     private void deleteChickenDish(){
         repo = ctx.getBean(ChickenDishRepository.class);
         ChickenDish chickenDish = repo.findOne(id);
         repo.delete(chickenDish);
         
         ChickenDish deletedChickenDish = repo.findOne(id);
		 
         Assert.assertNull(deletedChickenDish);
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