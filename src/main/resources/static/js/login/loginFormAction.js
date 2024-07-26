/**
 * ログイン画面の操作
 */

$(document).ready(function() {
	/**
	 * ログイン前ユーザー登録画面からログイン画面へ遷移する
	 */
	$(".getLoginForm").on("click", function() {
		window.location.href = '/loginForm'
	});
	
	/**
		ログインデータを送信する
	 */
	$(".login").on("click", function() {
		// htmlに入力された値を格納する
		let email = $("#mailInput").val();
		let password = $("#passwordInput").val();
		
		// console.log(email);
		// console.log(password);
		// ログインデータをオブジェクトに格納
		let loginUser = {
			email: email, 
			password: password
		};

		// フォームの値をセットする
		$(".loginFormContainer input[ name = 'email' ]").val(loginUser.email);
		$(".loginFormContainer input[ name = 'password' ]").val(loginUser.password);
		
		// フォームを送信
		$(".loginFormContainer").submit();
		// return false;
		
	});
});