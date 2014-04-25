package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Bartender;
import com.jonathan.barweb.respository.BartenderRepository;
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
public class BartenderRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private BartenderRepository repo;
    
    public BartenderRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(BartenderRepository.class);
         Bartender bartender = new Bartender(1).("Adam").build();
         repo.save(bartender);
		 id = bartender.getId();
		 Assert.assertNotNull(bartender);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readBartender()
	 {
         repo = ctx.getBean(BartenderRepository.class);
         Bartender bartender = repo.findOne(id);
         Assert.assertEquals(Bartender.getName(), "Adam");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readBartender")
     private void updateBartender(){
         repo = ctx.getBean(BartenderRepository.class);
         Bartender bartender = repo.findOne(id);
         Bartender updatedBartender = new Bartender.Builder(1).name("Blake").build();
         repo.save(updatedBartender);
         
         Bartender newBartender = repo.findOne(id);
         Assert.assertEquals(newBartender.getName(), "Blake"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateBartender")
     private void deleteBartender(){
         repo = ctx.getBean(BartenderRepository.class);
         Bartender bartender = repo.findOne(id);
         repo.delete(bartender);
         
         Bartender deletedBartender = repo.findOne(id);
		 
         Assert.assertNull(deletedBartender);
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