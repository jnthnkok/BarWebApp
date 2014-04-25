package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Sandwitch;
import com.jonathan.barweb.respository.SandwitchRepository;
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
public class SandwitchRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private SandwitchRepository repo;
    
    public SandwitchRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(SandwitchRepository.class);
         Sandwitch sandwitch = new Sandwitch(1).("Ham and Cheese").build();
         repo.save(sandwitch);
		 id = sandwitch.getId();
		 Assert.assertNotNull(sandwitch);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readSandwitch()
	 {
         repo = ctx.getBean(SandwitchRepository.class);
         Sandwitch sandwitch = repo.findOne(id);
         Assert.assertEquals(Sandwitch.getName(), "Ham and Cheese");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readSandwitch")
     private void updateSandwitch(){
         repo = ctx.getBean(SandwitchRepository.class);
         Sandwitch sandwitch = repo.findOne(id);
         Sandwitch updatedSandwitch = new Sandwitch.Builder(1).name("Grilled Cheese").build();
         repo.save(updatedSandwitch);
         
         Sandwitch newSandwitch = repo.findOne(id);
         Assert.assertEquals(newSandwitch.getName(), "Grilled Cheese"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateSandwitch")
     private void deleteSandwitch(){
         repo = ctx.getBean(SandwitchRepository.class);
         Sandwitch sandwitch = repo.findOne(id);
         repo.delete(sandwitch);
         
         Sandwitch deletedSandwitch = repo.findOne(id);
		 
         Assert.assertNull(deletedSandwitch);
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