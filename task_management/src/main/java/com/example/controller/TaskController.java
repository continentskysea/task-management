package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entity.Task;
/**
 * タスクコントローラクラス
 * @author yoshi
 *
 */
import com.example.service.TaskService;
@Controller
public class TaskController {
	
	private final TaskService taskService;
	
	// コンストラクタインジェクション
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	/**
	 * タスク管理初期画面表示
	 * 
	 * @return タスク管理初期画面
	 */
	@GetMapping("/getTask")
	public String getTask() {
		// タスク管理初期画面を表示
		return "tasks/taskHome";
	}
	
	/**
	 * タスク登録画面表示
	 * 
	 * @return タスク登録画面
	 */
	@GetMapping("/createTask") // URLの紐づけ
	public String createTask(Model model) {
		// 新規登録用に空のタスク登録情報を作成
		Task task = new Task();
		model.addAttribute("task", task);
		// タスク登録画面を表示
		return "tasks/taskForm";
	}
	
	
	/**
	 * タスク一覧画面表示
	 * 
	 * @return タスク登録画面
	 */
	@GetMapping("/listTasks") // URLの紐づけ
	public String listTasks(Model model) {
		// 全タスク情報の取得
		List<Task> listTasks = this.taskService.listAll();
		model.addAttribute("listTasks ", listTasks );
		// タスク登録画面を表示
		return "tasks/tasks";
	}
	
	
}
