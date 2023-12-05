package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

/**
 * ユーザーデータのライフサイクルを制御するインターフェース
 * @author yoshi
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	// emailと一致するユーザー情報を取得する
	public User findByEmail(String email);
}
