package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Waiter;
import com.jonathan.barweb.respository.WaiterRepository;
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
public class WaiterRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private WaiterRepository repo;
    
    public WaiterRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(WaiterRepository.class);
         Waiter waiter = new Waiter(1).("Sara").build();
         repo.save(waiter);
		 id = waiter.getId();
		 Assert.assertNotNull(waiter);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readWaiter()
	 {
         repo = ctx.getBean(WaiterRepository.class);
         Waiter waiter = repo.findOne(id);
         Assert.assertEquals(Waiter.getName(), "Sara");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readWaiter")
     private void updateWaiter(){
         repo = ctx.getBean(WaiterRepository.class);
         Waiter waiter = repo.findOne(id);
         Waiter updatedWaiter = new Waiter.Builder(1).name("Laurel").build();
         repo.save(updatedWaiter);
         
         Waiter newWaiter = repo.findOne(id);
         Assert.assertEquals(newWaiter.getName(), "Laurel"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateWaiter")
     private void deleteWaiter(){
         repo = ctx.getBean(WaiterRepository.class);
         Waiter waiter = repo.findOne(id);
         repo.delete(waiter);
         
         Waiter deletedWaiter = repo.findOne(id);
		 
         Assert.assertNull(deletedWaiter);
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