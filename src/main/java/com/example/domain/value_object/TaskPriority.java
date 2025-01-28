package com.example.domain.value_object;

/**
 * 優先順位値オブジェクトクラス
 */
public class TaskPriority {
    private Long taskPriority;

    public TaskPriority(Long taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Long getTasKPriority() {
        return taskPriority;
    }
}
