// userActions.js

$(document).ready(function() {
	/**
		新規登録画面に遷移する
	 */
	$("#createUser").on("click", function() {
		window.location.href = '/getCreateUser';
	});
	
	/**
		編集画面に遷移する
	 */
	$("#editUser").on("click", function() {
		let selectedCheckbox = getSelectedCheckbox();
		
		if (selectedCheckbox) {
			// 選択されたユーザー情報を取得
			let userId = $(selectedCheckbox).val();
			// タスク情報を使って休憩タイマー画面にリダイレクト
			window.location.href = '/getEditUser/' + userId;
		}
	});
	
	/**
		タスク編集画面へ遷移する
	 */
	$(".taskEditRelation").on("click", function() {
		let selectedCheckbox = getSelectedCheckbox();
	
		if (selectedCheckbox) {
	        // 選択されたタスクの情報を取得
			let taskId = $(selectedCheckbox).val();
	        // タスク情報を使って編集画面にリダイレクト
			window.location.href = '/editUser/' + taskId;
		}
	});
	/**
		チェックされた要素を調べる
	 */
	function getSelectedCheckbox() {
		let checkboxes = $("input.userCheckbox:checked");
		let checkedCount = checkboxes.length;

		// チェックの要素が1つだけか確認する
		if (checkedCount  !== 1) {
			const errorMsg = "ユーザーが未選択あるいは複数選択されています";
			alert(errorMsg);
			return null;
		}
		return checkboxes[0];
	} 

	// ユーザー削除
	$("#deleteUser").on("click", function() {
		// チェックボックスクラスを取得する
		let checkedCheckboxes = $('.userCheckbox:checked');
		// 削除フォームidを取得する
		let userDeleteForm = $('#userDeleteForm');
		
		// タスクの選択数をチェック
		if (checkedCheckboxes.length > 0) {
			
			// ユーザーを取得し、urlにリクエストする
			let userId = checkedCheckboxes[0].value;
			userDeleteForm.attr('action', '/deleteUser/' + userId); 
			userDeleteForm.submit();// フォームを送信する
		} else {
			const errorMsg = "ユーザーが未選択です";
			alert(errorMsg);
			userDeleteForm.attr('action', '#');
		}
	});
});