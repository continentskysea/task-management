package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.TimersSetting;
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
	
	// コンストラクタインジェクション
	@Autowired
	public TimerController(TimersSettingService timersSettingService) {
		this.timersSettingService = timersSettingService;
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
}
