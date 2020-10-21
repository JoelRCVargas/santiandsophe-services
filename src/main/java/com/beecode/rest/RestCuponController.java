package com.beecode.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beecode.entity.Cupon;
import com.beecode.interfaces.CuponService;

@RestController
public class RestCuponController {
	
	@Autowired
	private CuponService cuponService;

	@GetMapping(value = "/public/cupon/by/name", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Optional<Cupon>> getCuponByName(@RequestParam("name") String name) {
		return ResponseEntity.ok().body(cuponService.getCuponByName(name));
	}
	
	@GetMapping(value = "/admin/cupon/all", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Cupon>> getAllCupon() {
		return ResponseEntity.ok().body(cuponService.getAllCupon());
	}
	
	@PostMapping(value = "/admin/cupon/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cupon> updatePerson(@RequestBody Cupon coupon){
		return ResponseEntity.status(HttpStatus.CREATED).body(cuponService.createCupon(coupon));
	}
	
	@GetMapping(value = "/admin/cupon/delete", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> deleteCuponById(@RequestParam("id_coupon") Long cuponId) {
		return cuponService.deleteCupon(cuponId);
	}
	

}
