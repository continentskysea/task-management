package com.example.domain.value_object.timer;

import java.time.LocalDateTime;

/**
 * 登録日時値オブジェクトクラス
 */
public class RegistarAt {
    private final LocalDateTime registarAt;

    public RegistarAt(LocalDateTime registarAt) {
        this.registarAt = registarAt;
    }

    public LocalDateTime getRegistarAt() {
        return this.registarAt;
    }
}
