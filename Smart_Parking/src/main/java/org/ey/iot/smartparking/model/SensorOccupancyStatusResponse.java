/**
 * 
 */
package org.ey.iot.smartparking.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kaushalendra.Singh
 *
 */
@XmlRootElement(name="baystates")
@XmlAccessorType(XmlAccessType.FIELD)
public class SensorOccupancyStatusResponse {
	@XmlElement(name="baystate")
	List<SensorOccupencyStatus> sensors = new ArrayList<SensorOccupencyStatus>();

	public List<SensorOccupencyStatus> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorOccupencyStatus> sensors) {
		this.sensors = sensors;
	}

	@Override
	public String toString() {
		return "SensorOccupancyStatusResponse [getSensors()=" + getSensors() + "]";
	}
	

}
