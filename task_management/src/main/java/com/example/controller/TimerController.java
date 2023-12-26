package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * タイマーコントローラ
 * @author yoshi
 *
 */
@Controller
public class TimerController {
	
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
}
