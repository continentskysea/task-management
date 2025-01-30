package com.example.domain.value_object.timer;

import java.util.Timer;

/**
 * 集中タイマー
 */
public class TimerId {
    private Long timerId;

    public TimerId(Long timerId) {
        this.timerId = timerId;
    }

    public Long getTimerID() {
        return timerId;
    }
}
