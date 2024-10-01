/**
 * タスクフォームの操作 
 */
$(document).ready(function() {
	/**
   * タスク登録画面ヘ遷移する
   */
	$(".taskCreateRelation").on("click", function () {
		window.location.href = "/getCreateTask";
	});
	/**
	 * 登録データを送信する
	 */
	$(".registarTask").on("click", function () {
    // htmlに入力された値を格納する
    let taskNameInput = $("#taskNameInput").val();
    let priorityInput = $("#priorityInput").val();
    let dueDateInput = $("#dueDateInput").val();

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

	/**
   * タスク一覧画面へ遷移
   */
	$(".getTasksList").on("click", function () {
		window.location.href = "/getListTasks";
		return false;
	});

	/**
   * タスク管理初期画面へ遷移
   */
	$(".getTaskHome").on("click", function () {
		window.location.href = "/getTask";
		return false;
	});
	/**
	 * チェックされた要素を調べる
	 *
	 * @returns チェックされたタスク情報
	 */
	const getSelectedCheckbox = () => {
		let checkboxes = $("input.taskCheckbox:checked");
		let checkedCount = checkboxes.length;

		// チェックの要素が1つだけか確認する
		if (checkedCount !== 1) {
		const errorMsg = "タスクが未選択あるいは複数選択されています";
		alert(errorMsg);
		return null;
		}
		return checkboxes[0];
	};
});
