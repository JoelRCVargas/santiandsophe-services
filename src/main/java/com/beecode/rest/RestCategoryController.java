package com.beecode.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beecode.entity.Category;
import com.beecode.interfaces.CategoryService;

@RestController
public class RestCategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/public/category/all", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Category>> getAllTopNotice(){
		return ResponseEntity.ok().body(categoryService.getAllCategory());
	}
	
	@PostMapping(value = "/admin/category/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Category> categoryCreate(@RequestBody Category category){
		return ResponseEntity.ok().body(categoryService.createCategory(category));
	}
	
	@GetMapping(value = "/admin/category/delete", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> deletecategoryById(@RequestParam("id_category") Long id_category) {
		return categoryService.deleteCategory(id_category);
	}
	

}
