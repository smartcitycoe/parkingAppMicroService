package org.ey.iot.smartparking.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.ey.iot.smartparking.model.BookingResponse;
import org.ey.iot.smartparking.model.Company;
import org.ey.iot.smartparking.model.ParkingResult;
import org.ey.iot.smartparking.service.GenerateQRCode;
import org.ey.iot.smartparking.service.GoogleCalendarAPI;
import org.ey.iot.smartparking.service.SitewhereAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.zxing.WriterException;


@RestController
@RequestMapping("/SmartParking")
public class BookingController {
	
	static Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	SitewhereAPI sitewhereAPI;
	
	@Autowired
	GoogleCalendarAPI calendarAPI;
	
		
	@RequestMapping("/searchParkingLots")
	public ParkingResult searchParkingLots(double latitude, double longitude, String startTime, String endTime, Integer radius) throws ParseException, IOException, GeneralSecurityException {
		Calendar cal = Calendar.getInstance();
		LOGGER.debug("Search API Call Started On " + cal.getTime());
		ResponseEntity<ParkingResult> response = sitewhereAPI.getParkingLot(latitude, longitude, radius, startTime, endTime);
		Calendar cal1 = Calendar.getInstance();
		LOGGER.debug("Main End Time: "+cal1.getTime());
		LOGGER.debug("Main Total Time: "+(cal1.getTimeInMillis() - cal.getTimeInMillis()));
		return response.getBody();
		
	}
	
	
	@RequestMapping("/bookParkingBay")
	public BookingResponse bookParkingBay(String parkingLotToken, String email, String startTime,  String endTime) throws ParseException, IOException, GeneralSecurityException {
		LOGGER.debug("Booking API Called ");
		BookingResponse response = sitewhereAPI.bookParkingBay(parkingLotToken,email, startTime, endTime);
		LOGGER.debug("Booking API Call Ended  ");
		return response;		
	}
	
	@RequestMapping(value="/download")
	public void getDownload(HttpServletResponse response, String qrCodeString) throws IOException, WriterException {

		// Get your file stream from wherever.
		//String qrCodeStr = calendarId+"|"+eventId+"|"+email;
		InputStream myStream = GenerateQRCode.createQRImageInputStream(qrCodeString, 200, "png");
		BookingResponse booking = getBookingDetails(qrCodeString);
		// Set the content type and attachment header.
		response.addHeader("Content-disposition", "attachment;filename="+booking.getCalendarId()+"_"+booking.getEventId()+".png");
		response.setContentType("image/png");

		// Copy the stream to the response's output stream.
		IOUtils.copy(myStream, response.getOutputStream());
		response.flushBuffer();
	}


	/**
	 * @param qrCodeString
	 * @throws IOException
	 * @throws JsonParseException
	 */
	private BookingResponse getBookingDetails(String qrCodeString) throws IOException, JsonParseException {
		BookingResponse result = new BookingResponse(); 
		JsonFactory factory = new JsonFactory();
		JsonParser  parser  = factory.createParser(qrCodeString);
		while(!parser.isClosed()){
		    JsonToken jsonToken = parser.nextToken();
		    if(JsonToken.FIELD_NAME.equals(jsonToken)){
		        String fieldName = parser.getCurrentName();
		        System.out.println(fieldName);
		        jsonToken = parser.nextToken();
		        if("calendarId".equals(fieldName)){
		        	result.setCalendarId(parser.getValueAsString());
		        } else if ("eventId".equals(fieldName)){
		        	result.setEventId(parser.getValueAsString());
		        }
		    }
		}
		return result;
	}
	@RequestMapping(value= "/fetchxml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public List<Company> getForObjectXMLDemo(@PathVariable(value = "id") Integer id) {
		List list =  new ArrayList<>();
		Company comp = new Company();
		comp.setId(id);
		comp.setCompanyName("XYZ");
		comp.setCeoName("ABCD");
		comp.setNoEmp(100);
		list.add(comp);
		Company comp1 = new Company();
		comp1.setId(id);
		comp1.setCompanyName("XYZ");
		comp1.setCeoName("ABCD");
		comp1.setNoEmp(100);
		list.add(comp1);
		return list;
	} 
	
}
