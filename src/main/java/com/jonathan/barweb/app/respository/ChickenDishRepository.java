package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Food.ChickenDish;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface ChickenDishRepository extends JpaRepository<ChickenDish, Long>{
    
}