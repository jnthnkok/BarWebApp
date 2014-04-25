package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Supplier.AlcoholSupplier;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface AlchoholSupplierRepository extends JpaRepository<AlcoholSupplier, Long>{
    
}