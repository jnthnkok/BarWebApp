package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Employee.DishWasher;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface DishWasherRepository extends JpaRepository<DishWasher, Long>{
    
}