package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.SeafoodDish;
import com.jonathan.barweb.respository.SeafoodDishRepository;
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
public class SeafoodDishRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private SeafoodDishRepository repo;
    
    public SeafoodDishRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(SeafoodDishRepository.class);
         SeafoodDish sandwitch = new SeafoodDish(1).("Fish and Chips").build();
         repo.save(sandwitch);
		 id = sandwitch.getId();
		 Assert.assertNotNull(sandwitch);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readSeafoodDish()
	 {
         repo = ctx.getBean(SeafoodDishRepository.class);
         SeafoodDish sandwitch = repo.findOne(id);
         Assert.assertEquals(SeafoodDish.getName(), "Fish and Chips");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readSeafoodDish")
     private void updateSeafoodDish(){
         repo = ctx.getBean(SeafoodDishRepository.class);
         SeafoodDish sandwitch = repo.findOne(id);
         SeafoodDish updatedSeafoodDish = new SeafoodDish.Builder(1).name("Fried Fish").build();
         repo.save(updatedSeafoodDish);
         
         SeafoodDish newSeafoodDish = repo.findOne(id);
         Assert.assertEquals(newSeafoodDish.getName(), "Fried Fish"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateSeafoodDish")
     private void deleteSeafoodDish(){
         repo = ctx.getBean(SeafoodDishRepository.class);
         SeafoodDish sandwitch = repo.findOne(id);
         repo.delete(sandwitch);
         
         SeafoodDish deletedSeafoodDish = repo.findOne(id);
		 
         Assert.assertNull(deletedSeafoodDish);
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