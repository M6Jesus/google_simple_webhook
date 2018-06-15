package org.norsys.pamela.webhookSimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * runner de l'application google_simple_webhook
 * @author panou
 *
 */
@SpringBootApplication
public class GoogleApRunner {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(GoogleApRunner.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
