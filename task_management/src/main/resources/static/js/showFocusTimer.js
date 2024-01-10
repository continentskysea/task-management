/**
 * 集中タイマー画面表示用
 */
 // 選択されたチェックボックスに対応するリンクを生成する関数
 function generateFocusTimerLink() {
	// すべてのチェックボックスを取得
	let checkboxes = document.getElementsByName('taskIds');
	for (let i = 0; i < checkboxes.length; i++) {
		// チェックボックスが選択されている場合
		if (checkboxes[i].checked) {
			// idを取得してリンクを生成
			let id = checkboxes[i].value;
			document.getElementById('focusTimerLink').href =`/getFocusTimer/${id}`;
		}
	}
}