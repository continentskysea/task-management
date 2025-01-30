package com.example.domain.value_object.user;

/**
 * パスワード値オブジェクトクラス
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
     * パスワードの正規表現チェック
     * @return true = a~zと0~9までの文字が含まれる小文字のパスワード / それ以外
     */
    private boolean passwordRegex(String password) {
        return password.matches( "^(?=.*[a-z0-9]).*");
    }

    public String getPassword() {
        passwordInputVertificationResult(this.password);
        passwordRegex(this.password);
        return this.password;
    }
}
