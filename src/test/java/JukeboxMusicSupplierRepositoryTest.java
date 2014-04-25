package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.JukeboxMusicSupplier;
import com.jonathan.barweb.respository.JukeboxMusicSupplierRepository;
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
public class JukeboxMusicSupplierRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private JukeboxMusicSupplierRepository repo;
    
    public JukeboxMusicSupplierRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(JukeboxMusicSupplierRepository.class);
         JukeboxMusicSupplier jukeboxMusicSupplier = new JukeboxMusicSupplier(1).("Great Tunes!").build();
         repo.save(jukeboxMusicSupplier);
		 id = jukeboxMusicSupplier.getId();
		 Assert.assertNotNull(jukeboxMusicSupplier);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readJukeboxMusicSupplier()
	 {
         repo = ctx.getBean(JukeboxMusicSupplierRepository.class);
         JukeboxMusicSupplier jukeboxMusicSupplier = repo.findOne(id);
         Assert.assertEquals(JukeboxMusicSupplier.getName(), "Great Tunes!");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readJukeboxMusicSupplier")
     private void updateJukeboxMusicSupplier(){
         repo = ctx.getBean(JukeboxMusicSupplierRepository.class);
         JukeboxMusicSupplier jukeboxMusicSupplier = repo.findOne(id);
         JukeboxMusicSupplier updatedJukeboxMusicSupplier = new JukeboxMusicSupplier.Builder(1).name("Great Tunes! Ltd.").build();
         repo.save(updatedJukeboxMusicSupplier);
         
         JukeboxMusicSupplier newJukeboxMusicSupplier = repo.findOne(id);
         Assert.assertEquals(newJukeboxMusicSupplier.getName(), "Great Tunes! Ltd."); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateJukeboxMusicSupplier")
     private void deleteJukeboxMusicSupplier(){
         repo = ctx.getBean(JukeboxMusicSupplierRepository.class);
         JukeboxMusicSupplier jukeboxMusicSupplier = repo.findOne(id);
         repo.delete(jukeboxMusicSupplier);
         
         JukeboxMusicSupplier deletedJukeboxMusicSupplier = repo.findOne(id);
		 
         Assert.assertNull(deletedJukeboxMusicSupplier);
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