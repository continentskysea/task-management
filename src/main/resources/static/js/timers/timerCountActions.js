/**
 * タイマーカウントの制御
 */
$(document).ready(function() {
	// 起動ボタンが押された時の処理
	$("#startButton").on("click", function() {
		// 現在のタイマーが集中タイマーかどうか
		let isFocusTimer = true;
		handleStartButton();

		// 表示されている要素を取得する
		let timerElement = document.getElementById("timerCount");
		// 表示されている初期時間を取得する
		let initialTime = timerElement.textContent;
		// 初期化用の時間(リセットと初期表示の際に使用)
		const immutableInitialTime = timerElement.textContent;

		 // タスクID
		let taskId = parseInt($('#taskId').val());
		// タイマーの種類を判定する
		let timerPageNumber = parseInt($('#timerPageNumber').val()); 
		

		
		// : ごとに配列に格納
		let timeArray = initialTime.split(":");
		let hours = parseInt(timeArray[0]);
		let min = parseInt(timeArray[1]);
		let sec = parseInt(timeArray[2]);
		
		let totalTimeInSeconds = hours * 3600 + min * 60 + sec; // 初期時間を秒数に変換

		// カウントダウンを実行
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

				checkingTimerType(isFocusTimer, timerPageNumber, taskId);
				
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

			
		/**
		 * 
		 * @param {boolean} isFocusTimerFlag 集中タイマーかどうかを判定する値
		 * @param {number} timerPageNumber 集中タイマー/休憩タイマーのページを判別する値
		 * @param {number} taskId タスクID
		 * 
		 * @returns フォーカスタイマーかどうか, OKボタンが押下された際に次に表示されるページ, キャンセルボタンが押下された際に表示されるページ
		*/
		function checkingTimerType(isFocusTimerFlag, timerPageNumber, taskId) {
			
			const isFocusTimer = timerPageNumber === 1; 
			const nextUrl = isFocusTimerFlag ? '/getBreakTimer/' + taskId : '/getFocusTimer/' + taskId;
			const prevUrl = isFocusTimerFlag ? '/getFocusTimer/' + taskId : '/getBreakTimer/' + taskId;
			
			return { isFocusTimer, nextUrl, prevUrl };
		}

		/**
		 * タイマー終了時の動きを制御する関数
		 */
		const handleTimerEnd = () => {
			const { isFocusTimer, nextUrl, prevUrl } = checkingTimerType(isFocusTimer, timerPageNumber, taskId);

			// OKボタンが押下されたらタイマー画面を遷移する
			if (confirm("時間です!タイマーを切り替えますか?")) {
				// console.log(nextUrl);
				window.location.href = nextUrl;
			} else {
				window.location.href = prevUrl;
			}
		}
		
	});



	


	/**
	 * 	時間のフォーマットを調整する関数
	 * 
	 * @param {string} time
	 *  
	 * @returns 文字列連結された時間表示
	 */
		const formatTime = (time) => {
			return time < 10 ? "0" + time : time;
		}
	
		
	/**
	 * 開始ボタンの操作を制御する関数
	*/
	const handleStartButton = () => {
		// 停止ボタンを活性にする
		$("#stopButton").prop("disabled", false);
		// 停止ボタンが活性された場合
		if($("#stopButton").prop("disabled", false)) {
			$("#startButton").prop("disabled", true); // 起動ボタンの非活性
			$("#listTasks").attr("disabled", true); // 戻るボタンの非活性
			$("#resetButton").prop("disabled", false); // リセットボタンの活性
		}
	}

	/**
	 * 停止ボタンの操作を制御する関数
	 */
	const handleStopButton = () => {
		$("#stopButton").prop("disabled", true);
		// 停止ボタンが非活性になった場合
		if ($("#stopButton").prop("disabled", true)) {
			$("#startButton").prop("disabled", false); // 起動ボタンを活性
			$("#listTasks").attr("disabled", false); // 戻るボタンを活性
			$("#resetButton").prop("disabled", true); // リセットボタンを非活性
		}
	}

});
