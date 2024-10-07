/**
 * ユーザー編集画面の操作
 */
$(document).ready(function() {
	// ユーザー一覧画面へ遷移する
	$(".getListUsers").on("click", function() {
		window.location.href = "/getListUsers";
		return false;
	});
});