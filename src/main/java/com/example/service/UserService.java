package com.example.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.entity.User;
import com.example.repository.UserRepository;




/**
 * ユーザー情報のサービスクラス
 * @author yoshi
 *
 */
@Service
public class UserService implements Validator{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * ユーザー情報一覧取得処理
	 * 
	 * @return ユーザー一覧情報
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	
	/**
	 * ユーザー情報登録処理
	 * 
	 * @param 保存したいユーザー情報
	 * @return 保存したユーザー情報
	 * 
	 */
	public User save(User user) {		
		// パスワードをハッシュ化
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}


	/**
	 * idに紐づくユーザー情報取得処理
	 * @param id ユーザーID
	 * @return ユーザー情報
	 */
	public Optional<User> get(Long id) {
		return userRepository.findById(id);
	}
	
	
	/**
	 * ログイン中のユーザーのemailを比較し、一致したユーザーのIDを取得する
	 * @return user.getId() ユーザーID
	 */
	public Long getCurrentUserId() {
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
		return User.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		// 名前のバリデーション
		if (!user.getName().matches("[\\p{IsHan}\\p{IsDigit}]{3,12}")) {
			errors.rejectValue("name", "name.invalid", "名前は3文字以上12文字以内の漢字で入力してください");
		}
		
		// メールアドレスのバリデーション
		if (!user.getEmail().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
			errors.rejectValue("email", "email.invalid", "メールアドレスの形式が正しくありません");
		}
		
	    // パスワードのバリデーション
		if (!user.getPassword().matches("^(?=.*[a-z0-9]).*$")) {
			errors.rejectValue("password", "password.invalid", "パスワードは小文字と数字のみを含む必要があります");
		}
	}


	/**
	 * ID情報に紐づくユーザー情報削除処理
	 * 
	 * @param id ユーザーID
	 */
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
}
