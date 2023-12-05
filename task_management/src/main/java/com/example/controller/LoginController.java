package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログインコントローラ
 * @author yoshi
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * ログイン画面表示
	 * 
	 * @return ログイン画面
	 */
	@GetMapping
	public String login() {
		// ログイン画面を表示
		return "login";
	}
}
