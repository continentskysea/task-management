/**
 * タスク編集画面表示用
 */
 // 選択されたチェックボックスに対応するリンクを生成する関数
 function generateLink() {
	// すべてのチェックボックスを取得
	let checkboxes = document.getElementsByName('taskIds');
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
		document.getElementById('editLink').href =`/editTask/${checkedId}`;
		
	// チェックがないまたは複数ある場合は、アラートを表示ページをリダイレクト
	} else {
		const errorMsg = "タスクが未選択あるいは複数選択されています";
		alert(errorMsg);
		document.getElementById('editLink').href =`/listTasks`;
	}
}