/*package com.biblioteca.springboot.backend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class RestAPIConnect {

	private static void getById(String uri)
	{
	    String uri = "http://localhost:8080/springrestexample/employees/{id}";
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "1");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class, params);
	    restTemplate.
	     
	    System.out.println(result);
	}
}
*/