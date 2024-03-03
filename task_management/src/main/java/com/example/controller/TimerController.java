package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Task;
import com.example.entity.TimerLog;
import com.example.entity.TimersSetting;
import com.example.service.TaskService;
import com.example.service.TimerLogService;
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
	// タイマーログサービスのフィールドを追加
	private final TimerLogService timerLogService;
	
	// コンストラクタインジェクション
	@Autowired
	public TimerController(
			TimersSettingService timersSettingService,
			TaskService taskService
			,TimerLogService timerLogService
			) {
		this.timersSettingService = timersSettingService;
		this.taskService = taskService;
		this.timerLogService = timerLogService;
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
	
	/**
	 * 休憩タイマー画面表示
	 * 
	 * @return 休憩タイマー画面
	 */
	@GetMapping("/getBreakTimer/{id}")
	public String getBreakTimer(@PathVariable(name = "id") Integer id, Model model) {
		// エラーチェック
		if (id == null) {
			return "redirect:/listTasks";
		}
		// タスクIDに紐づくタスク情報を取得
		Optional<Task> optinalTask = taskService.get(id);
		
		if (optinalTask.isEmpty()) {
			// エラーページ
			return "redirect:/listTasks";
		} else {
			Task task = optinalTask.get();
			// タイマー情報を取得する
			TimersSetting timersSetting = timersSettingService.getUsersFocusTimer();
			// タスク情報を画面に渡す
			model.addAttribute("task", task);
			// タイマー情報を画面に渡す
			model.addAttribute("timersSetting", timersSetting);
			return "timers/breakTimer";
		}
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

