/**
 * 集中タイマー画面表示用
 */
 // 選択されたチェックボックスに対応するリンクを生成する関数
 function generateFocusTimerLink() {
	// すべてのチェックボックスを取得
	let checkboxes = document.getElementsByName('taskIds');
	// タイマーページのリンク
	const focusTimerLink = document.getElementById('focusTimerLink');
	// チェックボックスの数を格納
	let checkedCount = 0;
	let checkedId = "";
	
	// チェックが入っているチェックボックスの数をカウント	
	for (let i = 0; i < checkboxes.length; i++) {
		
		// チェックボックスが選択されている場合
		if (checkboxes[i].checked) {
			checkedCount++;
			checkedId = checkboxes[i].value;
		}
	}
	
	// チェックが一つだけ入っている場合は、編集ページヘ遷移する
	if (checkedCount === 1) {
		return focusTimerLink.href =`/getFocusTimer/${checkedId}`;
		
	// チェックがないまたは複数ある場合は、アラートを表示ページをリダイレクト	
	} else {
		const errorMsg = "タスクが未選択あるいは複数選択されています";
		alert(errorMsg);
		return focusTimerLink.href = '/listTasks';
	}
}