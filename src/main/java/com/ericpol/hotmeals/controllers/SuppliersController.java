package com.ericpol.hotmeals.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ericpol.hotmeals.model.Supplier;
import com.ericpol.hotmeals.model.SuppliersRepository;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SuppliersController {

    //private List<Supplier> suppliers = null;
    //private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private SuppliersRepository sr;
	
    @RequestMapping(value="/hotmeals/suppliers", method = RequestMethod.GET)
    List<Supplier> fetchSuppliers() {
        return sr.findAll();
    }
    
    @RequestMapping(value="/hotmeals/suppliers", method = RequestMethod.POST)
    @ResponseBody Supplier addSupplier(@RequestBody Supplier s) {
    	Supplier saved = sr.save(s);
    	return saved;
    }

    @RequestMapping(value="/hotmeals/suppliers/{name}", method = RequestMethod.GET)
    @ResponseBody Supplier getSupplier(@PathVariable String name, HttpServletResponse response) throws IOException {

    	List<Supplier> suppliers = sr.findByName(name);
    	if (suppliers != null && !suppliers.isEmpty())
    		return suppliers.get(0);
    	else {
    		response.sendError(404, "There's no supplier with given name.");
    		return null;
    	}
    }
/*
    @RequestMapping(value = "/{supplierId}/dishes", method = RequestMethod.GET)
    public List<Dish> fetchDishes(@PathVariable Long supplierId) {
    	Supplier supplier = sr.findOne(supplierId);
        if (supplier != null) {
                return supplier.getDishes();
        }
        return new ArrayList<Dish>();
    }

    @RequestMapping(value = "/{supplierId}/dishes/{date}", method = RequestMethod.GET)
    public List<Dish> fetchDishes(@PathVariable Long supplierId, @PathVariable String date) {
    	Supplier supplier = sr.findOne(supplierId);
        if (supplier != null) {
                return supplier.selectDishes(date);
        }
        return new ArrayList<Dish>();
    }
*/
    private void syncWithDatabase() {
        /*
        suppliers.add(new Supplier("Bronte"));
        suppliers.add(new Supplier("KFC"));
        suppliers.add(new Supplier("doorknob"));
        suppliers.add(new Supplier("switch"));
        suppliers.add(new Supplier("delicious"));
        suppliers.add(new Supplier("Gourmet"));
         */
    }
}