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

import com.ericpol.hotmeals.model.Dish;
import com.ericpol.hotmeals.model.DishesRepository;
import com.ericpol.hotmeals.model.Supplier;
import com.ericpol.hotmeals.model.SuppliersRepository;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class DishesController {

	@Autowired
	private DishesRepository dr;
	
	@Autowired
	private SuppliersRepository sr;
	
	@RequestMapping(value = "/hotmeals/dishes", method = RequestMethod.POST)
	@ResponseBody Dish addDish(@RequestBody Dish d) {

		return dr.save(d);
	}

	@RequestMapping(value = "/hotmeals/suppliers/{supplierId}/dishes", method = RequestMethod.GET)
	public List<Dish> fetchDishes(@PathVariable Long supplierId) {
		Supplier supplier = sr.findOne(supplierId);
		if (supplier != null) {
			return dr.findBySupplierId(supplierId);
		}
		return null;
	}

	@RequestMapping(value = "/hotmeals/suppliers/{supplierId}/dishes/{date}", method = RequestMethod.GET)
	public List<Dish> fetchDishes(@PathVariable Long supplierId, @PathVariable String date) {
		Supplier supplier = sr.findOne(supplierId);
		if (supplier != null) {
			return supplier.selectDishes(date);
		}
		return dr.findAll();
	}
	
    @RequestMapping(value="/hotmeals/dishes/{supplierId}/{categoryId}/{name}", method = RequestMethod.GET)
    @ResponseBody Dish getDish(@PathVariable Long supplierId, @PathVariable Long categoryId, @PathVariable String name, HttpServletResponse response) throws IOException {

    	List<Dish> dishes = dr.findBySupplierIdAndCategoryIdAndName(supplierId, categoryId, name);
    	if (dishes != null && !dishes.isEmpty())
    		return dishes.get(0);
    	else {
    		response.sendError(404, "There's no supplier with given name.");
    		return null;
    	}
    }
	
}
