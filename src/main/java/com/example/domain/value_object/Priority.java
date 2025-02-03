package com.example.domain.value_object;

/**
 * 優先順位値オブジェクトクラス
 */
public class Priority {
    private Long priority;

    public Priority(Long priority) {
        this.priority = priority;
    }

    /**
     * 優先順位の数値をチェックする
     * @param priority
     * @return true = 1文字以上30文字以内 / false = それ以外
     */
    private boolean priorityInput(Long priority) {
        return priority >= 1 && priority <= 30;
    }

    /**
     * 優先順位の検証結果を返す
     * @param taskPriority
     * @return 検証結果
     */
    private String priorityInputVertificationResult(Long priority) {
        return priorityInput(priority) ? "" : "優先順位は1から30までの数字を入力してください";
    }

    public Long getTasKPriority() {
        priorityInputVertificationResult(priority);
        return priority;
    }
}
