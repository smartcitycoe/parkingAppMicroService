package org.ey.iot.smartparking.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.ey.iot.smartparking.model.BookingResponse;
import org.ey.iot.smartparking.model.ParkingBay;
import org.ey.iot.smartparking.model.ParkingBayResult;
import org.ey.iot.smartparking.model.ParkingLot;
import org.ey.iot.smartparking.model.ParkingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.FreeBusyRequestItem;

/**
 * @author Kaushalendra.Singh
 *
 */
@Service
public class SitewhereAPI {
	static Logger LOGGER = LoggerFactory.getLogger(SitewhereAPI.class);
	
	/**
	 * @return
	 */
	
	@Autowired
	GoogleCalendarAPI calendarAPI;
	
	public static final String SITEWHERE_PARKING_LOT_API = "http://35.154.110.137:8080/sitewhere/api/sites?includeZones=false&page=1&pageSize=100";
	
	
	
	public HttpEntity<String> getSitewhereHeader() {
		String plainCreds = "springmicroservice:spring456";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("X-Sitewhere-Tenant", "ajar123");
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		return httpEntity;
	}
	
	/**
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 * @throws ParseException 
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 */
	public ResponseEntity<ParkingResult> getParkingLot(double latitude, double longitude, Integer radius, String startTime,String endTime) throws ParseException, IOException, GeneralSecurityException {
		List<ParkingLot> result;
		HttpEntity<String> httpEntity = getSitewhereHeader();
		RestTemplate template = new RestTemplate();
		Calendar cal = Calendar.getInstance();
		LOGGER.debug("Start Time: "+cal.getTime());
		ResponseEntity<ParkingResult> response = template.exchange(SITEWHERE_PARKING_LOT_API,HttpMethod.GET,httpEntity, ParkingResult.class);
		if(HttpStatus.OK == response.getStatusCode()){
			LOGGER.debug(response.getBody().toString());
		}else{
			LOGGER.debug("Error");
		}
		Calendar cal1 = Calendar.getInstance();
		LOGGER.debug("End Time: "+cal1.getTime());
		LOGGER.debug("Total Time: "+(cal1.getTimeInMillis() - cal.getTimeInMillis()));
		
		int count = response.getBody().getNumResults();
		LOGGER.debug("Total Result: " + count);
		result = response.getBody().getResults();
		List<ParkingLot> list = ParkingSearch.getMatchingParkingLot(result, latitude, longitude, radius, "K", startTime, endTime, this, calendarAPI);
		response.getBody().setResults(list);
		response.getBody().setNumResults(list.size());
		return response;
	} 

	
	/**
	 * 
	 * @param parkingLotToken
	 * @return
	 */
	public List<ParkingBay> getParkingBays(String parkingLotToken) {
		ResponseEntity<ParkingBayResult> response = null;
		HttpEntity<String> httpEntity = getSitewhereHeader();
		RestTemplate temp = new RestTemplate();
		response = temp.exchange("http://35.154.110.137:8080/sitewhere/api/sites/"+parkingLotToken+"/assignments?includeDevice=false&includeAsset=false&includeSite=false&page=1&pageSize=100",HttpMethod.GET,httpEntity, ParkingBayResult.class);
		if(HttpStatus.OK == response.getStatusCode()){
			//System.out.println(response.getBody());
		}else{
			System.out.println("Error");
		}
		return response.getBody().getResults();
	}
	
	/**
	 * 
	 * @param parkingLotToken
	 * @return
	 */
	public ParkingLot getParkingLot(String parkingLotToken) {
		ResponseEntity<ParkingLot> response = null;
		HttpEntity<String> httpEntity = getSitewhereHeader();
		RestTemplate temp = new RestTemplate();
		response = temp.exchange("http://35.154.110.137:8080/sitewhere/api/sites/8811cd57-e92e-44af-86a7-dd6bdd09de56",HttpMethod.GET,httpEntity, ParkingLot.class);
		if(HttpStatus.OK == response.getStatusCode()){
			//System.out.println(response.getBody());
		}else{
			System.out.println("Error");
		}
		return response.getBody();
	}

	/**
	 * @param parkingLotToken
	 * @param email
	 * @param startTime
	 * @param duration
	 * @param durationUnit
	 * @return
	 * @throws ParseException 
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 */
	public BookingResponse bookParkingBay(String parkingLotToken, String email, String startTime,
			String endTime) throws ParseException, IOException, GeneralSecurityException {
		// TODO Auto-generated method stub
		BookingResponse response = null; 
		List<ParkingBay> bayList = getParkingBays(parkingLotToken);
		Event event = null;
		Map<FreeBusyRequestItem, ParkingBay> itemMap = new HashMap<FreeBusyRequestItem, ParkingBay>();
		for(ParkingBay bay:bayList){						
			FreeBusyRequestItem item = new FreeBusyRequestItem();
			item.setId(bay.getMetadata().getCalendarID());
			itemMap.put(item, bay);
		}
		DateTime start = DateUtil.convertDateStrToDateTime(startTime);
		DateTime end = DateUtil.convertDateStrToDateTime(endTime);
		List<String> freeCalendars = calendarAPI.getFreebusy(start, end, new ArrayList(itemMap.keySet()));
		for(String calId:freeCalendars){
			event = calendarAPI.createEvent(calId, start, end, email);
			if(event!=null && event.getId()!=null){
				ParkingBay bay = itemMap.get((new FreeBusyRequestItem()).setId(calId));
				ParkingLot parkingLot =  getParkingLot(bay.getToken());
				response = new BookingResponse();
				response.setCalendarId(calId);
				response.setEventId(event.getId());
				response.setEmail(email);
				response.setAssignmentToken(bay.getToken());
				response.setSiteToken(bay.getSiteToken());
				response.setAssetName(bay.getAssetName());
				response.setParkingLotName(parkingLot.getName());
				break;
			}
		}
		return response;
	}

}
