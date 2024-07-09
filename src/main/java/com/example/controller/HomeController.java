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
	 * @param loginUser　ログインユーザー情報
	 * @param model ログインユーザー情報
	 *  
	 * @return 初期画面
	 */
	@GetMapping("/home")
	public String getHome(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		// ログインユーザー情報からユーザーオブジェクトを生成する
		User currentUser = loginUser.getUser();
		// modelに追加し、遷移先へ渡す
		model.addAttribute("currentUser" ,currentUser);
		// 初期画面へ遷移
		return "home";
	}
}
