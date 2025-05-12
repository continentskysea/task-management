package com.example.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import com.example.domain.value_object.role.Role;
import com.example.domain.value_object.user.UserId;
import com.example.domain.value_object.user.name.UserName;
import com.example.domain.value_object.user.password.Password;
import com.example.domain.value_object.user.email.Email;



/**
 * ユーザー情報集約エンティティ
 * @author yoshi
 *
 */
@Entity
@Table(name = "users", schema = "verification")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private final UserId id;

	@Column(name = "name")
	private final UserName name;

	@Column(name = "email")
	private final Email email;

	@Column(name = "password")
	private final Password password;

	@Column(name = "role")
	private final Role role;


	public User
		(
			UserId id,
			UserName name,
			Email email,
			Password password,
			Role role
		) {
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
			this.role = role;
	}


	public UserId getUserId() {
		return this.id;
	}
	public UserName getUserName() {
		return this.name;
	}
	public Email getEmail() {
		return this.email;
	}
	public Password getPassword() {
		return this.password;
	}
	public Role getRole() {
		return this.role;
	}

	public User getUser() {
		return new User(id, name, email, password, role);
	}

}
