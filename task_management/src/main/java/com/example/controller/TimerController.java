package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Task;
import com.example.entity.TimersSetting;
import com.example.service.TaskService;
import com.example.service.TimersSettingService;

/**
 * タイマーコントローラ
 * @author yoshi
 *
 */
@Controller
public class TimerController {
	
	// フィールドの追加
	private final TimersSettingService timersSettingService;
	private final TaskService taskService;
	
	// コンストラクタインジェクション
	@Autowired
	public TimerController(TimersSettingService timersSettingService, TaskService taskService) {
		this.timersSettingService = timersSettingService;
		this.taskService = taskService;
	}
	
	/**
	 * タイマー管理初期画面表示
	 * 
	 * @return タイマー管理初期画面
	 */
	@GetMapping("/getTimer")
	public String getTimer() {
		// タスク管理初期画面を表示
		return "timers/timerHome";
	}
	
	/**
	 * タイマー登録画面表示
	 * 
	 * @return タイマー登録画面
	 */
	@GetMapping("/getTimerSetting")
	public String getTimerSetting(Model model) {
		// 登録用の空のオブジェクトを生成
		TimersSetting timersSetting = new TimersSetting();
		model.addAttribute("timersSetting" ,timersSetting);
		return "timers/timerSetting";
	}
	
	/**
	 * タイマーの登録処理
	 * 
	 * @param timersSetting 集中時間
	 * @return タイマー管理初期画面
	 */
	@PostMapping("/save")
	public String save(TimersSetting timersSetting) {
		timersSettingService.save(timersSetting);
		return "redirect:/getTimer";
	}
	
	
	/**
	 * 集中タイマー画面表示
	 * 
	 * @return 集中タイマー画面
	 */
	
	@GetMapping("/getFocusTimer/{id}")
	public String getFocusTimer(@PathVariable(name = "id") Integer id, Model model) {
		if (id == null) {
			// エラーページ
			return "redirect:/listTasks";
		}
		
		// タスクIDに紐づくタスク情報を取得
		Optional<Task> optionalTask = taskService.get(id);
			
		if (optionalTask.isEmpty()) {
			// エラーページ
			return "redirect:/listTasks";
		} else {
			Task task = optionalTask.get();
			// タイマー情報を取得し画面に渡す
			TimersSetting latestTimersSetting = timersSettingService.getUsersFocusTimer(); 
			// タスク情報を画面に流す
			model.addAttribute("task", task);
			model.addAttribute("timersSetting", latestTimersSetting);
			return "timers/focusTimer";
		}
	}
}

