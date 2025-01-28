package com.example.domain.value_object;

/**
 * 優先順位値オブジェクトクラス
 */
public class TaskPriority {
    private Long taskPriority;

    public TaskPriority(Long taskPriority) {
        this.taskPriority = taskPriority;
    }

    /**
     * 優先順位の数値をチェックする
     * @param taskPriority
     * @return true = 1文字以上30文字以内 / false = それ以外
     */
    private boolean taskPriorityInput(Long taskPriority) {
        return taskPriority >= 1 && taskPriority <= 30;
    }

    /**
     * 優先順位の検証結果を返す
     * @param taskPriority
     * @return 検証結果
     */
    private String taskPriorityInputVertificationResult(Long taskPriority) {
        return taskPriorityInput(taskPriority) ? "" : "優先順位は1から30までの数字を入力してください";
    }

    public Long getTasKPriority(Long taskPriority) {
        taskPriorityInputVertificationResult(taskPriority);
        return taskPriority;
    }
}
