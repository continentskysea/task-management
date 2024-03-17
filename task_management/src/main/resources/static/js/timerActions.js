/**
 * タイマーの動きのフロント制御
 */

// 起動ボタンが押された時の処理
$("#startButton").on("click", function() {
	// タスクIDを取得
	let taskId = document.getElementsByName("taskId");
	// 表示されている要素を取得する
	let timerElement = document.getElementById("timerCount");
	// 表示されている初期時間を取得する
	let initialTime = timerElement.textContent;
	// タイマーの種類を取得
	let timerType = document.getElementsByName("timerType");
	// タイマー設定IDを取得
	let settingId = document.getElementsByClassName("settingId");

	// ストップボタンを活性化する
	$("#stopButton").prop("disabled", false);
	// ストップボタンが活性化された場合、スタートボタンを非活性する
	if($("#stopButton").prop("disabled", false)) {
		$("#startButton").prop("disabled", true);
		$("#listTasks").attr("disabled", true);
		$("#resetButton").prop("disabled", false);
	}
	console.log(initialTime);
	
	$.ajax({
		url: '/startTimer',
		type: 'POST',
		async: true,
		data: JSON.stringify({
			"taskId" :  taskId,
			"initialTime" : initialTime,
			"timerType" : timerType,
			"settingId" : settingId
		}),
		success: function(data) {
			console.log(data);
		},
		error: function(error) {
			console.error('Ther, has been a problem with your fetch operation:', error); // エラーが発生した場合の処理
		}
		
	});
	
	// : ごとに配列に格納
	let timeArray = initialTime.split(":");
	let hours = parseInt(timeArray[0]);
	let min = parseInt(timeArray[1]);
	let sec = parseInt(timeArray[2]);
	
	let totalTimeInSeconds = hours * 3600 + min * 60 + sec; // 初期時間を秒数に変換
	let countDownTimerId = setInterval(function() {
		// 残り時間を計算
		let hoursLeft = Math.floor(totalTimeInSeconds / 3600);
		let minLeft = Math.floor((totalTimeInSeconds % 3600) / 60);
		let secLeft = totalTimeInSeconds % 60;
		
		// 残り時間を表示
		timerElement.textContent = 
			formatTime(hoursLeft)+ ":"+ formatTime(minLeft) + ":" + formatTime(secLeft);
			
		// タイマーを1秒減少
		totalTimeInSeconds--;
		
		// 残り時間が0になったらフォームを自動的に送信
		if (totalTimeInSeconds < 0) {
			window.alert("時間です！再開しましょう！");
			clearInterval(countDownTimerId);
			document.getElementById("timerDisplayForm").submit();
		}
	}, 1000);
	
	// 停止ボタンがクリックされた時の処理
	$("#stopButton").on("click", function() {
		// インターバルを停止する
		clearInterval(countDownTimerId);
		$("#stopButton").prop("disabled", true);
		if ($("#stopButton").prop("disabled", true)) {
			$("#startButton").prop("disabled", false);
			$("#resetButton").prop("disabled", true);
			$("#listTasks").attr("disabled", false);
		}
		
	});

}); 

// リセットボタンがクリックされた時の処理
$("resetButton").on("click", function() {
	// 初期値の取得
	let initialTime = document.getElementById("timerCount").textContent;
	
	
	// Ajaxリクエストを送信
	$.ajax({
		url: '/resetTimer', // urlの指定
		type: 'POST', // メソッドの指定
		contentType: 'application/json', // ヘッダーの形式
		data: JSON.stringify({ 
			"taskId" : taskId,
			"initialTime": initialTime,
			"timerType" : timerType,
			"settingId" : settingId
			
		}), // データをJSON形式に変換
		success: function(data) { // 成功時
			console.log(data); // サーバーからレスポンスをログに出力
		},
		error: function(error) {
			console.error('Ther, has been a problem with your fetch operation:', error); // エラーが発生した場合の処理
		}
		
	});
});

// 時間のフォーマットを調整する関数
function formatTime(time) {
	return time < 10 ? "0" + time : time;
}


/**
	必要な処理
	タイマーとスタートする処理
		=> 表示されている値を取得する
		=> 取得したカウントダウンさせる
		=> 9まで数字がカウントされたら再び0から計測される
		=> 時間が来たらすべて0にする
	タイマー止める
	タイマーをリセットする
	表示されている
 */
