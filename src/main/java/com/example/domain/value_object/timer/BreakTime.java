
package com.example.domain.value_object.timer;

public class BreakTime {
    private String breakTime;

    public BreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    private boolean breakTimerInputCheck(String breakTime) {
        return breakTime.equals("") || breakTime != null ;
    }

    private String timerInputCheckVertificationResult(String breakTime) {
        return breakTimerInputCheck(breakTime) ? "" : "集中タイマーを入力してください";
    }

    public String getBreakTime() {
        timerInputCheckVertificationResult(breakTime);
        return breakTime;
    }
}
