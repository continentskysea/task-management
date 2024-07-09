package com.example.controller;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;



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
	 * @param loginUser　ログインユーザー情報
	 * @param model ユーザーオブジェクトの属性を追加
	 * 
	 * @return タイマー管理初期画面
	 */
	@GetMapping("/getTimer")
	public String getTimer(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		// ログインユーザー情報からユーザーオブジェクトを生成する
		User currentUser = loginUser.getUser();
		// modelに追加し、遷移先へ渡す
		model.addAttribute("currentUser" , currentUser);
		// タスク管理初期画面を表示
		return "timers/timerHome";
	}


	/**
	 * タイマー一覧表示
	 * @param model タイマーのリストとステータスメッセージの属性を追加
	 * @param request リダイレクト元から受け取ったセッションを受け取る
	 * 
	 * @return タイマー一覧画面
	 */
	@GetMapping("/getTimerlist")
	public String getTimerlist(Model model, HttpServletRequest request) {
		
		// リダイレクト元から送信されたセッションスコープをオブジェクトに取得する
		HttpSession session = request.getSession();
		String messageType = (String) session.getAttribute("messageType");
		
		// メッセージタイプオブジェクトのキーとバリューを比較する
		String message = null;
		if ("timerSet".equals(messageType)) {
			// タイマー登録完了メッセージをセットする
			message = (String) model.getAttribute("timerSetMessage");
		} else if ("timerDelete".equals(messageType)) {
			// タイマー削除完了メッセージをセットする
			message = (String) model.getAttribute("timerDeleteMessage");
			// タイマー未登録メッセージをセットする
		} else if ("timerNotSet".equals(messageType)) {
			message = (String) model.getAttribute("timerNotSetMassage");
		}
		// セッションスコープからメッセージタイプを削除
		session.removeAttribute("messageType");

		// メッセージがある場合はModelに追加
		if (Objects.nonNull(message)) {
			model.addAttribute("flashMessage", message);
		}

		// ログインユーザーのIDからタイマー設定情報のリストを画面に表示する
		Long currentUserId = userService.getCurrentUserId();
		List<TimersSetting> timersList = timersSettingService.getTimersByUserId(currentUserId);
		model.addAttribute("timersList", timersList);

		// タイマー一覧画面へ遷移
		return "timers/timers";
	} 
	
	
	/**
	 * タイマー登録画面表示
	 * 
	 * @param model　タイマーオブジェクトの属性を追加
	 * 
	 * @return タイマー登録画面
	 */
	@GetMapping("/getTimerSetting")
	public String getTimerSetting(Model model) {
		// 登録用の空のオブジェクトをモデルに追加
		TimersSetting timersSetting = new TimersSetting();
		model.addAttribute("timersSetting" ,timersSetting);
		// タイマー登録画面へ遷移
		return "timers/timerSetting";
	}
	

	/**
	 * タイマーの登録処理
	 * 
	 * @param timersSetting タイマー設定情報(集中時間と休憩時間)
	 * @param formSendCheckPageValue リクエストの送信がされたページの判定値
	 * @param ra  リダイレクト先のステータスメッセージ
	 * @param request リダイレクト先へ渡すセッションを渡す
	 * 
	 * @return タスク一覧画面
	 */
	@PostMapping("/save")
	public String save(
		@ModelAttribute("timersSetting") TimersSetting timersSetting, 
		@RequestParam("formSendCheckPageValue") String formSendCheckPageValue,
		RedirectAttributes ra,
		HttpServletRequest request
	) {

		// タイマー設定情報を登録する
		timersSettingService.save(timersSetting);

		// FlashScopeに保存する
		ra.addFlashAttribute("timerSetMessage", "タイマーを登録しました");
		// リダイレクト先にセッションスコープを渡す
		HttpSession session = request.getSession();
		session.setAttribute("messageType", "timerSet");
		
		// タイマー新規登録フォームならタスク一覧画面へと遷移
		if (Long.parseLong(formSendCheckPageValue) == 1) {
			return "redirect:/getListTasks";
		}
		// タイマー一覧画面へ遷移
		return "redirect:/getTimerlist";
	}
	
	
	/**
	 * 集中タイマー画面表示
	 * 
	 * @param id タイマー設定ID
	 * @param model　タイマーオブジェクトとタスクオブジェクトの属性を追加
	 * @param ra リダイレクト先のステータスメッセージ
	 * @param request リダイレクト先へ渡すセッションを渡す
	 * 
	 * @return 集中タイマー画面
	 */
	@GetMapping("/getFocusTimer/{id}")
	public String getFocusTimer(
		@PathVariable(name = "id") Long id, 
		Model model, 
		RedirectAttributes ra,
		HttpServletRequest request
	) {
		// タイマー設定IDのエラーチェック
		if (id == null) {
			// エラーページ(タスク一覧をリダイレクト)
			return "redirect:/getListTasks";
		}
		
		// タスクIDに紐づくタスク情報を取得
		Optional<Task> optionalTask = taskService.get(id);
			
		// タスク情報が何もない場合はタスク一覧表ヘ遷移
		if (optionalTask.isEmpty()) {
			// エラー(タスク一覧へリダイレクト)
			return "redirect:/getListTasks";
		}
		
		// タスク情報と最新のタイマー情報を取得する
		Task task = optionalTask.get();
		TimersSetting latestTimersSetting = timersSettingService.getUsersSettingTimer(); 
		
		// タイマー未登録チェック
		if (latestTimersSetting == null) {
		
			// FlashScopeに保存する
			ra.addFlashAttribute("timerNotSetMessage", "タイマーが未登録です");
			// リダイレクト先にセッションスコープを渡す
			HttpSession session = request.getSession();
			session.setAttribute("messageType", "timerNotSet");
			// エラー(タスク一覧へリダイレクト)
			return "redirect:/getListTasks";
		}
		
		// modelに追加し、遷移先へ渡す
		model.addAttribute("task", task);
		model.addAttribute("timersSetting", latestTimersSetting);
		
		// 集中タイマー画面へ遷移
		return "timers/focusTimer";
	}
	

	/**
	 * 休憩タイマー画面表示
	 * 
	 * @param id タスクID
	 * @param model タスクオブジェクトとタイマーオブジェクトの属性を追加
	 * @param ra リダイレクト先のステータスメッセージ
	 * @param request リダイレクト先へ渡すセッションを渡す
	 * 
	 * @return 休憩タイマー画面
	 */
	@GetMapping("/getBreakTimer/{id}")
	public String getBreakTimer(
			@PathVariable(name = "id") Long id, 
			Model model,
			RedirectAttributes ra,
			HttpServletRequest request
		) {
		
		// タスクIDのエラーチェック
		if (id == null) {
			// タスク一覧画面ヘリダイレクトする
			return "redirect:/getListTasks";
		}
		// タスクIDに紐づくタスク情報を取得
		Optional<Task> optinalTask = taskService.get(id);
		
		// タスク情報がnullかチェック
		if (optinalTask.isEmpty()) {
			// タスク一覧画面へリダイレクトする
			return "redirect:/getListTasks";
		}
			
		// タスク情報とタイマー情報と取得する
		Task task = optinalTask.get();
		TimersSetting latestTimersSetting = timersSettingService.getUsersSettingTimer();
		
		// タイマーがnullかチェック
		if (latestTimersSetting == null) {
			// FlashScopeに保存する
			ra.addFlashAttribute("timerNotSetMessage", "タイマーが未登録です");
			// リダイレクト先にセッションスコープを渡す
			HttpSession session = request.getSession();
			session.setAttribute("messageType", "timerNotSet");

			// タスク一覧画面へリダイレクトする
			return "redirect:/getListTasks";			
		}
		
		// modelに保存し、遷移先へ渡す
		model.addAttribute("task", task);
		model.addAttribute("timersSetting", latestTimersSetting);
		
		// 休憩タイマー画面へ遷移
		return "timers/breakTimer";
	}


	/**
	 * タイマー編集画面表示
	 * 
	 * @param id タイマーID
	 * @param model タイマー情報の属性を追加
	 * 
	 * @return タイマー編集画面
	 */
	@GetMapping("/getEditTimer/{id}")
	public String getEditTimer(@PathVariable(name = "id") Long id, Model model) {
		// タイマー情報に何もなければタイマー一覧へリダイレクト
		if (id == null) {
			return "redirect:/getTimerlist";
		}

		// タスクIDに紐づくタスク情報を取得
		Optional<TimersSetting> optinalTimer = timersSettingService.get(id);

		// タイマー情報がnullかチェック
		if (optinalTimer.isEmpty()) {
			// エラーページ
			return "redirect:/getTimerlist";
		} else {
			// 取得したタイマー情報からオブジェクトを生成する
			TimersSetting timersSetting = optinalTimer.get();
			// modelに保存し、遷移先へ渡す
			model.addAttribute("timersSetting" ,timersSetting);
			// タイマー編集画面へ遷移する
			return "timers/timerEdit";		
		}
	}

	/**
	 * タイマー削除処理
	 * 
	 * @param id タイマーid
	 * @param ra リダイレクト先のステータスメッセージ
	 * @param request リダイレクト先へ渡すセッションを渡す
	 * 
	 * @return タイマー一覧画面
	 * 
	 */
	@PostMapping("/deleteTimer/{id}")
	public String deleteTimer(
		@PathVariable(name = "id") Long id, 
		RedirectAttributes ra,
		HttpServletRequest request
	) {

		// タイマー情報を削除する
		timersSettingService.delete(id);

		// FlashScopeに保存する
		ra.addFlashAttribute("timerDeleteMessage", "タイマーを削除しました");
		// リダイレクト先にセッションスコープを渡す
		HttpSession session = request.getSession(); 
		session.setAttribute("messageType", "timerDelete");
	
		// タイマー一覧をリダイレクト
		return "redirect:/getTimerlist";
	}
	
	
}

