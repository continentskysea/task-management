package com.example.domain.value_object;

import java.time.LocalDateTime;

/**
 * 登録日時値オブジェクトクラス
 */
public class RegistarAt {
    private LocalDateTime registarAt;

    public RegistarAt(LocalDateTime registarAt) {
        this.registarAt = registarAt;
    }

    public LocalDateTime LocalDateTime() {
        return registarAt;
    }
}
