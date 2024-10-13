import { validataTask } from "./validateTask";

/**
 * タスク登録の処理
 */
$(document).ready(function() {
	/**
	 * 登録データを送信する
	 */
	$(".registarTask").on("click", function () {
        // フォームから入力値を取得
		let taskNameInput = $("#taskNameInput").val();
		let priorityInput = $("#priorityInput").val();
		let dueDateInput = $("#dueDateInput").val();

		// 入力値の検証

			// 入力データをオブジェクトに格納
			let task = {
				taksName: taskNameInput,
				priority: priorityInput,
				dueDate: dueDateInput,
			};
	
			// フォームの値をセットする
			$(".taskFormContainer input[ name = 'taskName' ]").val(task.taksName);
			$(".taskFormContainer input[ name = 'priority' ]").val(task.priority);
			$(".taskFormContainer input[ name = 'dueDate' ]").val(task.dueDate);
	
			// フォームを送信
			$(".taskRegistarForm").submit();
	});
});