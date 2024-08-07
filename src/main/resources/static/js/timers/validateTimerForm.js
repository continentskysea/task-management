/**
 * タイマー登録のバリデーション
*/
$(document).ready(function() {
    $("button").on("click", function() {
        // フォームから入力値を取得
        let focusTime = $("#focusTime").val();
        let breakTime = $("#breakTime").val();
        // バリデーションフラグ
        let isValid = true;
        
        /**
         * タイマーの形式が正しいかチェックする関数
         * 
         * @param {string} timeString 
         * @returns タイマー情報のバリデーション結果
         */
        const validateTimeFormat = (timeString) => {
            let regex = /^(?:2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$/;
            return regex.test(timeString);
        }

        if (focusTime === "" || focusTime == null) {
            alert("集中タイマーを入力してください");
            isValid = false;
            return isValid;
        }

        if (breakTime === "" || breakTime == null) {
            alert("休憩タイマーを入力してください");
            isValid = false;
            return isValid;
        }
        
        // タイマーのバリデーション
        if (!validateTimeFormat(focusTime)) {
            alert("集中タイマーの形式が正しくありません。正しい形式は00:00:00です。");
            isValid = false;
            return isValid;
        }
        
        if (!validateTimeFormat(breakTime)) {
            alert("休憩タイマーの形式が正しくありません。正しい形式は00:00:00です。");
            isValid = false;
            return isValid;
        }
        
        /**
         * 取得したタイマー情報(文字列)を秒数に変換する関数
         * @param {string} timeString 
         * @returns ミリ秒数に変換したタイマー情報
         */
        const parseTime = (timeString) => {
            let parts = timeString.split(":");
            let hours = parseInt(parts[0]);
            let minutes = parseInt(parts[1]);
            let seconds = parseInt(parts[2]);
            return hours * 3600 + minutes * 60 + seconds;
        }

        // 24:00:00でのバリデーション
        if (parseTime(focusTime) >= 86400  || parseTime(focusTime) < 0 || focusTime === "00:00:00") {
            alert("集中タイマーには00:00:01から24:00:00までの時間を入力してください");
            isValid = false;
            return isValid;
        }
        
        if (parseTime(breakTime) >= 86400  || parseTime(breakTime) < 0 || breakTime=== "00:00:00") {
            alert("休憩タイマーには00:00:01から24:00:00までの時間を入力してください");
            isValid = false;
            return isValid;
        }
        // フォームエラー送信を中止する
        return isValid;	

    });
});
