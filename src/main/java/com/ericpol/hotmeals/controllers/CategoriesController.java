package com.ericpol.hotmeals.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ericpol.hotmeals.model.CategoriesRepository;
import com.ericpol.hotmeals.model.Category;
import com.ericpol.hotmeals.model.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("")
public class CategoriesController {

	@Autowired
	private CategoriesRepository cr;

	@RequestMapping(value = "/hotmeals/category", method = RequestMethod.GET)
	public @ResponseBody List<Category> fetchCategories() {
		return cr.findAll();
	}

	@RequestMapping(value = "/hotmeals/category", method = RequestMethod.POST)
	public @ResponseBody Category addCategory(@RequestBody Category c) {

		Category result;
		
		List<Category> categories = cr.findBySupplierIdAndName(c.getSupplierId(), c.getName());
		if (categories ==  null || categories.isEmpty())
			result = cr.save(c);
		else
			result = categories.get(0);
		
		return result;
	}

	@RequestMapping(value = "/hotmeals/suppliers/{supplierId}/categories", method = RequestMethod.GET)
	public @ResponseBody List<Category> fetchCategories(@PathVariable Long supplierId) {
		return cr.findBySupplierId(supplierId);
	}

	@RequestMapping(value = "/hotmeals/category/{supplierId}/{name}", method = RequestMethod.GET)
	public @ResponseBody Category getCategories(@PathVariable Long supplierId, @PathVariable String name, HttpServletResponse response) throws IOException {

		List<Category> categories = cr.findBySupplierIdAndName(supplierId, name);
    	if (categories != null && !categories.isEmpty())
    		return categories.get(0);
    	else {
    		response.sendError(404, "There's no category with given name.");
    		return null;
    	}
	}

}
