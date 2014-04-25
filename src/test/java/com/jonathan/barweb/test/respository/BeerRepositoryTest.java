package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.domain.Drinks.Beer;
import com.jonathan.barweb.app.respository.BeerRepository;
import com.jonathan.barweb.conf.ConnectionConfig;
import junit.framework.Assert;
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
public class BeerRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private BeerRepository repo;
    
    public BeerRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(BeerRepository.class);
         Beer beer = new Beer.Builder(id).name("Castle Lager").build();
         repo.save(beer);
		 id = beer.getID();
		 Assert.assertNotNull(beer);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readBeer()
	 {
         repo = ctx.getBean(BeerRepository.class);
         Beer beer = repo.findOne(id);
         Assert.assertEquals(beer.getName(), "Castle Lager");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readBeer")
     private void updateBeer(){
         repo = ctx.getBean(BeerRepository.class);
         Beer beer = repo.findOne(id);
         Beer updatedBeer = new Beer.Builder(id).name("Black Label").build();
         repo.save(updatedBeer);
         
         Beer newBeer = repo.findOne(id);
         Assert.assertEquals(newBeer.getName(), "Black Label"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateBeer")
     private void deleteBeer(){
         repo = ctx.getBean(BeerRepository.class);
         Beer beer = repo.findOne(id);
         repo.delete(beer);
         
         Beer deletedBeer = repo.findOne(id);
		 
         Assert.assertNull(deletedBeer);
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