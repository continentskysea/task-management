package com.example.domain.value_object.timer;

import java.time.LocalDateTime;

/**
 * タイマー登録日時値オブジェクトクラス
 */
public class TimerRegistarAt {
    private LocalDateTime timerRegistarAt;

    public TimerRegistarAt(LocalDateTime timerRegistarAt) {
        this.timerRegistarAt = timerRegistarAt;
    }

    public LocalDateTime LocalDateTime() {
        return timerRegistarAt;
    }
}
