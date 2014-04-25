package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Drinks.Whiskey;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface WhiskeyRepository extends JpaRepository<Whiskey, Long>{
    
}