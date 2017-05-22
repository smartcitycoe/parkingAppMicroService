/**
 * 
 */
package org.ey.iot.smartparking.model;

/**
 * @author Kaushalendra.Singh
 *
 */
public class ParkingLotForMobile {
	
	String  name;
	String	description;
	String	imageUrl;
	Integer	billingCyle;
	Integer	normalRatePerCycle;
	Integer	overstayRatePerCycle;
	String	acceptedPaymentMethods;
	Integer	totalSpotNumber;
	String  type;
	Integer	availableSpotNumber;
	Integer	maximumParkingDuration;
	Double centerLatitude;
	Double centerLongitude;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getBillingCyle() {
		return billingCyle;
	}
	public void setBillingCyle(Integer billingCyle) {
		this.billingCyle = billingCyle;
	}
	public Integer getNormalRatePerCycle() {
		return normalRatePerCycle;
	}
	public void setNormalRatePerCycle(Integer normalRatePerCycle) {
		this.normalRatePerCycle = normalRatePerCycle;
	}
	public Integer getOverstayRatePerCycle() {
		return overstayRatePerCycle;
	}
	public void setOverstayRatePerCycle(Integer overstayRatePerCycle) {
		this.overstayRatePerCycle = overstayRatePerCycle;
	}
	public String getAcceptedPaymentMethods() {
		return acceptedPaymentMethods;
	}
	public void setAcceptedPaymentMethods(String acceptedPaymentMethods) {
		this.acceptedPaymentMethods = acceptedPaymentMethods;
	}
	public Integer getTotalSpotNumber() {
		return totalSpotNumber;
	}
	public void setTotalSpotNumber(Integer totalSpotNumber) {
		this.totalSpotNumber = totalSpotNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getAvailableSpotNumber() {
		return availableSpotNumber;
	}
	public void setAvailableSpotNumber(Integer availableSpotNumber) {
		this.availableSpotNumber = availableSpotNumber;
	}
	public Integer getMaximumParkingDuration() {
		return maximumParkingDuration;
	}
	public void setMaximumParkingDuration(Integer maximumParkingDuration) {
		this.maximumParkingDuration = maximumParkingDuration;
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
