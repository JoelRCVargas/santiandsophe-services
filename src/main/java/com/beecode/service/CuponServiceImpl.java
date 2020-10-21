package com.beecode.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.beecode.entity.Cupon;
import com.beecode.interfaces.CuponService;
import com.beecode.repository.ICuponRepository;

@Service
public class CuponServiceImpl implements CuponService{
	
	@Autowired
	private ICuponRepository cuponRepo;

	@Override
	public Cupon createCupon(Cupon cupon) {
		String str = cupon.getExpiredDateTime() + " " + "00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		cupon.setLocalExpiredDateTime(dateTime);
		return cuponRepo.save(cupon);
	}

	@Override
	public Cupon updateCupon(Cupon cupon) {
		return cuponRepo.save(cupon);
	}

	@Override
	public List<Cupon> getAllCupon() {
		List<Cupon> listCupon = cuponRepo.findAll();
		return listCupon;
	}

	@Override
	public ResponseEntity<String> deleteCupon(Long cuponId) {
		cuponRepo.deleteById(cuponId);
		return ResponseEntity.ok("Se elimino correctamente!");
	}

	@Override
	public Optional<Cupon> getCuponByName(String name) {
		Optional<Cupon> optionalCupon = cuponRepo.findByName(name);
		if(optionalCupon.isPresent()) {
			return optionalCupon;
		}
		return null;
	}

	
}
