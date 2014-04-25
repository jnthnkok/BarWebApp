
package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Employee.Waiter;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface WaiterRepository extends JpaRepository<Waiter, Long>{
    
}