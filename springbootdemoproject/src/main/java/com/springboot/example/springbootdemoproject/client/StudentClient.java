package com.springboot.example.springbootdemoproject.client;

import static com.springboot.example.springbootdemoproject.model.ApplicationConstants.URI_GET_ALL_TOPPERS;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * Rest Client that sends requests to the service.
 *
 *
 * 
 * 
 * 
 */
public class StudentClient {
	/**
	 * 
	 * Fetches the toppers as String and prints in the console
	 * 
	 */
	private static void getToppersAsString() {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(URI_GET_ALL_TOPPERS, String.class);
		System.out.println("Get Toppers As String" + result);
	}

	/**
	 * 
	 * Requests the response in XML format and prints the object in the console.
	 * 
	 */
	private static void getToppersAsXML() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_XML }));
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<StudentResponse[]> entity = new HttpEntity<StudentResponse[]>(headers);
		// Send request with GET method, and Headers.
		ResponseEntity<StudentResponse[]> response = restTemplate.exchange(URI_GET_ALL_TOPPERS, HttpMethod.GET, entity,
				StudentResponse[].class);
		StudentResponse[] list = response.getBody();
		if (list != null) {
			System.out.println("Get Toppers as XML");
			for (StudentResponse student : list) {
				System.out.println("Student: " + student.getId() + " - " + student.getName());
			}
		}
	}

	/**
	 * 
	 * Requests the response in JSON format and prints the object in the
	 * 
	 * console.
	 * 
	 */
	private static void getToppersAsJSON() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<StudentResponse[]> entity = new HttpEntity<StudentResponse[]>(headers);
		// Send request with GET method, and Headers.
		ResponseEntity<StudentResponse[]> response = restTemplate.exchange(URI_GET_ALL_TOPPERS, HttpMethod.GET, entity,
				StudentResponse[].class);
		StudentResponse[] list = response.getBody();
		if (list != null) {
			System.out.println("Get Toppers as JSON");
			for (StudentResponse student : list) {
				System.out.println("Student: " + student.getId() + " - " + student.getName());
			}
		}
	}

	public static void main(String[] args) {
		getToppersAsString();
		getToppersAsXML();
		getToppersAsJSON();
	}
}
