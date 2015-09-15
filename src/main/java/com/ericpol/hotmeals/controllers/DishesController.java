package com.ericpol.hotmeals.controllers;

import java.util.List;

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
}
