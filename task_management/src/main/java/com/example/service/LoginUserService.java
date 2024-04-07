package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

/**
 * 送信したユーザー情報を元に、ユーザー情報テーブルデータを取得し
 * ログインしたユーザーの実体を生成するためのクラス
 * @author yoshi
 *
 */
@Service
public class LoginUserService implements UserDetailsService {
	
	private final UserRepository userRepository; // USERSテーブルの情報取得をするためのフィールド
	
	// コンストラクタインジェクション
	@Autowired
	public LoginUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * 取得したユーザーオブジェクトの情報をユーザーテーブルと照合し、
	 * 該当するユーザーが見つかれば、LoginUserを返す
	 * 
	 * @param email
	 * @throws UsernameNotFoundException 
	 * @return ログインしたユーザーの実体
	 */
	@Override
	public LoginUser loadUserByUsername(String email) throws UsernameNotFoundException {
		// ログインフォームから受け取った email情報を元にユーザー情報を取得
		User user = userRepository.findByEmail(email);
		
		// ユーザー情報が見つからない場合は、例外を発生する
		if (user == null) {
			throw new UsernameNotFoundException("該当するユーザーが見つかりません");
		}
		
		// ユーザーが見つかった場合は、UserDetailsを生成し返却する
		return new LoginUser(user);
	}
}
