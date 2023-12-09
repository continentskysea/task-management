package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * タスクコントローラクラス
 * @author yoshi
 *
 */
@Controller
public class TaskController {
	
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
	public String createTask() {
		// タスク登録画面を表示
		return "tasks/taskForm";
	}
	
	
}
