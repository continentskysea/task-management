package com.example.domain.value_object;

/**
 * ユーザー名クラス
 */
public class UserName {
    private final String userName;

    public UserName(String userName) {
        this.userName = userName;
    }

    /**
     * ユーザー名入力チェック
     * @param username
     * @return true/false
     */
    public boolean isUsernameInput(String userName) {
        return userName.equals(userName);
    }

    /**
     * ユーザー名文字数チェック
     * @param userName
     * @return true/false
     */
    public boolean userNameLengthCheck(String userName) {
        return userName.length() >= 3 && userName.length() <= 12;
    }
}
