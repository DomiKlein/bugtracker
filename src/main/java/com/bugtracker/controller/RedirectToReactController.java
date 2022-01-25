package com.bugtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectToReactController {

	@RequestMapping(method = RequestMethod.GET, path = { "/tickets/**", "/" })
	public String forwardAngularPaths() {
		return "forward:/index.html";
	}
}
