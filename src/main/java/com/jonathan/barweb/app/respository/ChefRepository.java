package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Employee.Chef;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface ChefRepository extends JpaRepository<Chef, Long>{
    
}