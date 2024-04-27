package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {
	
	/**
	 * 管理者ホーム画面を呼び出す
	 * 
	 * @return 管理者ホーム画面
	 */
	@GetMapping("/getAdminHome")
	public String getAdminHome() {
		return "admin/adminHome";
	}
}
