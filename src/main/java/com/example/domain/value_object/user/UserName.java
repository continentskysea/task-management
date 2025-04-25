package com.example.domain.value_object.user;

import org.apache.commons.lang3.StringUtils;

/**
 * ユーザー名クラス
 */
public class UserName {
    private final String userName;

    public UserName(String userName) {
        
        // 入力チェック
        if (!isUserNameInput(userName)) throw new UserNameNotInputException();

        // 文字数制限のチェック
        if (!userNameLengthCheck(userName)) throw new UserNameCharacterLimitException();

        // 表記チェック
        if (!userNameRegex(userName)) throw new UserNameNotationException();
        this.userName = userName;
    }

    /**
     * ユーザー名入力チェック機能
     * @param username
     * @return true = 入力されている / false = 未入力
     */
    private boolean isUserNameInput(String userName) {
        return  StringUtils.isNotEmpty(userName);
    }

    /**
     * ユーザー名文字数チェック機能
     * @param userName
     * @return true = 3文字以上12文字以下のユーザー名 / false = それ以外
     */
    private boolean userNameLengthCheck(String userName) {
        return userName.length() >= 3 && userName.length() <= 12;
    }

    /**
     * ユーザー名表記チェック機能
     * @param userName
     * @return true = 漢字 / false = それ以外(アルファベット・ひらがな・カタカナ)
     */
    public boolean userNameRegex(String userName) {
        return userName.matches("[\\p{IsHan}}]*");
    }

    /**
     * ユーザー名を取得する
     * @return ユーザー名
     */
    public String getUserNameValue() {
        return userName;
    }
}
