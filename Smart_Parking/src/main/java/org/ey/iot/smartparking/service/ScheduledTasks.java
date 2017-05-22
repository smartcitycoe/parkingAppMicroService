/**
 * 
 */
package org.ey.iot.smartparking.service;

/**
 * @author Kaushalendra.Singh
 *
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.ey.iot.smartparking.model.MQTTResponse;
import org.ey.iot.smartparking.model.SensorOccupancyStatusResponse;
import org.ey.iot.smartparking.model.SensorOccupencyStatus;
import org.ey.iot.smartparking.mqtt.MQTTPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ScheduledTasks {

	@Autowired
	private MQTTPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    private List<SensorOccupencyStatus> lastResponse = new ArrayList<SensorOccupencyStatus>();

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {
    	NedappSensitAPI api = new NedappSensitAPI();
		SensorOccupancyStatusResponse resp = api.getSensorData();
		for(SensorOccupencyStatus bay:resp.getSensors()){
			int i = lastResponse.indexOf(bay);
			if(i>-1 && !lastResponse.get(i).getState().equals(bay.getState())){
				String hardwareId = bay.getNodeid();
				/*if(hardwareId.lastIndexOf(".")>0){
					hardwareId = hardwareId.substring(hardwareId.lastIndexOf(".")+1);
				}*/
				MQTTResponse response = new MQTTResponse();
				response.setHardwareId(hardwareId);
				MQTTResponse.Request request = response.createRequest();
				request.setEventDate(DateUtil.currentDateinGMT());	
				request.setLevel("info");
				request.setMessage(bay.getState());
				request.setUpdateState(true);
				request.setType("occupancy");
				response.setType("DeviceAlert");
				ObjectMapper mapper = new ObjectMapper();
				String responseMessage ="";
				try {
					responseMessage = mapper.writeValueAsString(response);
					System.out.println("responce " + responseMessage);
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				publisher.pushMessage(responseMessage, "EYSEP/input/json/100190/"+hardwareId+"/alert");
			}
		}
		lastResponse= resp.getSensors();
		lastResponse.get(2).setState("free");
		//System.out.println(resp.getSensors().size());
        log.info("API Response IS",resp);
    }
}