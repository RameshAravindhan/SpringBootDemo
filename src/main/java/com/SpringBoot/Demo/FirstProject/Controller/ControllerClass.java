package com.SpringBoot.Demo.FirstProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.SpringBoot.Demo.FirstProject.Services.Services;

@Controller
@SessionAttributes("name1")
public class ControllerClass {
	@Autowired
	Services service;

//	@RequestMapping(value = "/", method = RequestMethod.GET)
////	@ResponseBody
//	public String displaylogin(ModelMap model) {
//
//		return "login";
//	}
//
//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	public String displaylogoff(ModelMap model, @RequestParam String name, @RequestParam String password) {
//
//		boolean b = service.validatePassword(name, password);
//		if (b) {
//			model.put("name1", "hi");
//			return "welcome";
//		}
//		else
//		return "login";
//
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		model.put("name1",  getLoggedInUserName() );
		return "welcome";
	}
	
	
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	
	
	


}
