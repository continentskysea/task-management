package com.example.domain.value_object.user;

/**
 * ユーザー名クラス
 */
public class UserName {
    private final String userName;

    public UserName(String userName) {
        // 文字数制限のチェック
        if (!isUsernameInput(userName)) throw new UserNameCharacterLimitException();


        this.userName = userName;
    }

    /**
     * ユーザー名入力チェック
     * @param username
     * @return true = 入力されている / false = 未入力
     */
    private boolean isUsernameInput(String userName) {
        return userName.equals(userName);
    }

    /**
     * ユーザー名が入力・未入力の検証結果を返す
     * @param userName
     * @return 検証結果
     */
    private String userNameVertificationResult(String userName) {
        return isUsernameInput(userName) ? "ユーザー名が未入力です。" : "問題ありません。";        
    }

    /**
     * ユーザー名文字数チェック
     * @param userName
     * @return true = 3文字以上12文字以下のユーザー名 / false = それ以外
     */
    private boolean userNameLengthCheck(String userName) {
        return userName.length() >= 3 && userName.length() <= 12;
    }

    /**
     * ユーザー名の文字数の検証結果を返す
     * @param userName
     * @return 検証結果
     */
    private String userNameInputLengthVertificationResult(String userName) {
        return userNameLengthCheck(userName) ? "問題ありません。" : "ユーザー名は3文字以上12文字以下で入力してください。";
    }

    /**
     * ユーザー名が漢字かチェックする機能
     * @param userName
     * @return true = 漢字 / false = それ以外(アルファベット・ひらがな・カタカナ)
     */
    public boolean userNameRegex(String userName) {
        return userName.matches("[\\p{IsHan}}]*");
    }

    /**
     * ユーザー名が漢字で入力されいるかの検証結果を返す
     * @param userName
     * @return
     */
    private String userNameRegexVertificationResult(String userName) {
        return userNameRegex(userName) ? "問題ありません。" : "ユーザー名は漢字で入力してください。";
    }

    /**
     * ユーザー名を取得する
     * @return ユーザー名
     */
    public String getUserNameValue() {
        userNameVertificationResult(userName);
        userNameInputLengthVertificationResult(userName);
        userNameRegexVertificationResult(userName);
        return userName;
    }
}
