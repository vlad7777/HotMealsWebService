package com.ericpol.hotmeals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ericpol.hotmeals.model.Receipt;
import com.ericpol.hotmeals.model.ReceiptsRepository;

@RestController
public class ReceiptsController {

	@Autowired
	private ReceiptsRepository or;
	
	@RequestMapping(value = "/hotmeals/users/{userId}/orders", method = RequestMethod.GET)
	List<Receipt> fetchUserOrders(@PathVariable Long userId) {
		return or.findByUserId(userId);
	}
	
	@RequestMapping(value = "/hotmeals/orders", method = RequestMethod.POST)
	@ResponseBody Receipt addOrder(@RequestBody Receipt r) {
		
		return or.save(r);
	}
}
