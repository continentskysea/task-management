/**
 * タスク選択削除用
 */
 // チェックボックスのクラス名を取得
 let checkboxes = document.getElementsByClassName('taskCheckbox');
 // 削除フォームのidを取得
 let deleteForm = document.getElementById('deleteForm');
 
 
 function updateDeleteLink() {
	for (let i = 0; i< checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			deleteForm.action = '/deleteTask/'+ checkboxes[i].value;
			return;
		}
	}
	deleteForm.action = '#';
}
	
for (let i = 0; i < checkboxes.length; i++) {
	checkboxes[i].addEventListener('change', updateDeleteLink);
}