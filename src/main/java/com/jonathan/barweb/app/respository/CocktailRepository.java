package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Drinks.Cocktail;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface CocktailRepository extends JpaRepository<Cocktail, Long>{
    
}