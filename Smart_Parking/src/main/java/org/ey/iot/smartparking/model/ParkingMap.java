/**
 * 
 */
package org.ey.iot.smartparking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Kaushalendra.Singh
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingMap {
	
	String type;
	
	ParkingMapMetadata metadata;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ParkingMapMetadata getMetadata() {
		return metadata;
	}
	public void setMetadata(ParkingMapMetadata metadata) {
		this.metadata = metadata;
	}
	
}
