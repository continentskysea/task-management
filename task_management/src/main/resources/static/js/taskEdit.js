/**
 * タスク編集画面表示用
 */
function generateLink(checkedId) {
    // "null" でないことを確認してから数値に変換
    checkedId = checkedId !== null && checkedId !== "null" ? parseInt(checkedId) : null;
    // エラーメッセージ
    const errorMsg = "タスクが未選択あるいは複数選択されています";

    // チェックが一つだけ入っている且つ集中タイマーをクリック場合は、集中タイマーページヘ遷移する
    if (checkedId !== null && !isNaN(checkedId)) {
        // 集中タイマーボタン
        const focusTimerLink = document.getElementById('focusTimerLink');
        // 編集ボタン
        const editLink = document.getElementById('editLink');

        if (focusTimerLink.href) {
            return handleLink(focusTimerLink.href, checkedId);
        } else if (editLink.href) {
            return handleLink(editLink.href, checkedId);
        }
    } else {
        alert(errorMsg);
        const focusTimerLink = document.getElementById('focusTimerLink');
        const editLink = document.getElementById('editLink');
        focusTimerLink.href = `/listTasks`;
        editLink.href = `/listTasks`;
    }
}

function handleLink(link, checkedId) {
    window.location.href = `${link}/${checkedId}`;
}
