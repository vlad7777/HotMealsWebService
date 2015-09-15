package com.ericpol.hotmeals.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ericpol.hotmeals.model.Supplier;
import com.ericpol.hotmeals.model.SuppliersRepository;
import com.ericpol.hotmeals.model.User;
import com.ericpol.hotmeals.model.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/hotmeals/users")
public class UsersController {

	@Autowired
	private UsersRepository ur;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody User addUser(@RequestBody User s) {
    	return ur.save(s);
    }
}
