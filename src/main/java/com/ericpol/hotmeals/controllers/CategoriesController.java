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
@RequestMapping("/hotmeals/")
public class CategoriesController {

	@Autowired
	private CategoriesRepository cr;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	List<Category> fetchCat() {
		return cr.findAll();
	}

	@RequestMapping(value = "/suppliers/{supplierId}/categories", method = RequestMethod.GET)
	List<Category> fetchCat(@PathVariable Long supplierId) {
		return cr.findBySupplierId(supplierId);
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseBody Category addCat(@RequestBody Category c) {
		long supId = c.getSupplierId();
		String nameCat = c.getName();
		List<Category> crList = cr.findBySupplierId(supId);
		boolean isExist = false;
		long catId = 0;
		for(Category cc: crList){
			if(cc.getName().equals(nameCat)) {
					isExist = true;
					catId = cc.getId();
			}
		}
		if(cr.exists(supId) && isExist)
			return cr.save(new Category(supId, catId, nameCat));
		else
			return cr.save(c);
	}
}
