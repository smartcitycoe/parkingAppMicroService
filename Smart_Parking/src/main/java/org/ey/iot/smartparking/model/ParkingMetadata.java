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
public class ParkingMetadata {
	String owner;
	String acceptedPaymentMethods;
	String areaServed;
	Float maximumAllowedWidth;
	String chargeType;
	Integer ovrestayRatePerCycle;
	String type;
	String layout;
	Integer normalRatePerCycle;
	Integer billingCyle;
    String allowedVehicleType;
    Integer availableSpotNumber;
    String provider;
    Integer reviewCount;
    String requiredPermit;
    Integer ratingValue;
    Integer maximumParkingDuration;
    Integer maximumAllowedHeight;
    String category;
    Integer totalSpotNumber;
    String occupancyDetectionType;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAcceptedPaymentMethods() {
		return acceptedPaymentMethods;
	}
	public void setAcceptedPaymentMethods(String acceptedPaymentMethods) {
		this.acceptedPaymentMethods = acceptedPaymentMethods;
	}
	public String getAreaServed() {
		return areaServed;
	}
	public void setAreaServed(String areaServed) {
		this.areaServed = areaServed;
	}
	public Float getMaximumAllowedWidth() {
		return maximumAllowedWidth;
	}
	public void setMaximumAllowedWidth(Float maximumAllowedWidth) {
		this.maximumAllowedWidth = maximumAllowedWidth;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public Integer getOvrestayRatePerCycle() {
		return ovrestayRatePerCycle;
	}
	public void setOvrestayRatePerCycle(Integer ovrestayRatePerCycle) {
		this.ovrestayRatePerCycle = ovrestayRatePerCycle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public Integer getNormalRatePerCycle() {
		return normalRatePerCycle;
	}
	public void setNormalRatePerCycle(Integer normalRatePerCycle) {
		this.normalRatePerCycle = normalRatePerCycle;
	}
	public Integer getBillingCyle() {
		return billingCyle;
	}
	public void setBillingCyle(Integer billingCyle) {
		this.billingCyle = billingCyle;
	}
	public String getAllowedVehicleType() {
		return allowedVehicleType;
	}
	public void setAllowedVehicleType(String allowedVehicleType) {
		this.allowedVehicleType = allowedVehicleType;
	}
	public Integer getAvailableSpotNumber() {
		return availableSpotNumber;
	}
	public void setAvailableSpotNumber(Integer availableSpotNumber) {
		this.availableSpotNumber = availableSpotNumber;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public Integer getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}
	public String getRequiredPermit() {
		return requiredPermit;
	}
	public void setRequiredPermit(String requiredPermit) {
		this.requiredPermit = requiredPermit;
	}
	public Integer getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(Integer ratingValue) {
		this.ratingValue = ratingValue;
	}
	public Integer getMaximumParkingDuration() {
		return maximumParkingDuration;
	}
	public void setMaximumParkingDuration(Integer maximumParkingDuration) {
		this.maximumParkingDuration = maximumParkingDuration;
	}
	public Integer getMaximumAllowedHeight() {
		return maximumAllowedHeight;
	}
	public void setMaximumAllowedHeight(Integer maximumAllowedHeight) {
		this.maximumAllowedHeight = maximumAllowedHeight;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getOccupancyDetectionType() {
		return occupancyDetectionType;
	}
	public void setOccupancyDetectionType(String occupancyDetectionType) {
		this.occupancyDetectionType = occupancyDetectionType;
	}
	public Integer getTotalSpotNumber() {
		return totalSpotNumber;
	}
	public void setTotalSpotNumber(Integer totalSpotNumber) {
		this.totalSpotNumber = totalSpotNumber;
	}
    
    
    
}
