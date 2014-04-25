package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Food.BeefDish;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface BeefDishRepository extends JpaRepository<BeefDish, Long>{
    
}