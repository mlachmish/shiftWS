package com.shiftapp.test.client;

import java.io.ByteArrayInputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

public class JerseyClient {
	
	public static void main(String[] args) {
		try {

			ClientResponse response = loginB();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus() +  " " + response.getEntity(String.class));
			}

			String output = response.getEntity(String.class);

			System.out.println("Server response .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private static ClientResponse signup() {
		ClientConfig clientConfig = new DefaultClientConfig();
		
		clientConfig.getFeatures().put(
				JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		
		Client client = Client.create(clientConfig);
		
		WebResource webResource = client
				.resource("http://localhost:8080/app_api/v1.0/identify/signup");
		
		String conString = "This is the content";

	    FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
	    formDataMultiPart.field("firstName", "Matan");
	    formDataMultiPart.field("lastName", "Lachmish");
	    formDataMultiPart.field("countryCode", "+972");
	    formDataMultiPart.field("phoneNumber", "123");

	    FormDataBodyPart bodyPart = new FormDataBodyPart("picFile",
	            new ByteArrayInputStream(conString.getBytes()),
	            MediaType.APPLICATION_OCTET_STREAM_TYPE);
	    formDataMultiPart.bodyPart(bodyPart);
		
		ClientResponse response = webResource
				.type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, formDataMultiPart);
		return response;
	}
	
	private static ClientResponse loginA() {
		ClientConfig clientConfig = new DefaultClientConfig();

		clientConfig.getFeatures().put(
				JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

		Client client = Client.create(clientConfig);

		WebResource webResource = client
				.resource("http://localhost:8080/app_api/v1.0/identify/loginA");

		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("countryCode", "+972");
		formData.add("phoneNumber", "123");
		
		ClientResponse response = webResource
				.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
		return response;
	}

	private static ClientResponse loginB() {
		ClientConfig clientConfig = new DefaultClientConfig();
		
		clientConfig.getFeatures().put(
				JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		
		Client client = Client.create(clientConfig);
		
		WebResource webResource = client
				.resource("http://localhost:8080/app_api/v1.0/identify/loginB");
		
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("countryCode", "+972");
		formData.add("phoneNumber", "123");
		formData.add("password", "6980");
		
		ClientResponse response = webResource
				.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
		return response;
	}

}
