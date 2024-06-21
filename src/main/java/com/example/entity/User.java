package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * ユーザー情報のデータクラス
 * @author yoshi
 *
 */
@Entity
@Table(name = "users", schema = "pulic")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; // ユーザーID
	
	@NotBlank(message = "名前は必須です")
	@Size(min = 3, max = 12, message = "名前は3文字以上12文字以内で入力してください")
	@Pattern(regexp = "[\\p{IsHan}}]*", message = "名前は漢字のみ入力したください")
	@Column(name = "name")
	private String name; // ユーザー名
	
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "メールアドレスの形式が正しくありません")
	@Size(max = 50, message = "メールアドレスは50文字以内で入力してください")
	@Column(name = "email")
	private String email; // メールアドレス
	
	@NotBlank(message = "パスワードは必須です")
	@Pattern(regexp = "^(?=.*[a-z0-9]).*", message = "パスワードは小文字と数字のみを含む必要があります")
	@Column(name = "password")
	private String password; // -^パスワード
	
	@Column(name = "role")
	private String role; // 一般ユーザー/管理者ユーザー
	
	// アクセサメソッド
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
