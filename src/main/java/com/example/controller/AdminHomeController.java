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
	 * @param loginUser ログインユーザー情報
	 * @param model ユーザーオブジェクトの属性を追加
	 * 
	 * @return 管理者ホーム画面
	 */
	@GetMapping("/getAdminHome")
	public String getAdminHome(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		// ログインユーザー情報からユーザーオブジェクトを生成する
		User currentUser = loginUser.getUser();
		// modelに追加し、遷移先へ渡す
		model.addAttribute("currentUser", currentUser);
		// 管理者初期画面へ遷移
		return "admin/adminHome";
	}
}
