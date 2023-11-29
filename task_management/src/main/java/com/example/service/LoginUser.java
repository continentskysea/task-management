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
	// Userオブジェクト(Entityクラス)
	private final User user;
	
	// コンストラクタ
	public LoginUser(User user) {
		this.user = user;
	}
	
	// アクセサメソッド
	
	/**
	 * ユーザー情報を取得する
	 * 
	 * @return ユーザー情報
	 */
	public  User  getUser() {
		return this.user;
	}
	
	
	/**
	 * ユーザー認証に使用するパスワードを取得する
	 * 
	 * @return Entityクラスのパスワードの情報
	 */
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}
	
	/**
	 * ユーザー認証に使用するユーザー名を取得する
	 * 
	 * @return Entityクラスのユーザー名の情報(メールアドレスからユーザー名を検索する)
	 */
	@Override
	public String getUserName() {
		return this.user.getEmail();
	}
	
	
	/**
	 * ユーザー権限の情報を取得する
	 * 
	 * @return 一般ユーザー/管理者ユーザーの権限情報
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.NO_AUTHORITIES;
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
