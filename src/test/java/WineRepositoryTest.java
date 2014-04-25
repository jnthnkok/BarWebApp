package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Wine;
import com.jonathan.barweb.respository.WineRepository;
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
public class WineRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private WineRepository repo;
    
    public WineRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(WineRepository.class);
         Wine wine = new Wine(1).("Pastiche").build();
         repo.save(wine);
		 id = wine.getId();
		 Assert.assertNotNull(wine);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readWine()
	 {
         repo = ctx.getBean(WineRepository.class);
         Wine wine = repo.findOne(id);
         Assert.assertEquals(Wine.getName(), "Pastiche");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readWine")
     private void updateWine(){
         repo = ctx.getBean(WineRepository.class);
         Wine wine = repo.findOne(id);
         Wine updatedWine = new Wine.Builder(1).name("Beringer").build();
         repo.save(updatedWine);
         
         Wine newWine = repo.findOne(id);
         Assert.assertEquals(newWine.getName(), "Beringer"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateWine")
     private void deleteWine(){
         repo = ctx.getBean(WineRepository.class);
         Wine wine = repo.findOne(id);
         repo.delete(wine);
         
         Wine deletedWine = repo.findOne(id);
		 
         Assert.assertNull(deletedWine);
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