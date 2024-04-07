package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 初期画面のコントローラクラス
 * @author yoshi
 *
 */
@Controller
public class HomeController {
	
	/**
	 * 初期画面を表示
	 * 
	 * @return 初期画面
	 */
	@GetMapping("/home")
	public String getHome() {
		
		// ログイン後の初期画面を表示
		return "home";
	}
}
