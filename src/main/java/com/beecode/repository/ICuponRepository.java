package com.beecode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beecode.entity.Cupon;

public interface ICuponRepository extends JpaRepository<Cupon, Long> {
	
	public Optional<Cupon> findByName(String name);
	
}
