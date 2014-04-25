/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.barweb.app.domain.Supplier;
import java.util.Locale.Builder;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author jonathan
 */
@Entity
public class SoftDrinksSupplier implements Supplier, Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String name;

    public SoftDrinksSupplier() {
    }
    
  
    
    private SoftDrinksSupplier (Builder builder)
    {
        this.id = builder.id;
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }
    
    public static class Builder
    {
        private Long id;
        private String name;
        
        public Builder(Long idValue)
        {
            id = idValue;
        }
        
        public Builder name(String nm)
        {
            name = nm;
            return(this);
        }
                
        public SoftDrinksSupplier  build()
        {
            return new SoftDrinksSupplier (this);
        }
    }
    //end Builder
    
    @Override
    public Long getID() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SoftDrinksSupplier other = (SoftDrinksSupplier) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 23 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
	
	@Override
    public String toString()
	{
        return "com.jonathan.barweb.app.domain.Supplier.SoftDrinksSupplier[ id=" + id + " ]";
    }

}
