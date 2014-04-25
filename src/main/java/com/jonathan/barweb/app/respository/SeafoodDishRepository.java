package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Food.SeafoodDish;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface SeafoodDishRepository extends JpaRepository<SeafoodDish, Long>{
    
}