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
	 * @return タスク管理初期画面
	 */
	@GetMapping("/getTask")
	public String getTask(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		User currentUser = loginUser.getUser();
		model.addAttribute("currentUser", currentUser);
		// タスク管理初期画面を表示
		return "tasks/taskHome";
	}
	
	/**
	 * タスク登録画面表示
	 * 
	 * @return タスク登録画面
	 */
	@GetMapping("/getCreateTask") // URLの紐づけ
	public String getCreateTask(Model model) {
		// 新規登録用に空のタスク登録情報を作成
		Task task = new Task();
		model.addAttribute("task", task);
		// タスク登録画面を表示
		return "tasks/taskForm";
	}	

	/**
	 * ユーザー別タスク一覧画面表示
	 * 
	 * @return タスク一覧画面
	 */
	@GetMapping("/getListTasks") // URLの紐づけ
	public String getListTasks(Model model) {
		// Flash属性からメッセージを取得する
		String message = (String) model.getAttribute("errorMessage");
		// メッセージがnullかチェック
		if (Objects.isNull(message)) {
			message = (String) model.getAttribute("timerSetMessage");
			model.addAttribute("flashMessage" ,message);
		} else {
			model.addAttribute("flashMessage", message);
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
	 * 
	 * @return タスク一覧画面
	 */	 
	@PostMapping("/register") // URLの紐づけ
	public String registerTask(@ModelAttribute("task") Task task) {
		System.out.println(task.getName());
		System.out.println(task.getPriority());
		System.out.println(task.getDueDate());
		System.out.println(task.getUserId());
		// タスクサービスを呼び出す
		taskService.save(task);
		// タスク一覧画面をリダイレクト表示
		return "redirect:/getListTasks";
	}
	
	/**
	 * タスク編集画面表示
	 * 
	 * @param task タスク情報
	 * 
	 * @return タスク編集画面
	 */
	@GetMapping("/getEditTask/{id}")
	public String getEditTask(@PathVariable(name = "id")  Long id, Model model) {
		if (id == null) {
			return "redirect:/getListTasks";
		}
				
		// タスクIDに紐づくタスク情報を取得
		Optional<Task> optionalTask = taskService.get(id);

		if (optionalTask.isEmpty()) {
			// エラーページ
			return "redirect:/getListTasks";
		} else {
			Task task = optionalTask.get();
			// 取得したタスク情報を画面に渡す
			model.addAttribute("task" ,task);
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
	public String deleteTask(@PathVariable(name = "id") Long id) {
		taskService.delete(id);
		return "redirect:/getListTasks";
	}
	
}
