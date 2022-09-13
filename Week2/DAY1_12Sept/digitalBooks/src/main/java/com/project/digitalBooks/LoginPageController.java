package com.project.digitalBooks;

import org.springframework.stereotype.Controller;

@Controller
public class LoginPageController {
	@GetMapping("/login")
String login()
{
		return "Welcome";
}
}
