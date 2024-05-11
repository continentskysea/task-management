package com.example.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.User;

/**
 * ログイン中のユーザーデータを管理するクラス(UserDetailsインターフェースを実装する)
 * @author yoshi
 *
 */
public class LoginUser implements UserDetails{
	
	private final User user; // ユーザー情報
	private final String homePage; // ユーザー別ページ遷移先
	
	// コンストラクタ
	public LoginUser(User user, String homePage) {
		this.user = user;
		this.homePage = homePage;
	}
	
	// アクセサメソッド
	
	/**
	 * ユーザー情報を取得する
	 * 
	 * @return ユーザー情報
	 */
	public  User  getUser() {
		return user;
	}
	
	
	/**
	 * ユーザー認証に使用するパスワードを取得する
	 * 
	 * @return Entityクラスのパスワードの情報
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	/**
	 * ユーザー認証に使用するユーザー名を取得する
	 * 
	 * @return Entityクラスのユーザー名の情報(メールアドレスからユーザー名を検索する)
	 */
	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	
	/**
	 * ホーム画面情報を取得する
	 * 
	 * @return ホーム画面
	 */
	public String getHomePage() {
		return homePage;
	}
	

	/**
	 * ログインユーザーの権限情報を取得する
	 * 
	 * @return 一般ユーザー/管理者ユーザーの権限情報
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {

		String role = user.getRole();
		
		System.out.println(role);
		
		if (role != null && role.equals("ADMIN")) {
			return AuthorityUtils.createAuthorityList("ADMIN", "GENERAL");
		}
		return AuthorityUtils.createAuthorityList("GENERAL");
	}
	
	/**
	 * アカウントの有効期限の状態を判定する
	 * 
	 * @return true / false
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/**
	 * アカウントの凍結状態を判定する
	 * 
	 * @return true / false
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/**
	 * ユーザーの資格情報(パスワードとする)が有効期限の状態を判定する
	 * 
	 * @return true / false
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/**
	 * 有効なユーザーか判定する
	 * 
	 * @return true / false
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
