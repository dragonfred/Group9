package com.example.restaurantreviewapplication;
import java.io.Serializable;
import java.util.UUID;

/**
 * Because Java sucks, copy the following into your class:
 * 
private UUID uuid;
  
public UUID getUuid() {
	if(this.uuid==null) this.uuid = UUID.randomUUID();
	return this.uuid;
}
 * 
 * 
 * @author phil
 *
 */
interface Storable extends Serializable {
	
	public UUID getUuid();
	
}
