package com.example.controller;

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
		// タスクIDに紐づくタスク情報を取得
		Task task = taskService.get(id);
			
		
		if (task == null) {
			// エラーページ
			return "redirect:/listTask";
		} else {
			// タスク情報を画面に流す
			model.addAttribute("task", task);
			return "timers/focusTimer";
		}
	}
}
