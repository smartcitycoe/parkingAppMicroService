/**
 * 
 */
package org.ey.iot.smartparking.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Kaushalendra.Singh
 *
 */

@Component
public class MQTTPublisher {
	MqttClient client; 
	
	@Value("${mqtt.broker.ip}")
	private String mqttBrokerIp;
	
	@Value("${mqtt.broker.port}")
	private String mqttBrokerPort;
	

	/**
	 * 
	 */
	
	public MqttClient getClient() {
		if(client == null){
			try {
				MemoryPersistence persistence = new MemoryPersistence();
				String url = "tcp://35.154.110.137:1883";
				client = new MqttClient(url, "pahomqttpublish1",persistence);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return client;
	}

	public void pushMessage(String msg, String topic){
		MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        try {
			getClient().connect(connOpts);
			System.out.println("Topic:"+ topic);
			MqttMessage message = new MqttMessage(msg.getBytes());
	        message.setQos(2);
	        client.publish(topic, message);
	        //System.out.println("Message published");
	        client.disconnect();
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}
	
	public void sendMessage(String content, String topic){
		 	//String topic        = "MQTT Examples";
		    //String content      = "Message from MqttPublishSample";
		    int qos             = 2;
		    String broker       = "tcp://35.154.110.137:1883";
		    String clientId     = "pahomqttpublish1";
		    MemoryPersistence persistence = new MemoryPersistence();

		    try {
		        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
		        MqttConnectOptions connOpts = new MqttConnectOptions();
		        connOpts.setCleanSession(true);
		        System.out.println("Connecting to broker: "+broker);
		        sampleClient.connect(connOpts);
		        System.out.println("Connected");
		        System.out.println("Publishing message: "+content);
		        MqttMessage message = new MqttMessage(content.getBytes());
		        message.setQos(qos);
		        sampleClient.publish(topic, message);
		        System.out.println("Message published");
		        sampleClient.disconnect();
		        System.out.println("Disconnected");
		        //System.exit(0);
		    } catch(MqttException me) {
		        System.out.println("reason "+me.getReasonCode());
		        System.out.println("msg "+me.getMessage());
		        System.out.println("loc "+me.getLocalizedMessage());
		        System.out.println("cause "+me.getCause());
		        System.out.println("excep "+me);
		        me.printStackTrace();
		    }
	}
	public void setClient(MqttClient client) {
		this.client = client;
	}

	public String getMqttBrokerIp() {
		return mqttBrokerIp;
	}

	public void setMqttBrokerIp(String mqttBrokerIp) {
		this.mqttBrokerIp = mqttBrokerIp;
	}

	public String getMqttBrokerPort() {
		return mqttBrokerPort;
	}

	public void setMqttBrokerPort(String mqttBrokerPort) {
		this.mqttBrokerPort = mqttBrokerPort;
	}

	
	
	public static void main(String[] args) {

	    String topic        = "MQTT Examples";
	    String content      = "Message from MqttPublishSample";
	    int qos             = 2;
	    String broker       = "tcp://35.154.110.137:1883";
	    String clientId     = "JavaSample";
	    MemoryPersistence persistence = new MemoryPersistence();

	    try {
	        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        System.out.println("Connecting to broker: "+broker);
	        sampleClient.connect(connOpts);
	        System.out.println("Connected");
	        System.out.println("Publishing message: "+content);
	        MqttMessage message = new MqttMessage(content.getBytes());
	        message.setQos(qos);
	        sampleClient.publish(topic, message);
	        System.out.println("Message published");
	        sampleClient.disconnect();
	        System.out.println("Disconnected");
	        System.exit(0);
	    } catch(MqttException me) {
	        System.out.println("reason "+me.getReasonCode());
	        System.out.println("msg "+me.getMessage());
	        System.out.println("loc "+me.getLocalizedMessage());
	        System.out.println("cause "+me.getCause());
	        System.out.println("excep "+me);
	        me.printStackTrace();
	    }
	}
}
