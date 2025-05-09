package com.example.domain.value_object.user.password;

import org.apache.commons.lang3.StringUtils;

/**
 * パスワード値オブジェクトクラス
 */
public class Password {
    // 正規表現
    private static final String PASSWORD_REGEX = "/^(+[a-z0-9])+)+$/"; // /^(?=.*[a-z])(?=.*\d)[a-z\d]*$/;

    private String password;


    public Password(String password) {

        // 未入力チェック
        if (!isPasswordInput(password)) throw new PasswordNotInputException(); 
        // 正規表現チェック
        if (!passwordRegex(password)) throw new PasswordRegexException();
        // 文字数チェック
        if (!passwordLengthCheck(password)) throw new PasswordCharacterLimitException();

        this.password = password;
    }

    /**
     * パスワードの未入力チェック
     * @param password
     * @return true = 入力あり / false = 未入力
     */
    public boolean isPasswordInput(String password) {
        return StringUtils.isNotEmpty(password);
    }

    /**
     * パスワードの正規表現チェック
     * @return true = a~zと0~9までの文字が含まれる英数小文字のパスワード / それ以外
     */
    private boolean passwordRegex(String password) {
        return  password.matches(PASSWORD_REGEX);
    }

    /**
     * パスワード文字数チェック機能
     * @param userName
     * @return true = 1文字以上12文字以下のパスワード / false = それ以外
     */
    private boolean passwordLengthCheck(String password) {
        return password.length() >= 1 || password.length() > 12;
    }

    public String getPassword() {
        return this.password;
    }
}
