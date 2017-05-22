package org.ey.iot.smartparking.service;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Kaushalendra.Singh
 *
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
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

import ch.qos.logback.core.net.SyslogOutputStream;

public class ServiceAccountBasedAccess {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Google Calendar API Java Quickstart";

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    private static final List<String> SCOPES =
        Arrays.asList(CalendarScopes.CALENDAR);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
	
    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException,GeneralSecurityException {
    	
    	 java.io.File licenseFile = new java.io.File(new URL(ServiceAccountBasedAccess.class.getResource("ParkingCalendarProject-ce5e0db578de.p12").toString()).getFile());
    	 System.out.println("Absolute Path "+ licenseFile.getAbsolutePath());

    	   GoogleCredential credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
    	  .setJsonFactory(JSON_FACTORY)
    	  .setServiceAccountId("311378619760-compute@developer.gserviceaccount.com") 
    	  //.setServiceAccountUser(EMAIL_ADRESS)
    	  .setServiceAccountScopes(SCOPES)
    	  .setServiceAccountPrivateKeyFromP12File(licenseFile)
    	  .build();

        return credential;
    }

    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     */
    public static com.google.api.services.calendar.Calendar
        getCalendarService() throws IOException, GeneralSecurityException{
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void main(String[] args) throws IOException,ParseException,GeneralSecurityException {
    	System.out.println(ServiceAccountBasedAccess.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    	System.out.println(ServiceAccountBasedAccess.class.getResource("ParkingCalendarProject-ce5e0db578de.p12").toString());
    	//createEventquick();
    	/*System.out.println("\n********************************************************************************************************************");
		System.out.println("\n**********************************************Create Event*****************************************************");
		System.out.println("\n********************************************************************************************************************"); */
		//createEvent();
		getEvent("0dbfpdg815jksbdd8h17hvf5fg@group.calendar.google.com", "hqtmomu9k40cgl77qonjp1mql4");
    	/*System.out.println("\n********************************************************************************************************************");
		System.out.println("\n**********************************************FreeBusy Response*****************************************************");
		System.out.println("\n********************************************************************************************************************");*/
		getFreebusy();        
		//createEventquick();
    	System.out.println("\n********************************************************************************************************************");
		System.out.println("\n*************************************************Event List*********************************************************");
		System.out.println("\n********************************************************************************************************************");
		//getAllEvent();
		/*System.out.println("\n********************************************************************************************************************");
		System.out.println("\n*************************************************Calender List******************************************************");
		System.out.println("\n********************************************************************************************************************");
		getAllCalenders(); */
    }

	/**
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws ParseException
	 */
    
    
    public static Event getEvent(String calendarId, String eventId) throws IOException, GeneralSecurityException, ParseException {
		com.google.api.services.calendar.Calendar client =  getCalendarService();
		Event event = client.events().get(calendarId, eventId).execute();
		//System.out.println("Event Id: " + event.getId());
		//System.out.println("Organiser Email: " + event.getAttendees().get(0).getEmail());
		return event;
	}
    
    
	private static void getFreebusy() throws IOException, GeneralSecurityException, ParseException {
		com.google.api.services.calendar.Calendar client =
		        getCalendarService();
		   DateTime startTime = new DateTime("2017-03-15T13:30:00.000Z");
		   DateTime endTime = new DateTime("2017-03-15T14:30:00.000Z");
		   FreeBusyRequest request = new FreeBusyRequest();
		   FreeBusyRequestItem item1 = new FreeBusyRequestItem();
		   item1.setId("0dbfpdg815jksbdd8h17hvf5fg@group.calendar.google.com");
		   FreeBusyRequestItem item2 = new FreeBusyRequestItem();
		   item2.setId("01e0rvtm8msr371fa6d937rv68@group.calendar.google.com");
		   FreeBusyRequestItem item3 = new FreeBusyRequestItem();
		   item3.setId("iiokctfh2pv3qe8idp1o14lm2k@group.calendar.google.com");
		   FreeBusyRequestItem item4 = new FreeBusyRequestItem();
		   item4.setId("thg57l1r5d0f95aei6pqc2pm08@group.calendar.google.com");
		   List<FreeBusyRequestItem> itemList = new ArrayList<FreeBusyRequestItem>();
		   itemList.add(item1);
		   itemList.add(item2);
		   itemList.add(item3);
		   itemList.add(item4);
		   request.setTimeMax(endTime).setTimeZone("GMT");
		   request.setTimeMin(startTime).setTimeZone("GMT");
		   request.setItems(itemList);
		   System.out.println("request" + request);
		   Freebusy.Query fbq = client.freebusy().query(request);
		   FreeBusyResponse fbresponse = fbq.execute();
		   Set<Entry<String, FreeBusyCalendar>> set = fbresponse.getCalendars().entrySet();
		   for(Entry<String, FreeBusyCalendar> map:set){
			   System.out.println("Key:" +map.getKey() +"||||||Value"+ map.getValue().getBusy().isEmpty());
		   }
		  System.out.println(fbresponse.toString());
		  
	}
	
	
	/**
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws ParseException
	 */
	private static void getAllEvent() throws IOException, GeneralSecurityException, ParseException {
		com.google.api.services.calendar.Calendar client =
		        getCalendarService();
		// Iterate over the events in the specified calendar
		String pageToken = null;
		do {
		  com.google.api.services.calendar.model.Events events = client.events().list("0dbfpdg815jksbdd8h17hvf5fg@group.calendar.google.com").setPageToken(pageToken).execute();
		  List<Event> items = events.getItems();
		  for (Event event : items) {
		    System.out.println(event.getSummary()+":"+event.getId()+":"+ event.getStart());
		  }
		  pageToken = events.getNextPageToken();
		} while (pageToken != null);
	} 
	
	
	private static void getAllCalenders() throws IOException, GeneralSecurityException, ParseException {
		com.google.api.services.calendar.Calendar client =
		        getCalendarService();
		// Iterate through entries in calendar list
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
	
	
	
	public static void createEventquick() throws IOException,ParseException,GeneralSecurityException {

    	// Refer to the Java quickstart on how to setup the environment:
    	// https://developers.google.com/google-apps/calendar/quickstart/java
    	// Change the scope to CalendarScopes.CALENDAR and delete any stored
    	// credentials.
    	com.google.api.services.calendar.Calendar client =
                getCalendarService();
    	

    		// Quick-add an event
    		String eventText = "Appointment at Somewhere";
    		Event createdEvent =  client.events().quickAdd("01e0rvtm8msr371fa6d937rv68@group.calendar.google.com","Ramsingh").setText(eventText).execute();

    		System.out.println(createdEvent.getId());
     
     }
	
	
	
	
    public static void createEvent() throws IOException,ParseException,GeneralSecurityException {

    	// Refer to the Java quickstart on how to setup the environment:
    	// https://developers.google.com/google-apps/calendar/quickstart/java
    	// Change the scope to CalendarScopes.CALENDAR and delete any stored
    	// credentials.
    	com.google.api.services.calendar.Calendar client =
                getCalendarService();
    	Event event = new Event()
    	    .setSummary("Parking Lot Booking")
    	    .setLocation("Mayur Vihar Phase 1, Delhi, India")
    	    .setDescription("It is for testing the calender API");

    	DateTime startDateTime = new DateTime("2017-03-15T14:30:00.000Z");
    	EventDateTime start = new EventDateTime()
    	    .setDateTime(startDateTime)
    	    .setTimeZone("GMT");
    	event.setStart(start);

    	DateTime endDateTime = new DateTime("2017-03-15T15:30:00.000Z");
    	EventDateTime end = new EventDateTime()
    	    .setDateTime(endDateTime)
    	    .setTimeZone("GMT");
    	event.setEnd(end);

    	String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
    	event.setRecurrence(Arrays.asList(recurrence));

    	EventAttendee[] attendees = new EventAttendee[] {
    	    new EventAttendee().setEmail("311378619760-compute@developer.gserviceaccount.com"),
    	    new EventAttendee().setEmail("ey.smartcity.coe@gmail.com"),
    	};
    	event.setAttendees(Arrays.asList(attendees));

    	EventReminder[] reminderOverrides = new EventReminder[] {
    	    new EventReminder().setMethod("email").setMinutes(24 * 60),
    	    new EventReminder().setMethod("popup").setMinutes(10),
    	};
    	Event.Reminders reminders = new Event.Reminders()
    	    .setUseDefault(false)
    	    .setOverrides(Arrays.asList(reminderOverrides));
    	event.setReminders(reminders);

    	String calendarId = "0dbfpdg815jksbdd8h17hvf5fg@group.calendar.google.com";
    	event = client.events().insert(calendarId, event).execute();
    	System.out.printf("Event created: %s\n", event.getHtmlLink());
    	
    	System.out.println(event.getId());
    	System.out.println(event.getOrganizer().getId());
    	System.out.println(event.getOrganizer().getEmail());
     
     }
    
    

}
