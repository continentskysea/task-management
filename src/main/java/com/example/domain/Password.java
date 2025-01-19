package com.example.domain;

/**
 * パスワードクラス
 */
public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }


    public boolean validation(String password) {

    }

    /**
     * パスワードの未入力チェック
     * @param password
     * @return true/false
     */
    public boolean isPasswordInput(String password) {
        return password.equals(password);
    }

    public boolean numberInputIsVertify(String password) {

    }
}
