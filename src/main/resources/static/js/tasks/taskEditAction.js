/**
 * タスクフォームの操作 
 */
$(document).ready(function() {

	// タスク一覧画面へ遷移
	$(".getTasksList").on("click", function () {
		window.location.href = "/getListTasks";
		return false;
	});
});
