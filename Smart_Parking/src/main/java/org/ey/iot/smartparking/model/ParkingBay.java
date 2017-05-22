/**
 * 
 */
package org.ey.iot.smartparking.model;

import java.util.Date;

import org.ey.iot.smartparking.service.ParkingBayMetadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Kaushalendra.Singh
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingBay {
	
	
	 Date createdDate;
     String createdBy;
     Boolean deleted;
     String token;
     String deviceHardwareId;
     String assignmentType;
     Long assetModuleId;
     Integer assetId;
     String assetName;
     String assetImageUrl;
     String siteToken;
     String status;
     Date activeDate;
     ParkingBayMetadata metadata;
     
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getDeviceHardwareId() {
		return deviceHardwareId;
	}
	public void setDeviceHardwareId(String deviceHardwareId) {
		this.deviceHardwareId = deviceHardwareId;
	}
	public String getAssignmentType() {
		return assignmentType;
	}
	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}
	public Long getAssetModuleId() {
		return assetModuleId;
	}
	public void setAssetModuleId(Long assetModuleId) {
		this.assetModuleId = assetModuleId;
	}
	public Integer getAssetId() {
		return assetId;
	}
	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetImageUrl() {
		return assetImageUrl;
	}
	public void setAssetImageUrl(String assetImageUrl) {
		this.assetImageUrl = assetImageUrl;
	}
	public String getSiteToken() {
		return siteToken;
	}
	public void setSiteToken(String siteToken) {
		this.siteToken = siteToken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}
	public ParkingBayMetadata getMetadata() {
		return metadata;
	}
	public void setMetadata(ParkingBayMetadata metadata) {
		this.metadata = metadata;
	}
	@Override
	public String toString() {
		return "ParkingBay [createdDate=" + createdDate + ", createdBy=" + createdBy + ", deleted=" + deleted
				+ ", token=" + token + ", deviceHardwareId=" + deviceHardwareId + ", assignmentType=" + assignmentType
				+ ", assetModuleId=" + assetModuleId + ", assetId=" + assetId + ", assetName=" + assetName
				+ ", assetImageUrl=" + assetImageUrl + ", siteToken=" + siteToken + ", status=" + status
				+ ", activeDate=" + activeDate + ", metadata=" + metadata + "]";
	}
     
     
}
