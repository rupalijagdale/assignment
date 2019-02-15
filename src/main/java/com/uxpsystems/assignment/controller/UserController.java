/**
 * 
 */
package com.uxpsystems.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserService;

@Controller
public class UserController {
	private UserService userService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService ps) {
		this.userService = ps;
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public String addUser(@ModelAttribute("user") User u, BindingResult bindingResult) {
		if (u.getId() == 0 || u == null) {
			// new user, add it
			this.userService.addUser(u);
		} else {
			// existing user, call update
			this.userService.updateUser(u);
		}

		return "redirect:/user";

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userService.listUsers());

		return "user";
	}

	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable("id") long id) {

		this.userService.removeUser(id);
		return "redirect:/user";
	}

	@RequestMapping("/getUserById/{id}")
	public String getUserById(@PathVariable("id") long id, Model model) {
		model.addAttribute("user", this.userService.getUserById(id));
		model.addAttribute("listUsers", this.userService.listUsers());
		return "user";
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.PUT, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public String updateUser(@ModelAttribute("user") User u, BindingResult bindingResult) {
		this.userService.updateUser(u);
		return "redirect:/user";

	}

}
