package com.beecode.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beecode.entity.Provider;
import com.beecode.interfaces.ProviderService;

@RestController
public class RestProviderController {

	@Autowired
	private ProviderService providerService;
	
	@GetMapping(value = "/admin/provider/all", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Provider>> getAllProvider(){
		return ResponseEntity.ok().body(providerService.getAllProvider());
	}

	@PostMapping(value = "/admin/provider/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Provider> providerCreate(@RequestBody Provider provider){
		return ResponseEntity.ok().body(providerService.createProvider(provider));
	}
	
}
