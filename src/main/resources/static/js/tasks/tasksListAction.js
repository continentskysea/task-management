/**
 * タスク一覧画面の操作
 */
$(document).ready(function() {

    /**
     * 集中タイマー画面へ遷移する
     */
    $(".getFocusTimer").on("click", function() {
        let selectedCheckbox = getSelectedCheckbox();

        if (selectedCheckbox) {
            // 選択されたタスクの情報を取得
            let taskId = $(selectedCheckbox).val();
            // タスク情報を使って集中タイマー画面にリダイレクト
            window.location.href = '/getFocusTimer/' + taskId;
        }
    });

	/**
		タスク編集画面へ遷移する
	 */
    $(".getTaskEdit").on("click", function() {
        let selectedCheckbox = getSelectedCheckbox();
        
        if (selectedCheckbox) {
            // 選択されたタスクの情報を取得
            let taskId = $(selectedCheckbox).val();
            // タスク情報を使って編集画面にリダイレクト
            window.location.href = '/getEditTask/' + taskId;
        }
    });
    // 削除ボタンが押下された時の処理
    $(".taskDelete").on("click", function() {
        // チェックボックスクラスを取得する
        let checkedCheckboxes = $('.taskCheckbox:checked');
        // 削除フォームidを取得する
        let deleteForm = $(".taskDeleteForm");
        
        // タスクの選択数をチェック
        if (checkedCheckboxes.length === 1) {
            
            // タスクを取得し、urlにリクエストする
            let taskId = checkedCheckboxes[0].value;
            deleteForm.attr('action', '/deleteTask/' + taskId); 
            deleteForm.submit();// フォームを送信する
        } else {
            // エラーメッセージを出力し、削除をキャンセルする
            const errorMsg = "タスクが未選択あるいは複数選択されています";
            alert(errorMsg);
            deleteForm.attr('action', '#');
        }
    });
    
    // タスク管理初期画面へ遷移
    $(".getTaskHome").on("click", function () {
        window.location.href = "/getTaskHome";
        return false;
    });
    
    /**
     * チェックされた要素を調べる 
     * 
     * @returns チェックされたタスク情報
     */
    const getSelectedCheckbox = () => {
        let checkboxes = $("input.taskCheckbox:checked");
        let checkedCount = checkboxes.length;
    
        // チェックの要素が1つだけか確認する
        if (checkedCount  !== 1) {
            const errorMsg = "タスクが未選択あるいは複数選択されています";
            alert(errorMsg);
            return null;
        }
        return checkboxes[0];
    } 
});