package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Manager;
import com.jonathan.barweb.respository.ManagerRepository;
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
public class ManagerRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private ManagerRepository repo;
    
    public ManagerRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(ManagerRepository.class);
         Manager manager = new Manager(1).("Tina").build();
         repo.save(manager);
		 id = manager.getId();
		 Assert.assertNotNull(manager);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readManager()
	 {
         repo = ctx.getBean(ManagerRepository.class);
         Manager manager = repo.findOne(id);
         Assert.assertEquals(Manager.getName(), "Tina");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readManager")
     private void updateManager(){
         repo = ctx.getBean(ManagerRepository.class);
         Manager manager = repo.findOne(id);
         Manager updatedManager = new Manager.Builder(1).name("Mary").build();
         repo.save(updatedManager);
         
         Manager newManager = repo.findOne(id);
         Assert.assertEquals(newManager.getName(), "Mary"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateManager")
     private void deleteManager(){
         repo = ctx.getBean(ManagerRepository.class);
         Manager manager = repo.findOne(id);
         repo.delete(manager);
         
         Manager deletedManager = repo.findOne(id);
		 
         Assert.assertNull(deletedManager);
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