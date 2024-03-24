package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.User;
import com.example.service.UserService;

import jakarta.validation.Valid;

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
	public String createUser(
		@Valid @ModelAttribute("user") User user, 
		BindingResult bindingResult, 
		RedirectAttributes ra) {
			// 入力に登録に不備があれば登録画面に戻る
			if (bindingResult.hasErrors()) {
				ra.addFlashAttribute("error_message", "入力内容に誤りがあります");
				return "redirect:/getCreateUser";
			}
			
			// ユーザー情報をDBに保存する
			userService.save(user);
			// 登録が成功すればホーム画面をリダイレクト表示
			return "redirect:/home";
	}
}
