package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Employee.Manager;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface ManagerRepository extends JpaRepository<Manager, Long>{
    
}