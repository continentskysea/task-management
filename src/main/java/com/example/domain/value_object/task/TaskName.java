package com.example.domain.value_object.task;


import org.springframework.util.StringUtils;

/**
 * タスク名値オブジェクトクラス
 */
public class TaskName {
    private String taskName;

    public TaskName(String taskName) {
        // 入力チェック
        if (!hasInputted(taskName)) throw new TaskNameNotInputException();

        this.taskName = taskName;
    }

    /**
     * タスク名入力チェック機能
     * @param taskName
     * @return true = 入力されている / false = 0及びnull
     */
    private boolean hasInputted(String taskName) {
        return StringUtils.hasLength(taskName);
    }

    public String getTaskName() {
        return this.taskName;
    }
}
