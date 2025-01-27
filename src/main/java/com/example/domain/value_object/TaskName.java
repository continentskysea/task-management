package com.example.domain.value_object;

/**
 * タスク名値オブジェクトクラス
 */
public class TaskName {
    private String taskName;

    public TaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }
}
