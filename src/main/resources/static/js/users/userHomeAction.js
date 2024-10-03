/**
 * ユーザー管理ホーム画面の操作
 */
$(document).ready(function() {
	// 新規登録画面に遷移する
	$(".getCreateUser").on("click", function() {
		window.location.href = '/getCreateUser';
        return false;
	});
    
    // ユーザー一覧画面遷移する
    $(".getListUsers").on("click", function() {
        window.location.href = '/getListUsers';
        return false;
    });
    
    // システム管理者初期画面へ遷移
    $(".getAdminHome").on("click", function() {
        window.location.href ='/getAdminHome';
        return false;
    });
	
});