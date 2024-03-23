/**
 * タスク選択削除用
 */
 
 $(".taskRelation").on("click", function() {
	// チェックボックスクラスを取得する
	let checkedCheckboxes = $('.taskCheckbox:checked');
	// 削除フォームidを取得する
	let deleteForm = $('#deleteForm');
	
	// タスクの選択数をチェック
	if (checkedCheckboxes.length > 0) {
		
		// タスクを取得し、urlにリクエストする
		let taskId = checkedCheckboxes[0].value;
		deleteForm.attr('action', '/deleteTask/' + taskId); 
		deleteForm.submit();// フォームを送信する
	} else {
		const errorMsg = "タスクが未選択です";
		alert(errorMsg);
		deleteForm.attr('action', '#');
	}
});