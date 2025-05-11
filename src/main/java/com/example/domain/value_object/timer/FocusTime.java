package com.example.domain.value_object.timer;

import org.apache.commons.lang3.StringUtils;

/**
 * 集中時間値オブジェクト
 */
public class FocusTime {
    private String focusTime;
    private final static String TIMER_REGEX = "/^(?:2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$/";

    public FocusTime(String focusTime) {
        if (!hasInputted(focusTime)) throw new FocusTimerNotInputException("集中タイマーを入力してください");
        if (!isTimeValid(focusTime)) throw new FocusTimerFormatException("集中タイマーの形式が正しくありません。正しい形式は00:00:00です。");
        this.focusTime = focusTime;
    }


    /**
     * 集中タイマー入力チェック機能
     * @param focusTime
     * @return true = 入力されている / false = 0及びnull
     */
    private boolean hasInputted(String focusTime) {
        return StringUtils.isNotEmpty(focusTime);
    }

    /**
     * タイマーの形式チェック機能
     * @param focusTime
     * @return true = 00:00:00の形式 / 00:00:00形式ではない
     */
    private boolean isTimeValid(String focusTime) {
        return TIMER_REGEX.equals(focusTime);
    }

    public String getFoucsTime() {
        
        return this.focusTime;
    }
}
