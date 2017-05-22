/**
 * 
 */
package org.ey.iot.smartparking.service;

import java.util.Arrays;
import java.util.List;

import org.ey.iot.smartparking.model.Company;
import org.ey.iot.smartparking.model.ParkingBay;
import org.ey.iot.smartparking.model.ParkingBayResult;
import org.ey.iot.smartparking.model.SensorOccupancyStatusResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
//import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;


/**
 * @author Kaushalendra.Singh
 *
 */
public class NedappSensitAPI {
	
	
	private final String NEDAPP_PARKING_API="https://metroinfrasys-mmrda-bkc.nedapparking.com/so.xml?Ln=adminbkc&Pw=jk3nn0a&Cm=8&Fm=A";
	
	public SensorOccupancyStatusResponse getSensorData() {
		ResponseEntity<SensorOccupancyStatusResponse> response = null;	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		RestTemplate temp = new RestTemplate();
		//temp.getMessageConverters().add(new SimpleXmlHttpMessageConverter());
		response = temp.exchange(NEDAPP_PARKING_API,HttpMethod.GET,httpEntity, SensorOccupancyStatusResponse.class);
		if(HttpStatus.OK == response.getStatusCode()){
			//System.out.println(response.getBody());
		}else{
			System.out.println("Error");
		}
		return response.getBody();
	}
	
	 
	public static void main(String[] str){

		NedappSensitAPI api = new NedappSensitAPI();
		SensorOccupancyStatusResponse resp = api.getSensorData();
		System.out.println(resp.getSensors().size());
		int i =0;
	}
}
