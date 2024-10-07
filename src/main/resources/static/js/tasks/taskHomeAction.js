/**
 * タスク管理初期画面の操作
 */
$(document).ready(function () {
    /**
     * タスク登録画面ヘ遷移する
     */
    $(".getCreateTask").on("click", function () {
        window.location.href = "/getCreateTask";
        return false;
    });
    /**
     * タスク一覧画面へ遷移する
     */
    $(".getListTasks").on("click", function () {
        window.location.href = "/getListTasks";
        return false;
    });

    /**
     * タスク一覧画面へ遷移する
     */
    $(".getHome").on("click", function () {
        window.location.href = "/home";
        return false;
    });

    // 管理者ホーム画面へ遷移する
    $(".getAdminHome").on("click", function() {
        window.location.href = "/getAdminHome";
        return false;
    });

    
	let role = $("#role").val();
	/**
		ロールを比較して不要なものを非活性にする。
	 */
	if (role == "ADMIN") {
		$("#getAdminHome").prop("disabled", false); // 管理者用の戻るボタンを表示
		$("#getHome").prop("disabled", true); // 一般ユーザー用の戻るボタンを非表示
	} else if(role == "GENERAL") {
        $("#getAdminHome").prop("disabled", true); // 管理者用の戻るボタンを表示
		$("#getHome").prop("disabled", false); // 一般ユーザー用の戻るボタンを非表示
	}
	
});
