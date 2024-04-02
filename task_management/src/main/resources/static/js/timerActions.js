/**
 * タイマーの動きのフロント制御
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
		
		
		// 停止ボタンを活性化する
		$("#stopButton").prop("disabled", false);
		// 停止ボタンが活性化された場合
		if($("#stopButton").prop("disabled", false)) {
			$("#startButton").prop("disabled", true); // 起動ボタンの非活性
			$("#listTasks").attr("disabled", true); // 戻るボタンの非活性
			$("#resetButton").prop("disabled", false); // リセットボタンの活性
		}
		
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
			
			
			// 残り時間が0になったらフォームを自動的に送信
			if (totalTimeInSeconds < 0) {
				clearInterval(countDownTimerId);
				$("#stopButton").prop("disabled", true);
				if ($("#stopButton").prop("disabled")) {
					$("#startButton").prop("disabled", false)
					$("#listTasks").attr("disabled", false);
					$("#resetButton").prop("disabled", true);
					
				}
				window.alert("時間です！");
				// : ごとに配列に格納
				const immutableTimeArray = immutableInitialTime.split(":");
				const immutableHours = parseInt(immutableTimeArray[0]);
				const immutableMin = parseInt(immutableTimeArray[1]);
				const immutableSec = parseInt(immutableTimeArray[2]);
				// 初期登録時間を表示
				timerElement.textContent = 
				formatTime(immutableHours)+ ":"+ formatTime(immutableMin) + ":" + formatTime(immutableSec);
				return; // タイマーを強制狩猟し、-1を描画させない
			}
			// タイマーを1秒減少
			totalTimeInSeconds--;
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
	// 時間のフォーマットを調整する関数
	function formatTime(time) {
		return time < 10 ? "0" + time : time;
	}
	/**
	タイマー編集画面へ遷移する
	*/
	$(".timerEditRelation").on("click", function() {
		let selectedCheckbox = getSelectedCheckbox();
		
		if (selectedCheckbox) {
			// 選択されたタスクの情報を取得
			let timerId = $(selectedCheckbox).val();
			// タスク情報を使って編集画面にリダイレクト
			window.location.href = '/getEditTimer/' + timerId;
		}
	});
	// 削除ボタンが押下された時の処理
	$(".timerDeleteRelation").on("click", function() {
		// チェックボックスクラスを取得する
		let checkedCheckboxes = $('.taskCheckbox:checked');
		// 削除フォームidを取得する
		let deleteForm = $('#deleteForm');
		
		// タスクの選択数をチェック
		if (checkedCheckboxes.length > 0) {
			
			// タイマーを取得し、urlにリクエストする
			let timerId = checkedCheckboxes[0].value;
			tiemrDeleteForm.attr('action', '/deleteTimer/' + timerId); 
			tiemrDeleteForm.submit();// フォームを送信する
		} else {
			const errorMsg = "タイマーが未選択です";
			alert(errorMsg);
			tiemrDeleteForm.attr('action', '#');
		}
	});
	// タイマー管理初期画面へ遷移
	$(".timerHomeRelation").on("click", function() {
		window.location.href = '/getTimer';
	});
	/**
	チェックされた要素を調べる
	*/
	function getSelectedCheckbox() {
	let checkboxes = $("input.timerCheckbox:checked");
	let checkedCount = checkboxes.length;
	
	// チェックの要素が1つだけか確認する
	if (checkedCount  !== 1) {
		const errorMsg = "タイマーが未選択あるいは複数選択されています";
		alert(errorMsg);
		return null;
	}
	
	return checkboxes[0];
	}
});