/**
 * タスク管理初期画面の操作
 */
$(document).ready(function () {
    /**
     * タスク登録画面ヘ遷移する
     */
    $(".getCreateTask").on("click", function () {
        window.location.href = "/getCreateTask";
        return false;
    });
    /**
     * タスク一覧画面へ遷移する
     */
    $(".getListTasks").on("click", function () {
        window.location.href = "/getListTasks";
        return false;
    });

    /**
     * タスク一覧画面へ遷移する
     */
    $(".getHome").on("click", function () {
        window.location.href = "/home";
        return false;
    });
});
