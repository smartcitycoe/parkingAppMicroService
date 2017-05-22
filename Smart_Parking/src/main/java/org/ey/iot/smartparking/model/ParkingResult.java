/**
 * 
 */
package org.ey.iot.smartparking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Kaushalendra.Singh
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingResult {
	
	Integer numResults;
	
	List<ParkingLot> results;

	public Integer getNumResults() {
		return numResults;
	}

	public void setNumResults(Integer numResults) {
		this.numResults = numResults;
	}

	public List<ParkingLot> getResults() {
		return results;
	}

	public void setResults(List<ParkingLot> results) {
		this.results = results;
	}

	
}
