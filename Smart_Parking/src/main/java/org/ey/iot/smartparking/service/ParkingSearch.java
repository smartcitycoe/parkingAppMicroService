package org.ey.iot.smartparking.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.rmi.CORBA.UtilDelegate;

import org.ey.iot.smartparking.model.ParkingBay;
import org.ey.iot.smartparking.model.ParkingBayResult;
import org.ey.iot.smartparking.model.ParkingLot;
import org.ey.iot.smartparking.model.ParkingLotForMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.FreeBusyRequestItem;



/**
 * @author Kaushalendra.Singh
 *
 */

public class ParkingSearch {
	
	
	public static void main (String[] args) throws java.lang.Exception
	{
		System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
		System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
		System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");
	}

	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}
	
	public static  List<ParkingLot> getMatchingParkingLot(List<ParkingLot> parkingLots,double userLocLatitude, double userLocLongitude,double distance, String type,String startTime, String endTime, SitewhereAPI sitewhereAPI, GoogleCalendarAPI calendarAPI) throws ParseException, IOException, GeneralSecurityException{
		List<ParkingLot> parkingList = new ArrayList<ParkingLot>();
		 //JSONParser parser = new JSONParser();
		if(parkingLots!=null && parkingLots.size() >0){
			for(ParkingLot parkingLot:parkingLots){
				Double latitude = parkingLot.getMap().getMetadata().getCenterLatitude();
				Double longitude = parkingLot.getMap().getMetadata().getCenterLongitude();
				double actualDistance = distance(userLocLatitude,userLocLongitude,latitude,longitude,type);
				if(actualDistance<=distance){
					List<ParkingBay> bayList = sitewhereAPI.getParkingBays(parkingLot.getToken());
					List<FreeBusyRequestItem> itemList = new ArrayList<FreeBusyRequestItem>();
					for(ParkingBay bay:bayList){						
						FreeBusyRequestItem item = new FreeBusyRequestItem();
						item.setId(bay.getMetadata().getCalendarID());
						itemList.add(item);
					}
					int freeCount = 0;
					if(itemList != null && !itemList.isEmpty()){
						DateTime start = DateUtil.convertDateStrToDateTime(startTime);
						DateTime end = DateUtil.convertDateStrToDateTime(endTime);
						Calendar cal = Calendar.getInstance();
						System.out.println("Google Start Time: "+cal.getTime());						
						List<String> freeCalendars = calendarAPI.getFreebusy(start, end, itemList);
						Calendar cal1 = Calendar.getInstance();
						System.out.println("Google End Time: "+cal1.getTime());
						System.out.println("Google Total Time: "+(cal1.getTimeInMillis() - cal.getTimeInMillis()));
						freeCount = freeCalendars.size();
						
					}
					parkingLot.getMetadata().setAvailableSpotNumber(freeCount);
					parkingList.add(parkingLot);
//					Date d = df.parse(dIn);
//					DateTime startTime = new DateTime(d, TimeZone.getDefault());
//
//					Date de = df.parse(dIne);
//					DateTime endTime = new DateTime(de, TimeZone.getDefault());
//					FreeBusyRequest req = new FreeBusyRequest();
//					req.setTimeMin(startTime);
//					req.setTimeMax(endTime);
					//Freebusy.Query fbq = client.freebusy().query(req);

//					FreeBusyResponse fbresponse = fbq.execute();
//					System.out.println(fbresponse.toString());
//					parkingList.add(parkingLot);
//					
					/*ParkingLotForMobile parking = new ParkingLotForMobile();
					parking.setAcceptedPaymentMethods(parkingLot.getMetadata().getAcceptedPaymentMethods());
					parking.setAvailableSpotNumber(parkingLot.getMetadata().getAvailableSpotNumber());
					parking.setBillingCyle(parkingLot.getMetadata().getBillingCyle());
					parking.setCenterLatitude(latitude);
					parking.setCenterLongitude(longitude);
					parking.setDescription(parkingLot.getDescription());
					parking.setName(parkingLot.getName());
					parking.setImageUrl(parkingLot.getImageUrl());
					parking.setMaximumParkingDuration(parkingLot.getMetadata().getMaximumParkingDuration());
					parking.setNormalRatePerCycle(parkingLot.getMetadata().getNormalRatePerCycle());
					parking.setOverstayRatePerCycle(parkingLot.getMetadata().getOvrestayRatePerCycle());
					parking.setTotalSpotNumber(parkingLot.getMetadata().getTotalSpotNumber());
					parking.setType(parkingLot.getMetadata().getType());
					parkingList.add(parking);*/
				}
			}
		}
		
		return parkingList;
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}


}
