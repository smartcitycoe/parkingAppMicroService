/**
 * 
 */
package org.ey.iot.smartparking.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar.Freebusy;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.FreeBusyCalendar;
import com.google.api.services.calendar.model.FreeBusyRequest;
import com.google.api.services.calendar.model.FreeBusyRequestItem;
import com.google.api.services.calendar.model.FreeBusyResponse;

/**
 * @author Kaushalendra.Singh
 *
 */
@Service
public class GoogleCalendarAPI {
	
	@Autowired
	GoogleCalendarAPI claendarAPI;
	
	/** Application name. */
    private static final String APPLICATION_NAME = "SmartParkingSolution";

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    
    /** Global instance of the Certificate File Name */
    private static final String CERTIFICATE_FILE_NAME = "ParkingCalendarProject-ce5e0db578de.p12";
    
    /** Global instance of the Service Account. */
    private static final String SERVICE_ACCOUNT_ID = "311378619760-compute@developer.gserviceaccount.com";
     
    

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;
    
    private static File CERTIFICATE_FILE_PATH ;

    private static final List<String> SCOPES =
        Arrays.asList(CalendarScopes.CALENDAR);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            
            //CERTIFICATE_FILE_PATH = new java.io.File("/home/ubuntu/"+CERTIFICATE_FILE_NAME);
            CERTIFICATE_FILE_PATH = new java.io.File("C:/Development/IOT/Smart_Parking/target/classes/"+CERTIFICATE_FILE_NAME);
        } catch (Throwable t) {
            t.printStackTrace();            
        }
    } 
  
	
    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    private Credential authorize() throws IOException,GeneralSecurityException {
    	ClassLoader classLoader = getClass().getClassLoader();
    	//CERTIFICATE_FILE_PATH =\ new File(classLoader.getResource(CERTIFICATE_FILE_NAME).getFile());
    	GoogleCredential credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
    	  .setJsonFactory(JSON_FACTORY)
    	  .setServiceAccountId(SERVICE_ACCOUNT_ID) 
    	  .setServiceAccountScopes(SCOPES)
    	  .setServiceAccountPrivateKeyFromP12File(CERTIFICATE_FILE_PATH)
    	  .build();
        return credential;
    }

    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     */
    private com.google.api.services.calendar.Calendar
        getCalendarService() throws IOException, GeneralSecurityException{
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    /**
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws ParseException
	 */
	public List<String> getFreebusy(DateTime startTime,DateTime endTime, List<FreeBusyRequestItem> itemList) throws IOException, GeneralSecurityException, ParseException {
		com.google.api.services.calendar.Calendar client = getCalendarService();
		  
		   List<String> freeCalenders = new ArrayList<String>();
		   FreeBusyRequest request = new FreeBusyRequest();
		   request.setTimeMax(endTime).setTimeZone(DateUtil.GOOGLE_CALENDAR_TIME_ZONE);
		   request.setTimeMin(startTime).setTimeZone(DateUtil.GOOGLE_CALENDAR_TIME_ZONE);
		   request.setItems(itemList);
		   System.out.println("request" + request);
		   Freebusy.Query fbq = client.freebusy().query(request);
		   FreeBusyResponse fbresponse = fbq.execute();
		   Set<Entry<String, FreeBusyCalendar>> set = fbresponse.getCalendars().entrySet();
		   for(Entry<String, FreeBusyCalendar> map:set){
			   boolean flag = map.getValue().getBusy().isEmpty();
			   if(flag){
				   
				   freeCalenders.add(map.getKey());
			   }
			   System.out.println("Key:" +map.getKey() +"||||||Free : "+ map.getValue().getBusy().isEmpty());
		   }
		  System.out.println(fbresponse.toString());
		  return freeCalenders;	
	}
	
	
	/**
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws ParseException
	 */
	public void getAllEvent(String calendarId) throws IOException, GeneralSecurityException, ParseException {
		com.google.api.services.calendar.Calendar client =  getCalendarService();
		String pageToken = null;
		do {
		  com.google.api.services.calendar.model.Events events = client.events().list(calendarId).setPageToken(pageToken).execute();
		  List<Event> items = events.getItems();
		  for (Event event : items) {
		    System.out.println(event.getSummary()+":"+event.getId()+":"+ event.getStart());
		  }
		  pageToken = events.getNextPageToken();
		} while (pageToken != null);
	}
	
	public Event getEvent(String calendarId, String eventId) throws IOException, GeneralSecurityException, ParseException {
		com.google.api.services.calendar.Calendar client =  getCalendarService();
		Event event = client.events().get(calendarId, eventId).execute();
		return event;
	}
	
	
	public void getAllCalendersForServiceAccount() throws IOException, GeneralSecurityException, ParseException {
		com.google.api.services.calendar.Calendar client =
		        getCalendarService();
		String pageToken = null;
		do {
		  CalendarList calendarList = client.calendarList().list().setPageToken(pageToken).execute();
		  List<CalendarListEntry> items = calendarList.getItems();

		  for (CalendarListEntry calendarListEntry : items) {
		    System.out.println(calendarListEntry.getSummary() +"Descrption"+ calendarListEntry.getDescription()+" Calender Id: "+calendarListEntry.getId());
		    
		  }
		  pageToken = calendarList.getNextPageToken();
		} while (pageToken != null);
	} 
	
	
	
	public void createEventquick(String calendarId, String text, String eventText) throws IOException,ParseException,GeneralSecurityException {
    	com.google.api.services.calendar.Calendar client =
                getCalendarService();
   		Event createdEvent =  client.events().quickAdd(calendarId,text).setText(eventText).execute();
   		System.out.println(createdEvent.getId());
     
     }
	
	public Event createEvent(String calendarId, DateTime startDateTime, DateTime endDateTime, String attendeesEmail,String eventSummary) throws IOException,ParseException,GeneralSecurityException {
	  return createEvent(calendarId, startDateTime, endDateTime, attendeesEmail, eventSummary, null, null);
	}
	
	public Event createEvent(String calendarId, DateTime startDateTime, DateTime endDateTime, String attendeesEmail) throws IOException,ParseException,GeneralSecurityException {
		  return createEvent(calendarId, startDateTime, endDateTime, attendeesEmail, null);
	}
	
    public Event createEvent(String calendarId, DateTime startDateTime, DateTime endDateTime, String attendeesEmail,String eventSummary, String location, String description) throws IOException,ParseException,GeneralSecurityException {
    	com.google.api.services.calendar.Calendar client =  getCalendarService();
    	Event event = new Event()
    	    .setSummary(eventSummary == null?"Parking Bay Booking":eventSummary)
    	    .setLocation(location)
    	    .setDescription(description);
    	EventDateTime start = new EventDateTime()
    	    .setDateTime(startDateTime)
    	    .setTimeZone(DateUtil.GOOGLE_CALENDAR_TIME_ZONE);
    	event.setStart(start);
    	EventDateTime end = new EventDateTime()
    	    .setDateTime(endDateTime)
    	    .setTimeZone(DateUtil.GOOGLE_CALENDAR_TIME_ZONE);
    	event.setEnd(end);

    	String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
    	event.setRecurrence(Arrays.asList(recurrence));

    	EventAttendee[] attendees = new EventAttendee[] {
    	    new EventAttendee().setEmail(attendeesEmail)
    	};
    	event.setAttendees(Arrays.asList(attendees));

    	EventReminder[] reminderOverrides = new EventReminder[] {
    	    new EventReminder().setMethod("email").setMinutes(24 * 60)
    	};
    	Event.Reminders reminders = new Event.Reminders()
    	    .setUseDefault(false)
    	    .setOverrides(Arrays.asList(reminderOverrides));
    	event.setReminders(reminders);    	
    	event = client.events().insert(calendarId, event).execute();
    	System.out.printf("Event created: %s\n", event.getHtmlLink());
    	System.out.println("Email : " + event.getOrganizer().getEmail());//Calendar Id is the same as organizer's email id
    	System.out.println("Event id : " + event.getId());
    	System.out.println("Event Calendar : " + calendarId);
    	return event;
     }
    
     public String getQrCodeString(Event event, String userId){
    	 return userId + "|" + event.getOrganizer().getEmail() + "|" + event.getId();
     }
}
