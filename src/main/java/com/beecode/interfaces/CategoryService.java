package com.beecode.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.beecode.entity.Category;

public interface CategoryService {
	
	public Category createCategory(Category category);
	public Category updateCategory(Category category);
	public Category getCategoryById(Long id);
	public ResponseEntity<String> deleteCategory(Long id);
	public List<Category> getAllCategory();

}
