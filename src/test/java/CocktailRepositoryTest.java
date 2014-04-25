package com.jonathan.barweb.test.respository;

import com.jonathan.barweb.app.conf.ConnectionConfig;
import com.jonathan.barweb.domain.Cocktail;
import com.jonathan.barweb.respository.CocktailRepository;
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
public class CocktailRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;

    private CocktailRepository repo;
    
    public CocktailRepositoryTest() {
    }

	//Insert/Create Test
     @Test
     public void insertRecord() {
         repo = ctx.getBean(CocktailRepository.class);
         Cocktail cocktail = new Cocktail(1).("Porto Flip").build();
         repo.save(cocktail);
		 id = cocktail.getId();
		 Assert.assertNotNull(cocktail);
     }
	 
	 //Read Test
	 @Test(dependsOnMethods = "insertRecord")
     public void readCocktail()
	 {
         repo = ctx.getBean(CocktailRepository.class);
         Cocktail cocktail = repo.findOne(id);
         Assert.assertEquals(Cocktail.getName(), "Porto Flip");
     }
	 
	 //Update Test
	 @Test(dependsOnMethods = "readCocktail")
     private void updateCocktail(){
         repo = ctx.getBean(CocktailRepository.class);
         Cocktail cocktail = repo.findOne(id);
         Cocktail updatedCocktail = new Cocktail.Builder(1).name("Zombie").build();
         repo.save(updatedCocktail);
         
         Cocktail newCocktail = repo.findOne(id);
         Assert.assertEquals(newCocktail.getName(), "Zombie"); 
     }
	 
	 //Delete Test
	 @Test(dependsOnMethods = "updateCocktail")
     private void deleteCocktail(){
         repo = ctx.getBean(CocktailRepository.class);
         Cocktail cocktail = repo.findOne(id);
         repo.delete(cocktail);
         
         Cocktail deletedCocktail = repo.findOne(id);
		 
         Assert.assertNull(deletedCocktail);
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