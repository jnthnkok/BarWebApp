package com.jonathan.barweb.app.respository;

import com.jonathan.barweb.app.domain.Supplier.JukeboxMusicSupplier;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jonathan
 */
public interface JukeboxMusicSupplierRepository extends JpaRepository<JukeboxMusicSupplier, Long>{
    
}