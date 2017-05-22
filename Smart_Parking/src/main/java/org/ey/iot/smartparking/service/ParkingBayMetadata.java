/**
 * 
 */
package org.ey.iot.smartparking.service;

/**
 * @author Kaushalendra.Singh
 *
 */
public class ParkingBayMetadata {
	
	String calendarID;

	public String getCalendarID() {
		return calendarID;
	}

	public void setCalendarID(String calendarID) {
		this.calendarID = calendarID;
	}

	@Override
	public String toString() {
		return "ParkingBayMetadata [calendarID=" + calendarID + "]";
	}
	
	
}
