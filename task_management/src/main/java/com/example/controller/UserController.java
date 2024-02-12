package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.User;
import com.example.service.UserService;

/**
 * ユーザーコントローラー
 * @author yoshi
 *
 */
@Controller
public class UserController {
	// フィールドの追加
	private final UserService userService;
	
	// コンストラクタインジェクション
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService; 
	}
	
	/**
	 * ユーザー登録画面の表示
	 * 
	 * @return ユーザー登録画面
	 */
	@GetMapping("/getCreateUser")
	public String getCreateUser(Model model) {
		// 空のユーザーオブジェクト生成
		User user = new User();
		// ユーザーオブジェクトを画面に渡す
		model.addAttribute("user", user);
		return "users/userForm";
	}
	
	/**
	 * ユーザー登録処理
	 * 
	 * @param user ユーザー情報
	 * 
	 * @return ホーム画面
	 */	 
	@PostMapping("/createUser") // URLの紐づけ
	public String createUser(@ModelAttribute("user") User user) {
		// ユーザー情報をDBに保存する
		userService.save(user);
		// ホーム画面をリダイレクト表示
		return "redirect:/home";
	}
}
