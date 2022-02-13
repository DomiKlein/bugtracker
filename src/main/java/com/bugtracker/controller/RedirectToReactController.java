package com.bugtracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectToReactController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedirectToReactController.class);

	@RequestMapping(path = "{_:^(?!resources/index\\.html|api).*$}")
	public String forward() {
		LOGGER.info("Forwarded request");
		return "forward:/resources/index.html";
	}
}
