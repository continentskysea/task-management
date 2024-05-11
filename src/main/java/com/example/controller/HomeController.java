package com.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entity.User;
import com.example.service.LoginUser;

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
	public String getHome(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		User currentUser = loginUser.getUser();
		model.addAttribute("currentUser" ,currentUser);
		// ログイン後の初期画面を表示
		return "home";
	}
}
