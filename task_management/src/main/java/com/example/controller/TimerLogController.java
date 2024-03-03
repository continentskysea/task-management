package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.TimerLog;
import com.example.entity.TimersSetting;
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
	
	/**
	 * タイマー起動
	 * 
	 * @return 集中タイマー画面
	 */
	@PostMapping("/startTimer")
	public String startTimer(
			@RequestParam("focusTime") Integer focusTime, 
			@RequestParam("timerType") String timerType,  
			@RequestParam("settingId") Integer settingId,
			RedirectAttributes ra,
			Model model) {
		// ログ登録用にオブジェクトを生成する
		TimerLog timerLog = new TimerLog();
		
		// 開始時間を設定する
		timerLog.setStartTime(focusTime);
		// 集中タイマーか休憩タイマーを判定する
		if (timerType == "1") {
			timerLog.setTimerType("集中");
		} else if (timerType == "2") {
			timerLog.setTimerType("休憩");
		}

		// TIMERS_SETTINGSテーブルから対応する設定IDを取得
		TimersSetting timersSetting = timersSettingService.findById(settingId);
		
		// タイマー設定オブジェクトのnullチェック
		if (timersSetting == null) {
			ra.addFlashAttribute("error_message", "タイマー設定IDに対する設定が見つかりません");
			return "redirect:/focusTimer";
		}
		
		// タイマー設定IDを設定
		timerLog.setSettingId(timersSetting.getId());
		
		// ログに登録する
		timerLogService.save(timerLog);
		
		return "redirect:/focusTimer";
	}
}
