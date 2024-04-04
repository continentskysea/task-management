package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 * ユーザー管理初期画面表示
	 * @return ユーザー管理初期画面
	 */
	@GetMapping("/getUserHome")
	public String getUserHome() {
		return "users/userHome";
	}
	
	
	/**
	 * ユーザー一覧画面表示
	 * 
	 * @return ユーザー一覧画面
	 */
	@GetMapping("/getListUsers")	
	public String getListUsers(Model model) {
		List<User> listUsers = userService.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users/users";
	}
	
	/**
	 * ユーザー登録画面表示
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
	 * ログイン前ユーザー登録画面表示
	 * 
	 * @return ログイン前ユーザー登録画面
	 */
	@GetMapping("/getBeforeLoginCreateUser")
	public String getBeforeLoginCreateUser(Model model) {
		// 空のユーザーオブジェクト生成
		User user = new User();
		// ユーザーオブジェクトを画面に渡す
		model.addAttribute("user", user);
		return "users/beforeLoginUserForm";
	}
	

	/**
	 * ユーザー編集画面表示
	 * @param id ユーザーid
	 * @param model
	 * 
	 * @return　ユーザー編集画面
	 */
	@GetMapping("/getEditUser/{id}")
	public String getEditUser(@PathVariable(name = "id") Integer id, Model model) {
		if (id == null) {
			return "redirect:/getListUsers";
		}

		// ユーザーIDに紐づくユーザー情報を取得
		Optional<User> optionalUser = userService.get(id);

		// ユーザー情報の有無をチェック
		if (optionalUser.isEmpty()) {
			// エラーページ
			return "redirect:/getListUsers";
		} else {

			User user = optionalUser.get();
			model.addAttribute("user", user);
			return "users/userEdit";
		}
	}

	/**
	 * ユーザー情報登録
	 * 
	 * @param user ユーザー情報
	 * 
	 * @return ユーザー一覧画面
	 */	 
	@PostMapping("/registarUser") // URLの紐づけ
	public String registarUser(
			@Valid @ModelAttribute("user") User user, 
			BindingResult bindingResult, 
			RedirectAttributes ra,
			Model model,
			@RequestParam("createUserpageCheck") String createUserpageCheck
		) {
			System.out.println(user.getName());
			System.out.println(user.getEmail());
			System.out.println(user.getPassword());
	        // 入力に登録に不備があれば登録画面に戻る(Entityで設定したバリデーションを使ってチェックする)
			if (bindingResult.hasErrors()) {
				// エラーメッセージを追加するのは、バリデーションエラーが発生した場合のみ
				ra.addFlashAttribute("error_message", "入力内容に誤りがあります");
				return "redirect:/getCreateUser";
			}
			
			// ユーザー情報をDBに保存する
			userService.save(user);
			
			// 送信されたページを判定
			if (Integer.parseInt(createUserpageCheck) == 1) {
				// ログイン画面をリダイレクト表示
				return "redirect:/loginForm";
			}
			// ホーム画面をリダイレクト表示
			return "redirect:/home";
	}

	/**
	 * ユーザー削除削除
	 * 
	 * @param id ユーザーID
	 * @return ユーザー一覧画面
	 */
	@PostMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id) {
		userService.delete(id);
		return "redirect:/getListUsers";
	}
}
