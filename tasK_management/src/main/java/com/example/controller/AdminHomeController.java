package com.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entity.User;
import com.example.service.LoginUser;

@Controller
public class AdminHomeController {
	
	/**
	 * 管理者ホーム画面を呼び出す
	 * 
	 * @return 管理者ホーム画面
	 */
	@GetMapping("/getAdminHome")
	public String getAdminHome(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		User currentUser = loginUser.getUser();
		model.addAttribute("currentUser", currentUser);
		return "admin/adminHome";
	}
}
