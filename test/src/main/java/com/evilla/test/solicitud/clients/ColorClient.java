package com.evilla.test.solicitud.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.evilla.test.solicitud.dtos.ColorDto;
import com.evilla.test.solicitud.services.Utils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ColorClient {
	@Autowired
	private RestTemplate restTemplate;

	ResponseEntity<ColorDto> result = null;

	@Autowired
	Utils utilsService;

	@Value("${testex.api.url.colors}")
	String REQUEST_URI_COLOR;

	public ColorDto getColorById(Long colorId) {
		ColorDto searchResult = null;
		String url = REQUEST_URI_COLOR + utilsService.createUrl(colorId);
		log.info("Request course url: {} ", url);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>("parameters", headers);

		ParameterizedTypeReference<ColorDto> responseType = new ParameterizedTypeReference<ColorDto>() {
		};
		log.debug("Llamando a  color Service");

		result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
		searchResult = result.getBody();
		log.info(" color Service{}",searchResult);

		return searchResult;
	}
	
	

}
