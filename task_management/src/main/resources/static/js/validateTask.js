/**
 * タスク登録のバリデーション
 */
$(document).ready(function() {
    $("button").on("click", function() {
        // フォームから入力値を取得
        let taskName = $("#taskName").val();
        let priority = $("#priority").val();
        let dueDate = $("#dueDate").val();
        // バリデーションフラグ
        let isValid = true;

        // タスク名が入力されているかどうかをチェック
        if (taskName == "" || taskName === null) {
            // タスクが入力されていない場合はエラーを出してフォーム送信を中止する
            alert("タスクを入力してください");
            isValid = false;
            return isValid;
        }
        
        // 優先順位が入力されているかどうかをチェック
        if (priority === "" || priority === null) {
            // 優先順位が入力されていない場合はエラーを出してフォーム送信を中止する
            alert("優先順位を入力してください");
            isValid = false;
            return isValid;
            
        }
        
        /// 期日が入力されているかどうかをチェック
        if (dueDate === "" || dueDate === null) {
            // 期日が入力されていない場合はエラーを出してフォーム送信を中止する
            alert("期日を入力してください");
            isValid = false;
            return isValid;	
        }

        // 優先順位が1から30までの間で入力されているかチェック
        if (priority <= 0 || priority > 30) {
            // 優先順位が0以下または30を超える場合はエラーを出してフォーム送信を中止する
            alert("優先順位は1から30までの数字を入力してください");
            isValid = false;
            return isValid;	
        }

        // フォームエラー送信を中止する
        return isValid;	
    });
});
