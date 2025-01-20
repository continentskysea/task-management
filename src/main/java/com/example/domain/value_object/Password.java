package com.example.domain.value_object;

/**
 * パスワードクラス
 */
public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }



    /**
     * パスワードの未入力チェック
     * @param password
     * @return true/false
     */
    public boolean isPasswordInput() {
        return password != null;
    }

    /**
     * 
     * @return
     */
    public boolean passwordRegex() {
        return password.matches( "^(?=.*[a-z0-9]).*");
    }
}
