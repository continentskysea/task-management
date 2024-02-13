/**
 * 
 */
 
 function validateUser() {
	// フォームからEmailの入力値を取得
	let email = document.getElementById("email").value;
	// 名前の正規表現
	const nameRegex = /^[\u4E00-\u9FAF]+$/;
	// パスワードの正規表現
	const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]*$/;
	// デフォルトの@example.comが入力されているかどうかをチェック
	if (email.includes("@example.com")) {
		// デフォルト値が入力されている場合は、バリデーションをスキップして true を返す
		return true;		
	}
	
	// 名前の入力値を取得
	let name = document.getElementById("name").value;
	// パスワードの入力値を取得
	let password = document.getElementById("password").value;
	
	// 名前が入力されているかチェック
	if (name === "") {
		alert("名前は必須です");
		return false; // フォームの送信を中止
	}
	
	// 1文字以上の漢字か
	if (!nameRegex.test(name)) {
		alert("1文字以上の漢字を使ってください");
		return false;
	} 
	
	// 名前は4文字以上12文字以下かチェック
	if (name.length < 4 || name.length > 12) {
		alert("名前は4文字以上12文字以内で入力してください");
		return false;
	}
	
	// パスワードの長さが1文字以上12文字以内かチェック
	if (password.length < 1 || password.length > 12) {
		alert("パスワードは1文字以上12文字以内で入力してください");
		return false;
	}
	
	// パスワードの正規表現をチェック
	if (!passwordRegex.test(password)) {
		alert("パスワードは英数字の大文字小文字を含む必要があります");
		return false;
	}
	
	// すべてのバリデーションが通れば true を返す
	return true;
	
}