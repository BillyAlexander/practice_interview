package com.evilla.test.solicitud.services;

import org.springframework.stereotype.Service;

@Service
public class Utils {
	public String createUrl(Long colorId) {
		return "/" + colorId;
	}
	
	public String createUrl(String name) {
		return "/find?" + name;
	}
}
