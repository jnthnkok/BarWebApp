package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Supplier.FoodSupplier;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface FoodSupplierRepository extends JpaRepository<FoodSupplier, Long>{
    
}