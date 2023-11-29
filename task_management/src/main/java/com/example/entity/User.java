package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * ユーザー情報のデータクラス
 * @author yoshi
 *
 */
@Entity
@Table(name = "USERS")
public class User {
	@Id
	@SequenceGenerator(name = "USER_ID_GENERATOR", sequenceName = "USER_IT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id; // ユーザーID
	
	@Column(name = "NAME")
	private String name; // ユーザー名
	
	@Column(name = "EMAIL")
	private String email; // メールアドレス
	
	@Column(name = "PASSWORD")
	private String password; // パスワード
	
	// アクセサメソッド
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
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
	
	p	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
