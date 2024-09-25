/**
 * タイマー登録の操作
 */
$(document).ready(function () {
    /**
     * 登録データを送信する
     */
    $(".registarTimer").on("click", function () {
        // htmlに入力された値を格納する
        let pageChecked = $("#formSendCheckPageValue").val();
        let focusTimeInput = $("#focusTimeInput").val();
        let breakTimeInput = $("#breakTimeInputriorityInput").val();

        // 入力データをオブジェクトに格納
        let timer = {
            focusTime: focusTimeInput,
            breakTime: breakTimeInput,
        };
        // フォームの値をセットする
        $(".timerFormContainer input[ name = 'formSendCheckPageValue' ]").val(pageChecked);
        $(".timerFormContainer input[ name = 'focusTime' ]").val(timer.focusTime);
        $(".timerFormContainer input[ name = 'breakTime' ]").val(timer.breakTime);

        // フォームを送信
        $(".timerSettingForm").submit();
    });

  /**
   * タスク編集画面へ遷移する
	 */
  $(".taskEditRelation").on("click", function () {
    let selectedCheckbox = getSelectedCheckbox();

    if (selectedCheckbox) {
        // 選択されたタスクの情報を取得
        let taskId = $(selectedCheckbox).val();
        // タスク情報を使って編集画面にリダイレクト
        window.location.href = "/getEditTask/" + taskId;
    }
  });

  // 削除ボタンが押下された時の処理
  $(".taskDeleteRelation").on("click", function () {
    // チェックボックスクラスを取得する
    let checkedCheckboxes = $(".taskCheckbox:checked");
    // 削除フォームidを取得する
    let deleteForm = $("#deleteForm");

    // タスクの選択数をチェック
    if (checkedCheckboxes.length === 1) {
      // タスクを取得し、urlにリクエストする
      let taskId = checkedCheckboxes[0].value;
      deleteForm.attr("action", "/deleteTask/" + taskId);
      deleteForm.submit(); // フォームを送信する
    } else {
      // エラーメッセージを出力し、削除をキャンセルする
      const errorMsg = "タスクが未選択あるいは複数選択されています";
      alert(errorMsg);
      deleteForm.attr("action", "#");
    }
  });

    /**
     * タイマー管理初期画面へ遷移
     */
    $(".getTimerHome").on("click", function () {
        window.location.href = "/getTimer";
        return false;
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
        if (checkedCount !== 1) {
        const errorMsg = "タスクが未選択あるいは複数選択されています";
        alert(errorMsg);
        return null;
        }
        return checkboxes[0];
    };
});
