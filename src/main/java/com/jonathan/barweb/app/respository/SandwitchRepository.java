package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Food.Sandwitch;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface SandwitchRepository extends JpaRepository<Sandwitch, Long>{
    
}