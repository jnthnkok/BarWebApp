
package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Employee.Janitor;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface JanitorRepository extends JpaRepository<Janitor, Long>{
    
}