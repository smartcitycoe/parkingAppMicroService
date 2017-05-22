/**
 * 
 */
package org.ey.iot.smartparking.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kaushalendra.Singh
 *
 */

public class SensorOccupencyStatus {
	
	private String parkinglotid;
	
	private String zoneid;
	
	private String bayid;
	
	private String nodeid;
	
	private String state;
	
	@XmlAttribute(name="parkinglotid")
	public String getParkinglotid() {
		return parkinglotid;
	}

	public void setParkinglotid(String parkinglotid) {
		this.parkinglotid = parkinglotid;
	}
	@XmlAttribute(name="zoneid")
	public String getZoneid() {
		return zoneid;
	}

	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}
	@XmlAttribute(name="bayid")
	public String getBayid() {
		return bayid;
	}

	public void setBayid(String bayid) {
		this.bayid = bayid;
	}
	@XmlAttribute(name="nodeid")
	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	@XmlAttribute(name="state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bayid == null) ? 0 : bayid.hashCode());
		result = prime * result + ((nodeid == null) ? 0 : nodeid.hashCode());
		result = prime * result + ((parkinglotid == null) ? 0 : parkinglotid.hashCode());
		result = prime * result + ((zoneid == null) ? 0 : zoneid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorOccupencyStatus other = (SensorOccupencyStatus) obj;
		if (bayid == null) {
			if (other.bayid != null)
				return false;
		} else if (!bayid.equals(other.bayid))
			return false;
		if (nodeid == null) {
			if (other.nodeid != null)
				return false;
		} else if (!nodeid.equals(other.nodeid))
			return false;
		if (parkinglotid == null) {
			if (other.parkinglotid != null)
				return false;
		} else if (!parkinglotid.equals(other.parkinglotid))
			return false;
		if (zoneid == null) {
			if (other.zoneid != null)
				return false;
		} else if (!zoneid.equals(other.zoneid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SensorOccupencyStatus [parkinglotid=" + parkinglotid + ", zoneid=" + zoneid + ", bayid=" + bayid
				+ ", nodeid=" + nodeid + ", state=" + state + "]";
	}
	
	
	
}
