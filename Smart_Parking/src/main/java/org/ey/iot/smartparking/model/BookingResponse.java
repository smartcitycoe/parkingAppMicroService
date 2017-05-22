/**
 * 
 */
package org.ey.iot.smartparking.model;

/**
 * @author Kaushalendra.Singh
 *
 */
public class BookingResponse {
	
	String calendarId;
	String eventId;
	String email;
	String siteToken;
	String assignmentToken;
	String assetName;
	String parkingLotName;
	
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSiteToken() {
		return siteToken;
	}
	public void setSiteToken(String siteToken) {
		this.siteToken = siteToken;
	}
	public String getAssignmentToken() {
		return assignmentToken;
	}
	public void setAssignmentToken(String assignmentToken) {
		this.assignmentToken = assignmentToken;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getParkingLotName() {
		return parkingLotName;
	}
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	
}
