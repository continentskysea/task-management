package com.example.controller;

import java.util.List;
import java.util.Objects;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	 * 
	 * @return ユーザー管理初期画面
	 */
	@GetMapping("/getUserHome")
	public String getUserHome() {
		// ユーザー管理初期画面へ遷移
		return "users/userHome";
	}
	
	
	/**
	 * ユーザー一覧画面表示
	 * 
	 * @param model ユーザー一覧とステータスメッセージの属性を追加
	 * @param request リダイレクト元から受け取ったセッションを受け取る
	 * 
	 * @return ユーザー一覧画面
	 */
	@GetMapping("/getListUsers")	
	public String getListUsers(Model model, HttpServletRequest request) {
		
		// DBに登録されているユーザー情報の全てを取得しmodelに渡す
		List<User> listUsers = userService.findAll();
		model.addAttribute("listUsers", listUsers);
		
		// リダイレクト元から送信されたセッションスコープのオブジェクトを取得
		HttpSession session = request.getSession();
		String messageType = (String) session.getAttribute("messageType");

		String message = null;
		if ("userRegistar".equals(messageType)) {
			// ユーザー登録完了メッセージをセットする
			message = (String) model.getAttribute("userRegistarMessage");
		} else if ("deleteUser".equals(messageType)) {
			// ユーザー削除完了メッセージをセットする
			message = (String) model.getAttribute("deleteUserMessage");
		}

		// メッセージタイプを削除する
		session.removeAttribute("messageType");
		
		// メッセージがある場合はModelに追加
		if (Objects.nonNull(message)) {
			model.addAttribute("flashMessage" ,message);
		}
		// ユーザー一覧画面へ遷移
		return "users/users";
	}
	
	/**
	 * ユーザー登録画面表示
	 * 
	 * @param model ユーザーオブジェクトの属性を追加
	 * 
	 * @return ユーザー登録画面
	 */
	@GetMapping("/getCreateUser")
	public String getCreateUser(Model model) {
		// 空のユーザーオブジェクト生成
		User user = new User();
		// ユーザーオブジェクトを画面に渡す
		model.addAttribute("user", user);
		// ユーザー登録画面へ遷移
		return "users/userForm";
	}
	
	
	/**
	 * ログイン前ユーザー登録画面表示
	 * 
	 * @param model ユーザーオブジェクトの属性を追加
	 * 
	 * @return ログイン前ユーザー登録画面
	 */
	@GetMapping("/getBeforeLoginCreateUser")
	public String getBeforeLoginCreateUser(Model model) {
		// 空のユーザーオブジェクト生成
		User user = new User();
		// modelに追加し、遷移先へ渡す
		model.addAttribute("user", user);
		// 登録フォームヘ遷移する(ログイン前新規登録用)
		return "users/beforeLoginUserForm";
	}
	

	/**
	 * ユーザー編集画面表示
	 * 
	 * @param id ユーザーid
	 * @param model ユーザーオブジェクトの属性を追加
	 * 
	 * @return　ユーザー編集画面
	 */
	@GetMapping("/getEditUser/{id}")
	public String getEditUser(@PathVariable(name = "id") Long id, Model model) {

		//ユーザーIDのエラ―チェック
		if (id == null) {
			// ユーザー一覧へリダイレクトする
			return "redirect:/getListUsers";
		}

		// ユーザーIDに紐づくユーザー情報を取得
		Optional<User> optionalUser = userService.get(id);

		// ユーザー情報の有無をチェック
		if (optionalUser.isEmpty()) {
			// ユーザー一覧へリダイレクトする
			return "redirect:/getListUsers";
		} else {
			// 編集対処のユーザーを取得しモデルに追加する
			User user = optionalUser.get();
			// modelに保存し、遷移先へ渡す
			model.addAttribute("user", user);
			// ユーザー編集画面へ遷移
			return "users/userEdit";
		}
	}

	/**
	 * ユーザー情報登録
	 * 
	 * @param user ユーザー情報
	 * @param bindingResult 
	 * @param createUserPageCheck リクエストの送信がされたページの判定値
	 * @param ra リダイレクト先のステータスメッセージ
	 * @param request リダイレクト先へ渡すセッション
	 * 
	 * @return ユーザー一覧画面
	 */	 
	@PostMapping("/registarUser") // URLの紐づけ
	public String registarUser(
			@Valid @ModelAttribute("user") User user, 
			BindingResult bindingResult, 
			@RequestParam("createUserpageCheck") String createUserpageCheck,
			RedirectAttributes ra,
			HttpServletRequest request

		) {
	        // 入力に登録に不備があれば登録画面に戻る(Entityで設定したバリデーションを使ってチェックする)
			if (bindingResult.hasErrors()) {
				// エラーメッセージを追加するのは、バリデーションエラーが発生した場合のみ
				ra.addFlashAttribute("error_message", "入力内容に誤りがあります");
				return "redirect:/getCreateUser";
			}
			// 登録されたロールをオブジェクト化
			String role = user.getRole();
			// 送信されたロールを判定し登録する
			if (role == null || role.equals("") ||  role.equals("GENERAL")) {
				user.setRole("GENERAL");
			} else if (role.equals("管理者")) {
				user.setRole("ADMIN");
			}
			
			// ユーザー情報をDBに保存する
			userService.save(user);

			// FlashScopeに保存する
			ra.addFlashAttribute("userRegistarMessage", "ユーザーを登録しました");
			// セッションスコープにメッセージの種類を保存
			HttpSession session = request.getSession();
			session.setAttribute("messageType", "userRegistar");

			
			// 送信されたページを判定
			if (Long.parseLong(createUserpageCheck) == 1) {
				// ログイン画面をリダイレクト表示
				return "redirect:/loginForm";
			}

			// ユーザー一覧画面をリダイレクト表示
			return "redirect:/getListUsers";
	}

	/**
	 * ユーザー削除削除
	 * 
	 * @param id ユーザーID
	 * @param ra リダイレクト先のステータスメッセージ
	 * @param request リダイレクト先へ渡すセッション
	 * 
	 * @return ユーザー一覧画面
	 */
	@PostMapping("/deleteUser/{id}")
	public String deleteUser(
		@PathVariable(name = "id") Long id,
		RedirectAttributes ra,
		HttpServletRequest request		
	) {
		// ユーザー情報を削除
		userService.delete(id);

		// FlashScopeに保存する
		ra.addFlashAttribute("deleteUserMessage", "ユーザーを削除しました");
		// セッションスコープにメッセージの種類を保存
		HttpSession session = request.getSession();
		session.setAttribute("messageType", "deleteUser");

		// ユーザー一覧画面をリダイレクトする
		return "redirect:/getListUsers";
	}
}
