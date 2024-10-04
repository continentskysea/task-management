/**
 * タスク管理初期画面に適用
 */
$(document).ready(function() {

	/**
	 * タスク管理ボタンが押されたら
	 */
	$(".getTaskHome").on("click", function() {
		window.location.href = "/getTask";
		return false;
	});

	/**
	 * タイマー管理ボタンが押されたら
	 */
	$(".getTimerHome").on("click", function () {
		window.location.href = "/getTimer";
		return false;
	})

    // ユーザー管理ボタンが押されたら
    $(".getUserHome").on("click", function () {
        window.location.href = "/getUserHome";
        return false;
    });

	let role = $("#role").val();
	/**
		ロールを比較して不要なものを非活性にする。
	 */
	if (role == "ADMIN") {
		$(".getAdminHome").prop("disabled", false); // 管理者用の戻るボタンを表示
		$(".getHome").prop("disabled", true); // 一般ユーザー用の戻るボタンを非表示
	} else if(role == "GENERAL") {
			$("#getAdminHome").prop("disabled", true); // 管理者用の戻るボタンを表示
		$(".getHome").prop("disabled", false); // 一般ユーザー用の戻るボタンを非表示
	}
	
});
