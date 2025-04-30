package com.example.domain.value_object.user.email;

import com.sun.tools.javac.util.StringUtils;
/**
 * メールアドレス値オブジェクトクラス
 */
public class Email {
    private String email;

    private final static int EMAIL_MAX_LENGTH = 50; // 最大文字数

    public Email(String email) {
        // 未入力チェック
        if (!hasInputted(email)) throw new EmailNotInputException();
        // 文字数チェック
        if (!isLengthWithin(email)) throw new EmailLengthException();
        // 形式チェック
        if (!hasFormatIncludes(email)) throw new EmailDomainFormatException();

        this.email = email;
    }

    /**
     * アドレスの未入力チェック
     * @param email
     * @return true = 入力済 / false = 未入力
     */
    private boolean hasInputted(String email) {
        return StringUtils.isNotEmpty(email);
    }

    /**
     * メールアドレスの文字数をチェックする
     * @param email
     * @return true = 50文字以内 / false = 50文字以上
     */
    private boolean isLengthWithin(String email) {
        return email.length() <= EMAIL_MAX_LENGTH;
    }

    /**
     * アドレスのドメイン名が含まれるかチェックする
     * @param email
     * @return true = @example.comが含まれる / 含まれない
     */
    private boolean hasFormatIncludes(String email) {
        return email.contains("@example.com");
    }

    public String getEmail() {
        return email;
    }
}