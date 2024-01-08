package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.service.TimerLogService;

/**
 * タイマーログコントローラクラス
 * @author yoshi
 *
 */
@Controller
public class TimerLogController {
	// タイマーログサービスのフィールドを追加
	private final TimerLogService timerLogService;
	
	// コンストラクタインジェクション
	@Autowired
	public TimerLogController(TimerLogService timerLogService) {
		this.timerLogService = timerLogService;
	}
}
