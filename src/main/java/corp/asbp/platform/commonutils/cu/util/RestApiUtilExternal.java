package corp.asbp.platform.commonutils.cu.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import corp.asbp.platform.commonutils.cu.model.RestMethod;

public class RestApiUtilExternal<I, O> {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiUtilExternal.class);
	
	public O request(RestTemplate restTemplate, String url, RestMethod method, I input, Class<O> responseClazz) throws IOException {
		return request(restTemplate, url, method, input, null, responseClazz);
	}
	
	public O request(RestTemplate restTemplate, String url, RestMethod method, I input, HttpHeaders headers, Class<O> responseClazz) {
		ResponseEntity<O> response = requestAndGetResponseEntity(restTemplate, url, method, input, headers, responseClazz);
		return response.getBody();
	}
	
	public ResponseEntity<O> requestAndGetResponseEntity(RestTemplate restTemplate, String url, RestMethod method, I input, Class<O> responseClazz) throws IOException {
		return requestAndGetResponseEntity(restTemplate, url, method, input, null, responseClazz);
	}
	
	public ResponseEntity<O> requestAndGetResponseEntity(RestTemplate restTemplate, String url, RestMethod method, I input, HttpHeaders headers, Class<O> responseClazz) {
		HttpMethod httpMethod = method.getHttpMethod();
		HttpEntity<I> entity = new HttpEntity<I>(input, headers);
		LOGGER.info("Rest url: " + url + ", input: " + input);
		return restTemplate.exchange(url, httpMethod, entity, responseClazz);
	}
}
