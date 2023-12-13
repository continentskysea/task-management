package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Task;
import com.example.repository.TaskRepository;

/**
 * タスクサービスクラス
 * @author yoshi
 *
 */
@Service
public class TaskService {
	private final TaskRepository taskRepository;
	
	
	// コンストラクタインジェクション
	@Autowired
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	/**
	 * タスク情報全件取得
	 * 
	 * @return タスク情報のリスト
	 */
	public List<Task> listAll() {
		return this.taskRepository.findAll();
	}
	
	/**
	 * タスク情報登録処理
	 * 
	 * @param task 保存したいタスク情報
	 * @return 保存したタスク情報
	 */
	public Task save(Task task) {
		return taskRepository.save(task);
	}
}
