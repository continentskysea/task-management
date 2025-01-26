package com.example.domain.value_object;

/**
 * メールアドレス値オブジェクトクラス
 */
public class Email {
    private String email;

    public Email(String email) {
        this.email = email;
    }

    /**
     * アドレスの未入力チェック
     * @param email
     * @return true/false
     */
    private boolean isEmailInput(String email) {
        return email.equals(email);
    }

    /**
     * メールアドレスが入力・未入力かの検証
     * @param email
     * @return 検証結果
     */
    private String emailInputVertificationResult(String email) {
        return isEmailInput(email) ? "問題ありません。" : "メールアドレスが未入力です。";
    }

    /**
     * メールアドレスの文字数をチェックする
     * @param email
     * @return true = 50文字以内 / false = 50文字以上
     */
    private boolean emailLengthCheck(String email) {
        return email.length() <= 50;
    }

    /**
     * メールアドレスの文字数の検証
     * @param email
     * @return 検証結果
     */
    private String emailInputLengthVertificationResult(String email) {
        return emailLengthCheck(email) ? "問題ありません。" : "メールアドレスは50文字以内で入力してください。";
    }

    /**
     * アドレスのドメイン名が含まれるかチェックする
     * @param email
     * @return true = @example.comが含まれる / 含まれない
     */
    private boolean emailContainsCheck(String email) {
        return email.contains("@example.com");
    }

    /**
     * 指定の文字が含まれるかどうかの検証
     * @param email
     * @return 検証結果
     */
    private String emailContainsCheckVertificationResult(String email) {
        return emailContainsCheck(email) ? "問題ありません。" : "不正なアドレスが入力されました。";
    }

    public String getEmail() {
        emailInputVertificationResult(email);
        emailInputLengthVertificationResult(email);
        emailContainsCheckVertificationResult(email);
        return email;
    }
}