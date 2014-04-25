package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Food.BreakfastDish;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface BreakfastDishRepository extends JpaRepository<BreakfastDish, Long>{
    
}