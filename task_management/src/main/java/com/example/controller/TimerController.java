package com.example.controller;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Task;
import com.example.entity.TimersSetting;
import com.example.service.TaskService;
import com.example.service.TimersSettingService;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;



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
	private final UserService userService;
	
	// コンストラクタインジェクション
	@Autowired
	public TimerController(
			TimersSettingService timersSettingService,
			TaskService taskService,
			UserService userService
			) {
		this.timersSettingService = timersSettingService;
		this.taskService = taskService;
		this.userService = userService;
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
	 * タイマー一覧表示
	 * @param model
	 * @return
	 */
	@GetMapping("/getTimerlist")
	public String getTimerlist(Model model) {
		// ログインしているユーザーのIDを取得する
		Long currentUserId = userService.getCurrentUserId();
		List<TimersSetting> timersList = timersSettingService.getTimersByUserId(currentUserId);
		model.addAttribute("timersList", timersList);
		return "timers/timers";
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
	 * @param timersSetting 集中時間と休憩時間
	 * @return タスク一覧画面
	 */
	@PostMapping("/save")
	public String save(@ModelAttribute("timersSetting") TimersSetting timersSetting, @RequestParam("formSendCheckPageValue") String formSendCheckPageValue) {
		timersSettingService.save(timersSetting);
		if (Long.parseLong(formSendCheckPageValue) == 1) {
			return "redirect:/getListTasks";
		} 
		return "redirect:/getTimerlist";
	}
	
	
	/**
	 * 集中タイマー画面表示
	 * 
	 * @return 集中タイマー画面
	 */
	
	@GetMapping("/getFocusTimer/{id}")
	public String getFocusTimer(@PathVariable(name = "id") Long id, Model model) {
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
	public String getBreakTimer(@PathVariable(name = "id") Long id, Model model) {
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
	 * タイマー編集画面表示
	 */
	@GetMapping("/getEditTimer/{id}")
	public String getEditTimer(@PathVariable(name = "id") Long id, Model model) {
		if (id == null) {
			return "redirect:/getTimerlist";
		}

		// タスクIDに紐づくタスク情報を取得
		Optional<TimersSetting> optinalTimer = timersSettingService.get(id);

		if (optinalTimer.isEmpty()) {
			// エラーページ
			return "redirect:/getTimerlist";
		} else {
			TimersSetting timersSetting = optinalTimer.get();
			// 取得したタスク情報を画面に渡す
			model.addAttribute("timersSetting" ,timersSetting);
			return "timers/timerEdit";		
		}
	}

	/**
	 * タイマー削除処理
	 * 
	 * @param id タイマーid
	 */
	@PostMapping("/deleteTimer/{id}")
	public String deleteTimer(@PathVariable(name = "id") Long id) {
		timersSettingService.delete(id);
		return "redirect:/getTimerlist";
	}
	
	
}

