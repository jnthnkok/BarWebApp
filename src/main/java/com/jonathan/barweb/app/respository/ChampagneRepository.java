package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Drinks.Champagne;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface ChampagneRepository extends JpaRepository<Champagne, Long>{
    
}