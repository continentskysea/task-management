/**
 * タイマー管理初期画面の操作
 */
$(document).ready(function () {
    /**
     * タイマー登録画面ヘ遷移する
     */
    $(".getTimerSetting").on("click", function () {
        window.location.href = "/getTimerSetting";
        return false;
    });
    /**
     * タスク一覧画面へ遷移する
     */
    $(".getTimerlist").on("click", function () {
        window.location.href = "/getTimerlist";
        return false;
    });

    /**
     * ホーム画面へ遷移する
     */
    $(".getHome").on("click", function () {
        window.location.href = "/home";
        return false;
    });
});
