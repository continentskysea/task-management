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
     * @return true = 入力あり / false = 未入力
     */
    public boolean isPasswordInput(String password) {
        return password != null || password != "";
    }


    /**
     * パスワードが入力・未入力かの検証結果を返す
     * @param password
     * @return 
     */
    private String passwordInputVertificationResult(String password) {
        return isPasswordInput(password) ? "問題ありません。" : "パスワードが未入力です。";
    }

    /**
     * 
     * @return
     */
    public boolean passwordRegex() {
        return password.matches( "^(?=.*[a-z0-9]).*");
    }

    public String getPassword() {
        passwordInputVertificationResult(password);
        return password;
    }
}
