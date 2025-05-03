package com.example.domain.value_object.user;

import org.apache.commons.lang3.StringUtils;

/**
 * ユーザー名クラス
 */
public class UserName {
    private String userName;
    private FirstName firstName;
    private LastName lastName;

    public UserName(FirstName firstName, LastName lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = firstName.getFirstNameValue() + lastName.getLastNameValue();

        // 文字数チェック
        if (!userNameLengthCheck(this.userName)) throw new UserNameCharacterLimitException();

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
     * フルネームを取得する
     * @return ユーザー名
     */
    public String getFullName() {
        return this.userName;
    }

    /**
     * 苗字を取得する機能
     * @return firstName
     */
    public FirstName getFirstName() {
        return this.firstName;
    }

    /**
     * 名前を取得する機能
     * @return lastName
     */
    public LastName getLastName() {
        return this.lastName;
    }
}
