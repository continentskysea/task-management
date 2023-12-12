package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Task;

/**
 * タスクリポジトリ―インターフェース
 * @author yoshi
 *
 */
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
