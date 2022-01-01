/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lydbydissing.scheduledcheckinservice.schedulingtasks;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lydbydissing.scheduledcheckinservice.heartbeats.Heartbeat;


@Component
public class CloudflareHeartbeat {

	private static final Logger log = LoggerFactory.getLogger(CloudflareHeartbeat.class);
//	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private HttpHeaders headers = new HttpHeaders();
	private JSONObject heartbeatJsonObject = new JSONObject();
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {

	    // add the header only once
		this.headers.add("user-agent", "Application");
	    
		return builder.build();
	}
	
	// Every minute
	@Scheduled(fixedRate = 60000)
	public void reportCurrentTime() throws URISyntaxException {
		log.info("Producing heartbeat now. Local time: {}", new Date());

		RestTemplate restTemplate = new RestTemplate();

		Heartbeat heartbeat = new Heartbeat(new URI(System.getProperty("HEARTBEAT_URI")), 
											System.getProperty("HEARTBEAT_LOCATION"));
		this.headers.setContentType(MediaType.APPLICATION_JSON);
	    heartbeatJsonObject.put("location", heartbeat.getLocation());
	    heartbeatJsonObject.put("local date", heartbeat.getLocalDate());

	    HttpEntity<String> request = new HttpEntity<String>(heartbeatJsonObject.toString(), headers);
	    String heartbeatResultAsJsonStr = restTemplate.postForObject(heartbeat.getUri(), request, String.class);
	    log.info("Heartbeat response: {}", heartbeatResultAsJsonStr);
	}

}