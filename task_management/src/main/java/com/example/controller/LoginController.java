package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインコントローラクラス
 * @author yoshi
 *
 */
@Controller
public class LoginController {
	
	
	@GetMapping("/loginForm")
	public String getLogin() {
		return "loginForm";
	}
}
