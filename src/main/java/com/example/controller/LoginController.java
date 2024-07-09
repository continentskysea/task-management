package com.example.controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
/**
 * ログインコントローラクラス
 * @author yoshi
 *
 */
@Controller
public class LoginController {
	
	/**
	 * ログイン画面表示
	 * 
	 * @param model ステータスメッセージの属性を追加
	 * @param request リダイレクト元から受け取ったセッションを受け取る
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/loginForm")
	public String getLogin(Model model, HttpServletRequest request) {

		// リダイレクト元から送信されたセッションスコープのオブジェクトを取得
		HttpSession session = request.getSession();
		String messageType = (String) session.getAttribute("messageType");

		String message = null;
		if ("userRegistar".equals(messageType)) {
			// ユーザー登録完了メッセージをセットする
			message = (String) model.getAttribute("userRegistarMessage");
		}

		// メッセージタイプを削除する
		session.removeAttribute("messageType");
		
		// メッセージがある場合はModelに追加
		if (Objects.nonNull(message)) {
			model.addAttribute("flashMessage" ,message);
		}
		
		// ログイン画面を表示
		return "loginForm";
	}

}
