package model;

import java.util.HashMap;
import java.util.Map;

public class ServiceBindResponse {

	private Map<String, Object> credentials;
	
	public ServiceBindResponse() {
		credentials = new HashMap<String, Object>();
	}
	
	public Map<String, Object> getCredentials() {
		return credentials;
	}
	
	public void addCredential(String key, String value) {
		credentials.put(key, value);
	}
}
