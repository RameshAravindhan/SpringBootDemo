package com.SpringBoot.Demo.FirstProject.Services;

import org.springframework.stereotype.Component;

@Component
public class Services {

	public boolean validatePassword(String un, String pw) {

		return un.equalsIgnoreCase("hi") && pw.equalsIgnoreCase("hello");
	}

}
