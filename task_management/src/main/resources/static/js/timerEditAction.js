/**
 * タイマー編集
 */
$(document).ready(function() {
	/**
	タイマー編集画面へ遷移する
	*/
	$(".timerEditRelation").on("click", function() {
		let selectedCheckbox = getSelectedCheckbox();
		
		if (selectedCheckbox) {
			// 選択されたタスクの情報を取得
			let timerId = $(selectedCheckbox).val();
			// タスク情報を使って編集画面にリダイレクト
			window.location.href = '/getEditTimer/' + timerId;
		}
	});

	// 削除ボタンが押下された時の処理
	$(".timerDeleteRelation").on("click", function() {
		// チェックボックスクラスを取得する
		let checkedCheckboxes = $('.timerCheckbox:checked');
		// 削除フォームidを取得する
		let timerDeleteForm = $('#timerDeleteForm');
		
		// タスクの選択数をチェック
		if (checkedCheckboxes.length === 1) {
			
			// タイマーを取得し、urlにリクエストする
			let timerId = checkedCheckboxes[0].value;
			timerDeleteForm.attr('action', '/deleteTimer/' + timerId); 
			timerDeleteForm.submit();// フォームを送信する
		} else {
			const errorMsg = "タイマーが未選択あるいは複数選択されています";
			alert(errorMsg);
			timerDeleteForm.attr('action', '#');
		}
	});

	// タイマー管理初期画面へ遷移
	$(".timerHomeRelation").on("click", function() {
		window.location.href = '/getTimer';
	});

	/**
	チェックされた要素を調べる
	*/
	function getSelectedCheckbox() {
		let checkboxes = $("input.timerCheckbox:checked");
		let checkedCount = checkboxes.length;
		
		// チェックの要素が1つだけか確認する
		if (checkedCount  !== 1) {
			const errorMsg = "タイマーが未選択あるいは複数選択されています";
			alert(errorMsg);
			return null;
		}
		
		return checkboxes[0];
	}
});