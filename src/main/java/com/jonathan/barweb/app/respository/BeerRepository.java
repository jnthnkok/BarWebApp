package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Drinks.Beer;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface BeerRepository extends JpaRepository<Beer, Long>{
    
}