package com.beecode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beecode.entity.Product_picture;

public interface IProductPictureRepository extends JpaRepository<Product_picture, Long> {
	
	public List<Product_picture> findByProductId(Long id);
	
}
