/**
 * タスク管理初期画面に適用
 */
$(document).ready(function() {

	// タスク管理ホームヘ遷移
	$(".getTaskHome").on("click", function() {
		window.location.href = "/getTaskHome";
		return false;
	});

	// タイマー管理ホームへ遷移
	$(".getTimerHome").on("click", function () {
		window.location.href = "/getTimer";
		return false;
	})

    // ユーザー管理ホームへ遷移(管理者用)
    $(".getUserHome").on("click", function () {
        window.location.href = "/getUserHome";
        return false;
    });

	// ユーザー権限
	let role = $("#role").val();

	// ロールによって認可を制御する
	if (role == "ADMIN") {
		$(".getAdminHome").prop("disabled", false); // 管理者用の戻るボタンを表示
		$(".getHome").prop("disabled", true); // 一般ユーザー用の戻るボタンを非表示
	} else if(role == "GENERAL") {
			$("#getAdminHome").prop("disabled", true); // 管理者用の戻るボタンを表示
		$(".getHome").prop("disabled", false); // 一般ユーザー用の戻るボタンを非表示
	}
	
});
