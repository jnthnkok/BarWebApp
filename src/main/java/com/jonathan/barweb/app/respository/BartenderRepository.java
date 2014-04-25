package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Employee.Bartender;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface BartenderRepository extends JpaRepository<Bartender, Long>{
    
}