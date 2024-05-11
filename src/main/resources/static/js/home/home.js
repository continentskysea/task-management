/**
 * タスク管理初期画面に適用
 */
$(document).ready(function() {

	let role = $("#role").val();
	console.log(role);
	
	
	
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
