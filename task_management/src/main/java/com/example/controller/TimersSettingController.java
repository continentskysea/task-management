package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.TimersSetting;
import com.example.service.TimersSettingService;

/**
 * タイマーのデータを設定するためのコントローラ
 * @author yoshi
 *
 */
@Controller
public class TimersSettingController {
	
	// タイマー設定用サービスクラスのフィールドを追加
	private final TimersSettingService timersSettingService;
	
	// コンストラクタインジェクション
	public TimersSettingController(TimersSettingService timersSettingService) {
		this.timersSettingService = timersSettingService;
	}
	
	/**
	 * タイマーの登録処理
	 * 
	 * @param focusTime 集中時間
	 * @return タイマー管理初期画面
	 */
	@PostMapping("/save")
	public String save(TimersSetting focusTime) {
		timersSettingService.save(focusTime);
		return "redirect:/getTimer";
	}
}
