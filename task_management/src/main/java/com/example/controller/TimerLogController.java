package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.service.TimerLogService;
import com.example.service.TimersSettingService;

/**
 * タイマーログコントローラクラス
 * @author yoshi
 *
 */
@Controller
public class TimerLogController {
	// タイマーログサービスのフィールドを追加
	private final TimerLogService timerLogService;
	private final TimersSettingService timersSettingService;
	
	// コンストラクタインジェクション
	@Autowired
	public TimerLogController(TimerLogService timerLogService, TimersSettingService timersSettingService) {
		this.timerLogService = timerLogService;
		this.timersSettingService = timersSettingService;
	}
	

}
