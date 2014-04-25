package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Drinks.SoftDrink;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface SoftDrinkRepository extends JpaRepository<SoftDrink, Long>{
    
}