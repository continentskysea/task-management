/**
 * タイマーの動作制御
 */
$(document).ready(function() {
	// 起動ボタンが押された時の処理
	$("#startButton").on("click", function() {
		
		// 表示されている要素を取得する
		let timerElement = document.getElementById("timerCount");
		// 表示されている初期時間を取得する
		let initialTime = timerElement.textContent;
		// 初期化用の時間
		const immutableInitialTime = timerElement.textContent;
		
		handleStartButton();
		
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
			
			// 残り時間を表示(hoursLef : minLeft :  secLeft)
			timerElement.textContent = formatTime(hoursLeft)+ ":"+ formatTime(minLeft) + ":" + formatTime(secLeft);
			
			
			// 残り時間が0になったらフォームを自動的に送信
			if (totalTimeInSeconds < 0) {
				// タイマーを停止
				clearInterval(countDownTimerId);
				// }
				//　confirmダイアログを表示
				let isFocusTimer = true; // 現在のタイマーが集中タイマーかどうか
				let taskId = parseInt($('#taskId').val()); // タスクID
				let timerPageNumber = parseInt($('#timerPageNumber').val()); // タイマーの種類を判定する

				checkingTimerType(timerPageNumber, taskId);
				handleTimerEnd();

				// : ごとに配列に格納
				const immutableTimeArray = immutableInitialTime.split(":");
				const immutableHours = parseInt(immutableTimeArray[0]);
				const immutableMin = parseInt(immutableTimeArray[1]);
				const immutableSec = parseInt(immutableTimeArray[2]);
				// 初期登録時間を表示
				timerElement.textContent = 
				formatTime(immutableHours)+ ":"+ formatTime(immutableMin) + ":" + formatTime(immutableSec);
				return; // タイマーを強制終了し、-1を描画させない
			}
			// タイマーを1秒減少
			totalTimeInSeconds--;
		}, 1000);
		// 停止ボタンがクリックされた時の処理
		$("#stopButton").on("click", function() {
			// インターバルを停止する
			clearInterval(countDownTimerId);
			handleStopButton();			
		});
	});

	/**
	 * 	時間のフォーマットを調整する関数
	 * @param {string} time 
	 * @returns 文字列連結された時間表示
	 */
	const formatTime = (time) => {
		return time < 10 ? "0" + time : time;
	}

	// タイマーの種類を判定する関数? => 
	/**
	 * 
	 * @param {number} timerPageNumber 集中タイマー/休憩タイマーの判定値
	 * @param {number} taskId タスクID
	 * 
	 * @returns 集中タイマーかどうか URL
	 */
	function checkingTimerType(timerPageNumber, taskId) {

		const isFocusTimer = timerPageNumber === 1;
		const nextUrl = isFocusTimer ? '/getBreakTimer/' + taskId : '/getFocusTimer/' + taskId;
		const prevUrl = isFocusTimer ? '/getFocusTimer' + taskId : '/getBreakTimer/' + taskId;

		return { isFocusTimer, nextUrl, prevUrl };
	}

	/**
	 * 停止ボタンの操作
	 */
	const handleStartButton = () => {
		// 停止ボタンを活性化する
		$("#stopButton").prop("disabled", false);
		// 停止ボタンが活性化された場合
		if($("#stopButton").prop("disabled", false)) {
			$("#startButton").prop("disabled", true); // 起動ボタンの非活性
			$("#listTasks").attr("disabled", true); // 戻るボタンの非活性
			$("#resetButton").prop("disabled", false); // リセットボタンの活性
		}
	}

	const handleStopButton = () => {
		$("#stopButton").prop("disabled", true);
		if ($("#stopButton").prop("disabled", true)) {
			$("#startButton").prop("disabled", false);
			$("#resetButton").prop("disabled", true);
			$("#listTasks").attr("disabled", false);
		}
	}

	const handleTimerEnd = () => {
		const { isFocusTimer, nextUrl, prevUrl } = checkingTimerType(timerPageNumber);

		if (confirm("時間です!タイマーを切り替えますか?")) {
			window.location.href = nextUrl;
		} else {
			window.location.href = prevUrl;
		}
	}
});
