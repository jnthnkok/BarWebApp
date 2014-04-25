package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Whiskey;
import com.jonathan.barweb.respository.WhiskeyRepository;
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
public class WhiskeyRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private WhiskeyRepository repo;
    
    public WhiskeyRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(WhiskeyRepository.class);
         Whiskey whiskey = new Whiskey(1).("Bourbon Lancer").build();
         repo.save(whiskey);
		 id = whiskey.getId();
		 Assert.assertNotNull(whiskey);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readWhiskey()
	 {
         repo = ctx.getBean(WhiskeyRepository.class);
         Whiskey whiskey = repo.findOne(id);
         Assert.assertEquals(Whiskey.getName(), "Bourbon Lancer");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readWhiskey")
     private void updateWhiskey(){
         repo = ctx.getBean(WhiskeyRepository.class);
         Whiskey whiskey = repo.findOne(id);
         Whiskey updatedWhiskey = new Whiskey.Builder(1).name("Blue Blazer").build();
         repo.save(updatedWhiskey);
         
         Whiskey newWhiskey = repo.findOne(id);
         Assert.assertEquals(newWhiskey.getName(), "Blue Blazer"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateWhiskey")
     private void deleteWhiskey(){
         repo = ctx.getBean(WhiskeyRepository.class);
         Whiskey whiskey = repo.findOne(id);
         repo.delete(whiskey);
         
         Whiskey deletedWhiskey = repo.findOne(id);
		 
         Assert.assertNull(deletedWhiskey);
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