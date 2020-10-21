package com.beecode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beecode.entity.ProductOrder;
import com.beecode.interfaces.ProductOrderService;
import com.beecode.repository.IProductOrderRepository;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

	@Autowired
	private IProductOrderRepository orderRepo;
	
	@Override
	public ProductOrder creatProductOrder(ProductOrder productOrder) {
		return orderRepo.save(productOrder);
	}

	@Override
	public ProductOrder updateProductOrder(ProductOrder productOrder) {
		return orderRepo.save(productOrder);
	}

	@Override
	public Optional<ProductOrder> getProductOrderrById(Long id) {
		Optional<ProductOrder> optionalProductOrder = orderRepo.findById(id);
		if(optionalProductOrder.isPresent()){
			return optionalProductOrder;
		}
		return null;
	}

	@Override
	public void deleteProductOrder(Long id) {
		orderRepo.deleteById(id);
	}

	@Override
	public List<ProductOrder> getAllProductOrder() {
		return orderRepo.findAll();
	}

}
