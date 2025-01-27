package com.example.domain.value_object;



/**
 * タスクID値オブジェクトクラス
 */
public class TaskId {
    private Long taskId;

    public TaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }
}
