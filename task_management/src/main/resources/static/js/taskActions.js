/**
 * 
 */
 // taskActions.js
 
 // 集中タイマー画面に遷移する
function redirectToFocusTimer() {
    let selectedCheckbox = getSelectedCheckbox();

    if (selectedCheckbox) {
        // 選択されたタスクの情報を取得
        let taskId = selectedCheckbox.value;

        // タスク情報を使って集中タイマー画面にリダイレクト
        window.location.href = '/getFocusTimer/' + taskId;
    }
}

// 休憩タイマー画面に遷移する
function redirectToBreakTimer() {
	let selectedCheckbox = getSelectedCheckbox();
	
	if (selectedCheckbox) {
		// 選択されたタスク情報を取得
		let taskId = selectedCheckbox.value;
		
		// タスク情報を使って休憩タイマー画面にリダイレクト
		window.location.href = '/getBreakTimer/' + taskId;
	}
}

// タスク編集画面へ遷移する
function redirectToEditTask() {
    let selectedCheckbox = getSelectedCheckbox();

    if (selectedCheckbox) {
        // 選択されたタスクの情報を取得
        let taskId = selectedCheckbox.value;

        // タスク情報を使って編集画面にリダイレクト
        window.location.href = '/editTask/' + taskId;
    }
}

// チェックされた要素を調べる
function getSelectedCheckbox() {
    let checkboxes = document.getElementsByClassName('taskCheckbox');
    let checkedCheckbox = null;
    let checkedCount = 0;
    
    
    for (let i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			checkedCheckbox = checkboxes[i];
			checkedCount++;
		}
	}
	
	// チェックの要素が1つだけか確認する
	if (checkedCount  === 0 || checkedCount >= 2) {
		const errorMsg = "タスクが未選択あるいは複数選択されています";
		alert(errorMsg);
		return null;
	}
    
	return checkedCheckbox;
}