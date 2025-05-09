package com.example.domain.value_object.task;



/**
 * タスクID値オブジェクトクラス
 */
public class TaskId {
    private final Long taskId;

    public TaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }
}
