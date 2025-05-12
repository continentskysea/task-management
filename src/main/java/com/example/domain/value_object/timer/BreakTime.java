package com.example.domain.value_object.timer;

import org.apache.commons.lang3.StringUtils;

/**
 * 休憩時間値オブジェクト
 */
public class BreakTime {
    private final static String TIMER_REGEX = "/^(?:2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$/";
    private String breakTime;

    public BreakTime(String breakTime) {
        // 未入力チェック
        if (!hasInputted(breakTime)) throw new TimerNotInputException("休憩タイマーを入力してください");
        // 形式チェック
        if (!isTimeValid(breakTime)) throw new TimerFormatException("休憩タイマーの形式が正しくありません。正しい形式は00:00:00です。");

        this.breakTime = breakTime;
    }

    /**
     * 休憩タイマー入力チェック機能
     * @param breakTime
     * @return true = 入力されている / false = 0及びnull
     */
    private boolean hasInputted(String breakTime) {
        return StringUtils.isNotEmpty(breakTime);
    }

    /**
     * タイマーの入力形式チェック機能
     * @param breakTime
     * @return true = 00:00:00の形式 / 00:00:00形式ではない
     */
    private boolean isTimeValid(String breakTime) {
        return TIMER_REGEX.equals(breakTime);
    }


    public String getBreakTime() {
        return this.breakTime;
    }
}
