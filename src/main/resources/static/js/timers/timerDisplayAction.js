/**
 * タイマー画面の表示の動きに関して制御する
 */
$(document).ready(function() {
	
	/**
	 * タイマー登録画面に遷移する
	 */
	$(".settingTimerRelation").on("click", function() {
		window.location.href = '/getTimerSetting';
	});
	/**
		集中タイマー画面に遷移する
	 */
	$(".focusTimerRelation").on("click", function() {
		let selectedCheckbox = getSelectedCheckbox();
	
		if (selectedCheckbox) {
	        // 選択されたタスクの情報を取得
			let taskId = $(selectedCheckbox).val();
	        // タスク情報を使って集中タイマー画面にリダイレクト
			window.location.href = '/getFocusTimer/' + taskId;
		}
	});
	
	/**
		休憩タイマー画面に遷移する
	 */
	$(".breakTimerRelation").on("click", function() {
		let selectedCheckbox = getSelectedCheckbox();
		
		if (selectedCheckbox) {
			// 選択されたタスク情報を取得
			let taskId = $(selectedCheckbox).val();
			// タスク情報を使って休憩タイマー画面にリダイレクト
			window.location.href = '/getBreakTimer/' + taskId;
		}
	});
	
	/**
	チェックされた要素を調べる
	*/
	function getSelectedCheckbox() {
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
