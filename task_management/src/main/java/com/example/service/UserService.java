package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.example.entity.User;
import com.example.repository.UserRepository;

import jakarta.validation.Validator;

/**
 * ユーザー情報のサービスクラス
 * @author yoshi
 *
 */
@Service
public class UserService implements Validator{
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	/**
	 * ユーザー情報登録処理
	 * 
	 * @param 保存したいユーザー情報
	 * @return 保存したユーザー情報
	 * 
	 */
	public User save(User user) {
		 return userRepository.save(user);
	}
	
	/**
	 * ログイン中のユーザーのemailを比較し、一致したユーザーのIDを取得する
	 * @return user.getId() ユーザーID
	 */
	public Integer getCurrentUserId() {
		// ログインしているユーザーの email を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserEmail = authentication.getName(); // ユーザーの email が格納されている可能性がある
		
		// ユーザーの email と一致するユーザーのデータをデータベースから検索
		User user = userRepository.findByEmail(loggedInUserEmail);
		
		// ユーザーが見つかった場合はそのユーザーの ID を返す
		if (user != null) {
			return user.getId();
		}
		
		// ユーザーが見つからない場合は null を返す
		return null;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		// 名前のバリデーション
		if (!user.getName().matches("[\\p{IsHan}\\p{IsDigit}]{1,12}")) {
			errors.rejectValue("name", "name.invalid", "名前は1文字以上12文字以内の漢字または数字で入力してください");
		}
		
		// メールアドレスのバリデーション
		if (!user.getEmail().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
			errors.rejectValue("email", "email.invalid", "メールアドレスの形式が正しくありません");
		}
		
		// パスワードのバリデーション
		if (!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]*$")) {
			errors.rejectValue("password", "password.invalid", "パスワードは英数字の大文字小文字を含む必要があります");
		}
	}
	
}
