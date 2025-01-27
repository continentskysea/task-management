package com.example.domain.value_object;

import java.time.LocalDate;

/**
 * 期日値オブジェクトクラス
 */
public class TaskDueDate {
    private LocalDate taskDueDate;

    public TaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }
}
