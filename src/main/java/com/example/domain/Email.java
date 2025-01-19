package com.example.domain;

/**
 * メールアドレスクラス
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
    public boolean isPasswordInput(String email) {
        return email.equals(email);
    }

    public boolean emailLengthCheck(String email) {
        return email.length() <= 50;
    }
}