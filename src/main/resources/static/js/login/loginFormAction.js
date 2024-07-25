/**
 * ログイン画面の操作
 */

$(document).ready(function() {
	/**
		新規登録画面に遷移する
	 */
	$(".registar").on("click", function() {
		window.location.href = '/getBeforeLoginCreateUser';
	});

	/**
	 * ログイン前ユーザー登録画面からログイン画面へ遷移する
	 */
	$(".getLoginFormButton").on("click", function() {
		window.location.href = '/loginForm'
	});
	
	/**
		ログインデータを送信する
	 */
	$(".loginRelation").on("click", function() {
		// htmlに入力された値を格納する
		let email = $("#mailInput").val();
		let password = $("#passwordInput").val();
		
		// ログインデータをオブジェクトに格納
		let loginUser = {
			email: email, 
			password: password
		};

		// フォームの値をセットする
		$("#userLoginForm input[ name = 'email' ]").val(loginUser.email);
		$("#userLoginForm input[ name = 'password' ]").val(loginUser.password);
		
		// フォームを送信
		$("#userLoginForm").submit();
		
		// フォームのデフォルトの送信キャンセル
		return false;
	});
			
});