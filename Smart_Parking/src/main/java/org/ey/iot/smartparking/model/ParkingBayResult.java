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
public class ParkingBayResult {
	
	Integer numResults;
	
	List<ParkingBay> results;

	public Integer getNumResults() {
		return numResults;
	}

	public void setNumResults(Integer numResults) {
		this.numResults = numResults;
	}

	public List<ParkingBay> getResults() {
		return results;
	}

	public void setResults(List<ParkingBay> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "ParkingBayResult [numResults=" + numResults + ", results=" + results + ", getNumResults()="
				+ getNumResults() + ", getResults()=" + getResults() + "]";
	}	
	
}
