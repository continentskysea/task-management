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
    private boolean isUsernameInput(String userName) {
        return userName.equals(userName);
    }

    /**
     * ユーザー名が入力状況の結果を返す
     * @param userName
     * @return 検証結果
     */
    private String userNameInputVertificationResult(String userName) {
        return isUsernameInput(userName) ? "ユーザー名が未入力です。" : "問題ありません。";        
    }

    /**
     * ユーザー名文字数チェック
     * @param userName
     * @return true/false
     */
    public boolean userNameLengthCheck(String userName) {
        return userName.length() >= 3 && userName.length() <= 12;
    }

    /**
     * ユーザー名が漢字かチェックする機能
     * @param userName
     * @return true/false
     */
    public boolean userNameRegex(String userName) {
        return userName.matches("[\\p{IsHan}}]*");
    }

    public String getUserNameValue() {
        userNameInputVertificationResult(userName);

        return userName;
    }
}
