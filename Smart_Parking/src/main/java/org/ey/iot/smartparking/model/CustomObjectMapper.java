
package org.ey.iot.smartparking.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Kaushalendra.Singh
 *
 */

@Primary
@ComponentScan
public class CustomObjectMapper{
	
	@Bean
	public ObjectMapper CustomObjectMapper() {
	    com.fasterxml.jackson.databind.ObjectMapper responseMapper = new com.fasterxml.jackson.databind.ObjectMapper();
	    return responseMapper;
	}

}