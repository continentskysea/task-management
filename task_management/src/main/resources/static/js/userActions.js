// userActions.js

$(document).ready(function() {
	/**
		新規登録画面に遷移する
	 */
	$(".createUserRelation").on("click", function() {
		window.location.href = '/getCreateUser';
	});
	
	/**
		編集画面に遷移する
	 */
	$(".editUserRelation").on("click", function() {
		let selectedCheckbox = getSelectedCheckbox();
		
		if (selectedCheckbox) {
			// 選択されたユーザー情報を取得
			let userId = $(selectedCheckbox).val();
			// タスク情報を使って休憩タイマー画面にリダイレクト
			window.location.href = '/getEditUser/' + userId;
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
	$(".deleteUserRelation").on("click", function() {
		// チェックボックスクラスを取得する
		let checkedCheckboxes = $('.userCheckbox:checked');
		// 削除フォームidを取得する
		let userDeleteForm = $('#userDeleteForm');
		
		// ユーザーの選択数をチェック
		if (checkedCheckboxes.length === 1) {
			
			// ユーザーを取得し、urlにリクエストする
			let userId = checkedCheckboxes[0].value;
			userDeleteForm.attr('action', '/deleteUser/' + userId); 
			userDeleteForm.submit();// フォームを送信する
		} else {
			const errorMsg = "ユーザーが未選択あるいは複数選択されています";
			alert(errorMsg);
			userDeleteForm.attr('action', '#');
		}
	});
	// ユーザー管理初期画面へ遷移
	$(".userHomeRelation").on("click", function() {
		window.location.href ='/getUserHome';
	});
});