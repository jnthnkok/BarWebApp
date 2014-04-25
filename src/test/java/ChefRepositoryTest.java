package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Chef;
import com.jonathan.barweb.respository.ChefRepository;
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
public class ChefRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private ChefRepository repo;
    
    public ChefRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(ChefRepository.class);
         Chef chef = new Chef(1).("Tony").build();
         repo.save(chef);
		 id = chef.getId();
		 Assert.assertNotNull(chef);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readChef()
	 {
         repo = ctx.getBean(ChefRepository.class);
         Chef chef = repo.findOne(id);
         Assert.assertEquals(Chef.getName(), "Tony");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readChef")
     private void updateChef(){
         repo = ctx.getBean(ChefRepository.class);
         Chef chef = repo.findOne(id);
         Chef updatedChef = new Chef.Builder(1).name("Rick").build();
         repo.save(updatedChef);
         
         Chef newChef = repo.findOne(id);
         Assert.assertEquals(newChef.getName(), "Rick"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateChef")
     private void deleteChef(){
         repo = ctx.getBean(ChefRepository.class);
         Chef chef = repo.findOne(id);
         repo.delete(chef);
         
         Chef deletedChef = repo.findOne(id);
		 
         Assert.assertNull(deletedChef);
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