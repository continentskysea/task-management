/**
 * タスク登録のバリデーション
 */
$(document).ready(function() {
    $(".registarTask").on("click", function() {
        // フォームから入力値を取得
        let taskNameInput = $("#taskNameInput").val();
        let priorityInput = $("#priorityInput").val();
        let dueDateInput = $("#dueDateInput").val();
        // バリデーションフラグ
        let isValid = true;
        // タスク名が入力されているかどうかをチェック
        if (taskNameInput == "" || taskNameInput === null) {
            // タスクが入力されていない場合はエラーを出してフォーム送信を中止する
            alert("タスクを入力してください");
            isValid = false;
            return isValid;
        }
        
        // 優先順位が入力されているかどうかをチェック
        if (priorityInput === "" || priorityInput === null) {
            // 優先順位が入力されていない場合はエラーを出してフォーム送信を中止する
            alert("優先順位を入力してください");
            isValid = false;
            return isValid;
            
        }
        
        /// 期日が入力されているかどうかをチェック
        if (dueDateInput === "" || dueDateInput === null) {
            // 期日が入力されていない場合はエラーを出してフォーム送信を中止する
            alert("期日を入力してください");
            isValid = false;
            return isValid;	
        }
        
        // 優先順位が1から30までの間で入力されているかチェック
        if (priorityInput <= 0 || priorityInput > 30) {
            // 優先順位が0以下または30を超える場合はエラーを出してフォーム送信を中止する
            alert("優先順位は1から30までの数字を入力してください");
            isValid = false;
            return isValid;	
        }
        
        // フォームエラー送信を中止する
        return isValid;	
    });        
});
