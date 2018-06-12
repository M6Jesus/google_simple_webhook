package org.norsys.pamela.webhookSimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.grpc.Context.Storage;

@SpringBootApplication
public class GoogleApRunner {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(GoogleApRunner.class, args);
		
//		
//		 Storage storage = StorageOptions.getDefaultInstance().getService();
//
//		  System.out.println("Buckets:");
//		  Page<Bucket> buckets = storage.list();
//		  for (Bucket bucket : buckets.iterateAll()) {
//		    System.out.println(bucket.toString());
//
//		
		
		
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
