package com.example.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Task;
import com.example.entity.TimersSetting;
import com.example.entity.User;
import com.example.service.LoginUser;
/**
 * タスクコントローラクラス
 * @author yoshi
 *
 */
import com.example.service.TaskService;
import com.example.service.TimersSettingService;
import com.example.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class TaskController {
	
	private final TaskService taskService;
	private final UserService userService;
	private final TimersSettingService timersSettingService;
	// コンストラクタインジェクション
	@Autowired
	public TaskController(TaskService taskService, UserService userService, TimersSettingService timersSettingService) {
		this.taskService = taskService;
		this.userService = userService;
		this.timersSettingService = timersSettingService;
	}
	
	/**
	 * タスク管理初期画面表示
	 * 
	 * @param loginUser ログインユーザー情報
	 * @param model ユーザーオブジェクトの属性を追加
	 * 
	 * @return タスク管理初期画面
	 */
	@GetMapping("/getTaskHome")
	public String getTask(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		// ログインユーザー情報からユーザーオブジェクトを生成する
		User currentUser = loginUser.getUser();
		// modelに追加し、遷移先へ渡す
		model.addAttribute("currentUser", currentUser);
		// タスク管理初期画面へ遷移
		return "tasks/taskHome";
	}
	
	/**
	 * タスク登録画面表示
	 * 
	 * @param model タスクオブジェクトの属性を追加
	 * 
	 * @return タスク登録画面
	 */
	@GetMapping("/getCreateTask") // URLの紐づけ
	public String getCreateTask(Model model) {
		// 新規登録用に空のタスク登録情報を作成
		Task task = new Task();
		// modelに渡す
		model.addAttribute("task", task);
		// タスク登録画面へ遷移
		return "tasks/taskForm";
	}	

	/**
	 * ユーザー別タスク一覧画面表示
	 * 
	 * @param model タスク一覧と最新のタイマーレコードの属性を追加
	 * @param request リダイレクト元から受け取ったセッションを受け取る
	 * 
	 * @return タスク一覧画面
	 */
	@GetMapping("/getListTasks") // URLの紐づけ
	public String getListTasks(Model model, HttpServletRequest request) {

		// リダイレクト元から送信されたセッションスコープのオブジェクトを取得
		HttpSession session = request.getSession();
		String messageType = (String) session.getAttribute("messageType");

		String message = null;
		if ("taskRegistar".equals(messageType)) {
			// タスク登録完了メッセージをセットする
			message = (String) model.getAttribute("taskRegistarMessage");
		} else if ("taskDelete".equals(messageType)) {
			// タスク削除完了メッセージをセットする
			message = (String) model.getAttribute("taskDeleteMessage");
		} else if ("timerSet".equals(messageType)) {
			// タイマー設定完了メッセージをセットする
			message = (String) model.getAttribute("timerSetMessage");
		} else if ("timerNotSet".equals(messageType)) {
			// タイマー未設定メッセージをセットする
			message = (String) model.getAttribute("timerNotSetMessage");
		}
		
		// メッセージタイプを削除する
		session.removeAttribute("messageType");
		// メッセージがある場合はModelに追加
		if (Objects.nonNull(message)) {
			model.addAttribute("flashMessage" ,message);
		}
		
		// ログインしているユーザーidを取得する
		Long currentUserId = userService.getCurrentUserId();
		List<Task> listTasks = taskService.getTasksByUserId(currentUserId);
		model.addAttribute("listTasks", listTasks);
		// 登録しているタイマー設定の最新版を取得する
		TimersSetting latestSetTimes = timersSettingService.getUsersSettingTimer();
		model.addAttribute("latestSetTimes", latestSetTimes);
		return "tasks/tasks";	
	}
	
	/**
	 * タスク登録・更新処理
	 * 
	 * @param task タスク情報
	 * @param ra リダイレクト先のステータスメッセージ
	 * @param request リダイレクト先へ渡すセッション
	 * 
	 * @return タスク一覧画面
	 */	 
	@PostMapping("/register") // URLの紐づけ
	public String registerTask(
		@ModelAttribute("task") Task task, 
		RedirectAttributes ra,
		HttpServletRequest request) {
		// タスクサービスを呼び出す
		taskService.save(task);
		
		// タスク登録完了メッセージをFlashScopeに保存
		ra.addFlashAttribute("taskRegistarMessage", "タスクを登録しました");
		// セッションスコープにメッセージの種類を保存
		HttpSession session = request.getSession();
		session.setAttribute("messageType", "taskRegistar");

		// タスク一覧画面をリダイレクト表示
		return "redirect:/getListTasks";
	}
	
	/**
	 * タスク編集画面表示
	 * 
	 * @param id タスクID
	 * @param  model タスクオブジェクトの属性を追加
	 * 
	 * @return タスク編集画面
	 */
	@GetMapping("/getEditTask/{id}")
	public String getEditTask(@PathVariable(name = "id")  Long id, Model model) {
		// タスクIDのエラーチェック
		if (id == null) {
			// タスク一覧画面へリダイレクトする
			return "redirect:/getListTasks";
		}
				
		// タスクIDに紐づくタスク情報を取得
		Optional<Task> optionalTask = taskService.get(id);

		// タスク情報がnullかチェック
		if (optionalTask.isEmpty()) {
			// エラーページ
			return "redirect:/getListTasks";
		} else {
			// 取得したタスク情報からオブジェクトを生成する
			Task task = optionalTask.get();
			// modelに追加し、遷移先へ渡す
			model.addAttribute("task" ,task);
			// タスク編集画面へ遷移
			return "tasks/taskEdit";		
		}
	}
	
	/**
	 * タスク削除処理
	 * 
	 * @param id タスクID
	 * 
	 * @return タスク一覧画面 
	 */
	@PostMapping("/deleteTask/{id}")
	public String deleteTask(
		@PathVariable(name = "id") Long id, 
		RedirectAttributes ra,
		HttpServletRequest request
	) {
		taskService.delete(id);

		// 削除完了メッセージをFlashScopeに保存
		ra.addFlashAttribute("taskDeleteMessage", "タスクを削除しました");

		// セッションスコープにメッセージの種類を保存
		HttpSession session = request.getSession();
		session.setAttribute("messageType", "taskDelete");

		// タスク一覧画面をリダイレクトする
		return "redirect:/getListTasks";
	}
	
}
