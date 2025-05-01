package com.example.domain.value_object;

/**
 * 優先順位値オブジェクトクラス
 */
public class Priority {
    private Long priority;
    private static int PRIORITY_MIN = 1;
    private static int PRIORITY_MAX = 30;

    public Priority(Long priority) {
        // 未入力チェック
        if(!hasInputted(priority)) throw new PriorityNotInputException();
        // 数値範囲のチェック
        if (!isPriorityNumberRange(priority))  throw new PriorityNumberException();

        this.priority = priority;
    }

    /**
     * 優先順位の数値をチェックする
     * @param priority
     * @return true = 1文字以上30文字以内 / false = それ以外
     */
    private boolean isPriorityNumberRange(Long priority) {
        return priority >= PRIORITY_MIN && priority <= PRIORITY_MAX;
    }

    /**
     * 入力されているかチェック
     * @param priority
     * @return true = 入力されている / false = 巳乳慮k
     */
    private boolean hasInputted(Long priority) {
        return priority != null;
    }

    public Long getTasKPriority() {
        return this.priority;
    }
}
