package com.example.domain.value_object.timer;

/**
 * 集中時間値オブジェクト
 */
public class FocusTime {
    private String focusTime;

    public FocusTime(String focusTime) {
        this.focusTime = focusTime;
    }

    private boolean timerInputCheck(String focusTime) {
        return focusTime.equals("") || focusTime != null ;
    }

    private String timerInputCheckVertificationResult(String focusTime) {
        return timerInputCheck(focusTime) ? "" : "集中タイマーを入力してください";
    }

    public String getFoucsTime() {
        timerInputCheckVertificationResult(focusTime);
        return focusTime;
    }
}
