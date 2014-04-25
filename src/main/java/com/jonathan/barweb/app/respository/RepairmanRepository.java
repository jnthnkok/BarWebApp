package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Employee.Repairman;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface RepairmanRepository extends JpaRepository<Repairman, Long>{
    
}