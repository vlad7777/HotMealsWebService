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
import com.ericpol.hotmeals.model.Supplier;
import com.ericpol.hotmeals.model.SuppliersRepository;
import com.ericpol.hotmeals.model.User;
import com.ericpol.hotmeals.model.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UsersController {

	@Autowired
	private UsersRepository ur;

    @RequestMapping(value = "/hotmeals/users", method = RequestMethod.POST)
    @ResponseBody User addUser(@RequestBody User s) {

    	return ur.save(s);
    }

    @RequestMapping(value = "/hotmeals/users", method = RequestMethod.GET)
    @ResponseBody List<User> fetchUsers() {

    	return ur.findAll();
    }

   	@RequestMapping(value = "/hotmeals/users/{login}", method = RequestMethod.GET)
    @ResponseBody User getUser(@PathVariable String login, HttpServletResponse response) throws IOException {

    	List<User> users = ur.findByLogin(login);
    	if (users != null && !users.isEmpty())
    		return users.get(0);
    	else {
    		response.sendError(404, "There's no user with given login.");
    		return null;
    	}
    }
}
