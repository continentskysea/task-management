package com.example.domain.value_object.task.due_date;

import java.time.LocalDate;

/**
 * 期日値オブジェクトクラス
 */
public class DueDate {
    private LocalDate dueDate;

    public DueDate(LocalDate dueDate) {
        // 入力チェック
        if (!hasInputted(dueDate)) throw new DueDateNotInputException();

        this.dueDate = dueDate;
    }

    /**
     * 期日の入力チェック機能
     * @param dueDate
     * @return true = 入力済 / false = 未入力
     */
    private boolean hasInputted(LocalDate duDate) {
        return dueDate != null;
    }


    public LocalDate getTaskDueDate() {
        return dueDate;
    }
}
