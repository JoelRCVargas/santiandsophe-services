package com.beecode.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.beecode.entity.Cupon;

public interface CuponService {
	
	public Cupon createCupon(Cupon cupon);
	public Cupon updateCupon(Cupon cupon);
	public List<Cupon> getAllCupon();
	public ResponseEntity<String> deleteCupon(Long cuponId);
	public Optional<Cupon> getCuponByName(String name);

}
