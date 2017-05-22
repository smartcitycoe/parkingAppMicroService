package org.ey.iot.smartparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartParking {

	public static void main(String[] args) {
		SpringApplication.run(SmartParking.class, args);
		}
}
