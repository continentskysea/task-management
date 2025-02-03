package com.example.domain.value_object;

import java.time.LocalDate;

/**
 * 期日値オブジェクトクラス
 */
public class DueDate {
    private LocalDate dueDate;

    public DueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getTaskDueDate() {
        return dueDate;
    }
}
