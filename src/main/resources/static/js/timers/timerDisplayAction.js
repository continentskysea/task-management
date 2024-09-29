/**
 * 集中タイマー表示に関する操作
 */
$(document).ready(function() {

	/**
		集中タイマー画面に遷移する
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
