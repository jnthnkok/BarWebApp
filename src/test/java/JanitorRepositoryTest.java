package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Janitor;
import com.jonathan.barweb.respository.JanitorRepository;
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
public class JanitorRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private JanitorRepository repo;
    
    public JanitorRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(JanitorRepository.class);
         Janitor janitor = new Janitor(1).("Edward").build();
         repo.save(janitor);
		 id = janitor.getId();
		 Assert.assertNotNull(janitor);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readJanitor()
	 {
         repo = ctx.getBean(JanitorRepository.class);
         Janitor janitor = repo.findOne(id);
         Assert.assertEquals(Janitor.getName(), "Edward");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readJanitor")
     private void updateJanitor(){
         repo = ctx.getBean(JanitorRepository.class);
         Janitor janitor = repo.findOne(id);
         Janitor updatedJanitor = new Janitor.Builder(1).name("Nick").build();
         repo.save(updatedJanitor);
         
         Janitor newJanitor = repo.findOne(id);
         Assert.assertEquals(newJanitor.getName(), "Nick"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateJanitor")
     private void deleteJanitor(){
         repo = ctx.getBean(JanitorRepository.class);
         Janitor janitor = repo.findOne(id);
         repo.delete(janitor);
         
         Janitor deletedJanitor = repo.findOne(id);
		 
         Assert.assertNull(deletedJanitor);
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