package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

/**
 * ユーザー情報のサービスクラス
 * @author yoshi
 *
 */
@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
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
}
