package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Task;
import com.example.entity.TimersSetting;
import com.example.entity.User;
import com.example.service.LoginUser;
import com.example.service.TaskService;
import com.example.service.TimersSettingService;
import com.example.service.UserService;



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
	public String getTimer(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		User currentUser = loginUser.getUser();
		model.addAttribute("currentUser" , currentUser);
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
		System.out.println(timersSetting.getFocusTime());
		System.out.println(timersSetting.getBreakTime());
		System.out.println(timersSetting.getUserId());
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
	public String getFocusTimer(@PathVariable(name = "id") Long id, Model model, RedirectAttributes ra) {
		if (id == null) {
			// エラーページ
			return "redirect:/getListTasks";
		}
		
		// タスクIDに紐づくタスク情報を取得
		Optional<Task> optionalTask = taskService.get(id);
			
		if (optionalTask.isEmpty()) {
			// エラーページ
			return "redirect:/getListTasks";
		}
		
		// タスク情報とタイマー情報を取得する
		Task task = optionalTask.get();
		TimersSetting latestTimersSetting = timersSettingService.getUsersSettingTimer(); 
		
		if (latestTimersSetting == null) {
			String errorMessage = "タイマーが未登録です";
			ra.addFlashAttribute("errorMessage", errorMessage);
			// エラーとなりリダイレクトされる
			return "redirect:/getListTasks";
		}
		
		// 集中タイマーとタスク情報を画面に流す
		model.addAttribute("task", task);
		model.addAttribute("timersSetting", latestTimersSetting);
		

			return "timers/focusTimer";
	}
	
	/**
	 * 休憩タイマー画面表示
	 * 
	 * @return 休憩タイマー画面
	 */
	@GetMapping("/getBreakTimer/{id}")
	public String getBreakTimer(@PathVariable(name = "id") Long id, Model model, RedirectAttributes ra) {
		
		// エラーチェック
		if (id == null) {
			return "redirect:/getListTasks";
		}
		// タスクIDに紐づくタスク情報を取得
		Optional<Task> optinalTask = taskService.get(id);
		
		// タスク情報の有無をチェック
		if (optinalTask.isEmpty()) {
			// エラーページ
			return "redirect:/getListTasks";
		}
			
		// タスク情報とタイマー情報と取得する
		Task task = optinalTask.get();
		TimersSetting latestTimersSetting = timersSettingService.getUsersSettingTimer();
		
		if (latestTimersSetting == null) {
			String errorMessage = "タイマーが未登録です";
			ra.addFlashAttribute("errorMessage", errorMessage);
			// エラーとなりリダイレクトされる
			return "redirect:/getListTasks";			
		}
		
		// タスク情報を画面に渡す
		model.addAttribute("task", task);
		// タイマー情報を画面に渡す
		model.addAttribute("timersSetting", latestTimersSetting);
		return "timers/breakTimer";

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

