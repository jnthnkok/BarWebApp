package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Repairman;
import com.jonathan.barweb.respository.RepairmanRepository;
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
public class RepairmanRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private RepairmanRepository repo;
    
    public RepairmanRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(RepairmanRepository.class);
         Repairman repairman = new Repairman(1).("Paul").build();
         repo.save(repairman);
		 id = repairman.getId();
		 Assert.assertNotNull(repairman);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readRepairman()
	 {
         repo = ctx.getBean(RepairmanRepository.class);
         Repairman repairman = repo.findOne(id);
         Assert.assertEquals(Repairman.getName(), "Paul");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readRepairman")
     private void updateRepairman(){
         repo = ctx.getBean(RepairmanRepository.class);
         Repairman repairman = repo.findOne(id);
         Repairman updatedRepairman = new Repairman.Builder(1).name("Dev").build();
         repo.save(updatedRepairman);
         
         Repairman newRepairman = repo.findOne(id);
         Assert.assertEquals(newRepairman.getName(), "Dev"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateRepairman")
     private void deleteRepairman(){
         repo = ctx.getBean(RepairmanRepository.class);
         Repairman repairman = repo.findOne(id);
         repo.delete(repairman);
         
         Repairman deletedRepairman = repo.findOne(id);
		 
         Assert.assertNull(deletedRepairman);
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