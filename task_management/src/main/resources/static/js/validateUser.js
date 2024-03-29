/**
 * ユーザー登録のバリデーション
 */
$(document).ready(function() {
    $("#createUserButton").on("click", function() {
        // フォームから入力値を取得
        let name = $("#name").val();
        let password = $("#password").val();
        let email = $("#email").val();
        
        // バリデーションフラグ
        let isValid = true;


        // デフォルトの@example.comが入力されているかどうかをチェック
        if (!email.includes("@example.com")) {
            // 入力値に @example.com が含まれていない場合はフォームの送信を中止
            alert("アドレスには@example.comを含めてください");
            isValid = false;	
        }
        

        // 名前のバリデーション
        const nameRegex = /^[\u4E00-\u9FAF]+$/; // 名前の正規表現
             // 1文字以上の漢字か
        if (!nameRegex.test(name)) {
            alert("1文字以上の漢字を使ってください");
            isValid = false;
        } else if (name === "") {
            alert("名前は必須です");
            isValid = false;
        } else if (name.length < 3 || name.length > 12) {
            alert("名前は3文字以上12文字以内で入力してください");
            isValid = false;
        }
        
        // パスワードのバリデーション
        const passwordRegex = /^(?=.*[a-z])(?=.*\d)[a-z\d]*$/; // 小文字と数字のみを許可
        if (password.length < 1 || password.length > 12) {
            alert("パスワードは1文字以上12文字以内で入力してください");
            isValid = false;
        } else if (!passwordRegex.test(password)) {
            alert("パスワードは小文字と数字のみを含む必要があります");
            isValid = false;
        }
    
        // バリデーションを通過したラフォームを送信
        return isValid;
    });
});
