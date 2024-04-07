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
	
	/**
	 * ログイン画面表示
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/loginForm")
	public String getLogin() {
		// ログイン画面を表示
		return "loginForm";
	}

}
