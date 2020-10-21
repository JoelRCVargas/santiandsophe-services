package com.beecode.interfaces;

import java.util.List;
import java.util.Optional;

import com.beecode.entity.ProductOrder;

public interface ProductOrderService {
	
	public ProductOrder creatProductOrder(ProductOrder productOrder);
	public ProductOrder updateProductOrder(ProductOrder productOrder);
	public Optional<ProductOrder> getProductOrderrById(Long id);
	public void deleteProductOrder(Long id);
	public List<ProductOrder> getAllProductOrder();
	
}
