/**
 * 
 */
package org.ey.iot.smartparking.model;

import java.util.Date;

/**
 * @author Kaushalendra.Singh
 *
 */
public class MQTTResponse {
	
	@Override
	public String toString() {
		return "MQTTResponse [hardwareId=" + hardwareId + ", type=" + type + ", request=" + request + "]";
	}
	private String hardwareId;
    
	private String type;
    
	private Request request;
    
	/*public enum AvailabilityStatus {OCCUPIED{public String toString() {
        return "occupied";
    }}, FREE{public String toString() {
        return "free";
    }},UNKNOWN{public String toString() {
        return "Unknown";
    }}};*/
    
    //public enum AvailabilityStatus {OCCUPIED, FREE};
	
	
	public String getHardwareId() {
		return hardwareId;
	}
	public void setHardwareId(String hardwareId) {
		this.hardwareId = hardwareId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public Request createRequest() {
		
		return request==null?request =new Request():request;
	}
	public class Request{
		
		String type;
	    
		String level;
	    
		String message;
	    
		Boolean updateState;
	    
		String eventDate;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Boolean getUpdateState() {
			return updateState;
		}
		public void setUpdateState(Boolean updateState) {
			this.updateState = updateState;
		}
		public String getEventDate() {
			return eventDate;
		}
		public void setEventDate(String eventDate) {
			this.eventDate = eventDate;
		}
		@Override
		public String toString() {
			return "Request [type=" + type + ", level=" + level + ", message=" + message + ", updateState=" + updateState
					+ ", eventDate=" + eventDate + "]";
		}
	    
	    
	}
}
    

