package com.example.service;

import java.util.List;
import java.util.Optional;

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
	private final UserService userService;
	
	// コンストラクタインジェクション
	@Autowired
	public TaskService(TaskRepository taskRepository, UserService userService) {
		this.taskRepository = taskRepository;
		this.userService = userService;
	}
		
	/**
	 * ユーザー別タスク情報全件取得
	 * 	
	 * @param userId タスクテーブルに紐づくユーザーID
	 * 
	 * @return ユーザー別タスク情報のリスト
	 */
	public List<Task> getTasksByUserId(Long userId) {

		// ユーザーIDに一致したタスク情報のリストを返す
		return taskRepository.findByUserId(userId);
	}
	
	/**
	 * タスク情報登録処理
	 * 
	 * @param task 保存したいタスク情報
	 * 
	 * @return 保存したタスク情報
	 */
	public Task save(Task task) {
		// 現在ログインしているユーザーのIDを取得する
		Long currentLoggedInUserId = userService.getCurrentUserId();
		// 取得してきたユーザーidをセットしDBに保存する
		task.setUserId(currentLoggedInUserId);
		return taskRepository.save(task);
	}
	
	/**
	 * IDに紐づくタスク情報取得処理
	 * 
	 * @param id タスクID
	 * 
	 * @return タスクIDに紐づくタスク情報
	 */
	public Optional<Task> get(Long id) {
		
		// タスクIDに一致したタスク情報を返す
		return taskRepository.findById(id);
	}
	
	/**
	 * IDに紐づくタスク情報削除処理
	 * 
	 * @param id タスクID
	 * 
	 */
	public void delete(Long id) {
		// タスクIDに一致したタスク情報をDBから削除する
		taskRepository.deleteById(id);
	}
}
