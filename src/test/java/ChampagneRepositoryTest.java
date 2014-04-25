package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Champagne;
import com.jonathan.barweb.respository.ChampagnerRepository;
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
public class ChampagneRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private ChampagneRepository repo;
    
    public ChampagneRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(ChampagneRepository.class);
         Champagne champagne = new Champagne(1).("Xpensive").build();
         repo.save(champagne);
		 id = champagne.getId();
		 Assert.assertNotNull(champagne);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readBeer()
	 {
         repo = ctx.getBean(ChampagneRepository.class);
         Champagne champagne = repo.findOne(id);
         Assert.assertEquals(Champagne.getName(), "Xpensive");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readChampagne")
     private void updateChampagne(){
         repo = ctx.getBean(ChampagneRepository.class);
         Champagne champagne = repo.findOne(id);
         Champagne updatedChampagne = new Champagne.Builder(1).name("TRG-Champagne").build();
         repo.save(updatedChampagne);
         
         Champagne champagne = repo.findOne(id);
         Assert.assertEquals(champagne.getName(), "TRG-Champagne"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updatechampagne")
     private void deletechampagne(){
         repo = ctx.getBean(champagneRepository.class);
         Champagne champagne = repo.findOne(id);
         repo.delete(champagne);
         
         Champagne deletedchampagne = repo.findOne(id);
		 
         Assert.assertNull(deletedchampagne);
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