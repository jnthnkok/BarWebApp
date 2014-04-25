package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Food.VegetarianDish;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface VegetarianDishRepository extends JpaRepository<VegetarianDish, Long>{
    
}