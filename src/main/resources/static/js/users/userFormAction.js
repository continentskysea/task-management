/**
 * 登録フォームの操作
 */
$(document).ready(function () {
    // ユーザー管理初期画面へ遷移
    $(".getUserHome").on("click", function () {
        window.location.href = "/getUserHome";
        return false;
    });
});