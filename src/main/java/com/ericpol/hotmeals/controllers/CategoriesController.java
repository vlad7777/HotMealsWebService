package com.ericpol.hotmeals.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ericpol.hotmeals.model.CategoriesRepository;
import com.ericpol.hotmeals.model.Category;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/hotmeals")
public class CategoriesController {

	@Autowired
	private CategoriesRepository cr;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public @ResponseBody List<Category> fetchCategories() {
		return cr.findAll();
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public @ResponseBody Category addCategory(@RequestBody Category c) {

		Category result;
		
		List<Category> categories = cr.findBySupplierIdAndName(c.getSupplierId(), c.getName());
		if (categories ==  null || categories.isEmpty())
			result = cr.save(c);
		else
			result = categories.get(0);
		
		return result;
	}

	@RequestMapping(value = "/suppliers/{supplierId}/categories", method = RequestMethod.GET)
	public @ResponseBody List<Category> fetchCategories(@PathVariable Long supplierId) {
		return cr.findBySupplierId(supplierId);
	}

}
