package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Task;

/**
 * タスクリポジトリ―インターフェース
 * @author yoshi
 *
 */
public interface TaskRepository extends JpaRepository<Task, Integer>{
	// ユーザーidを検索する処理
	List<Task> findByUserId(Integer userId);
}
