package com.beecode.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.beecode.entity.Provider;

public interface ProviderService {
	
	public Provider createProvider(Provider provider);
	public Provider updateProvider(Provider provider);
	public Provider getProviderById(Long id);
	public ResponseEntity<String> deleteProvider(Long id);
	public List<Provider> getAllProvider();
	
}
