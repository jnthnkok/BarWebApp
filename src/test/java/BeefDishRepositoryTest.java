package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.BeefDish;
import com.jonathan.barweb.respository.BeefDishRepository;
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
public class BeefDishRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private BeefDishRepository repo;
    
    public BeefDishRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(BeefDishRepository.class);
         BeefDish beefDish = new BeefDish(1).("Beef Lasagne").build();
         repo.save(beefDish);
		 id = beefDish.getId();
		 Assert.assertNotNull(beefDish);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readBeefDish()
	 {
         repo = ctx.getBean(BeefDishRepository.class);
         BeefDish beefDish = repo.findOne(id);
         Assert.assertEquals(BeefDish.getName(), "Beef Lasagne");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readBeefDish")
     private void updateBeefDish(){
         repo = ctx.getBean(BeefDishRepository.class);
         BeefDish beefDish = repo.findOne(id);
         BeefDish updatedBeefDish = new BeefDish.Builder(1).name("Beef Burger").build();
         repo.save(updatedBeefDish);
         
         BeefDish newBeefDish = repo.findOne(id);
         Assert.assertEquals(newBeefDish.getName(), "Beef Burger"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateBeefDish")
     private void deleteBeefDish(){
         repo = ctx.getBean(BeefDishRepository.class);
         BeefDish beefDish = repo.findOne(id);
         repo.delete(beefDish);
         
         BeefDish deletedBeefDish = repo.findOne(id);
		 
         Assert.assertNull(deletedBeefDish);
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