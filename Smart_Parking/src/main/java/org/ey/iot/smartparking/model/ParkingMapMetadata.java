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
public class ParkingMapMetadata {
	
	Integer zoomLevel;
	
	Double centerLatitude;
	
	Double centerLongitude;

	public Integer getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(Integer zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public Double getCenterLatitude() {
		return centerLatitude;
	}

	public void setCenterLatitude(Double centerLatitude) {
		this.centerLatitude = centerLatitude;
	}

	public Double getCenterLongitude() {
		return centerLongitude;
	}

	public void setCenterLongitude(Double centerLongitude) {
		this.centerLongitude = centerLongitude;
	}
	
	
	
}
