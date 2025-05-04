package com.example.domain.value_object.user.name;

import org.springframework.util.StringUtils;

public class LastName {
    private final String lastName;

    public LastName(String lastName) {
        // 未入力チェック
        if (!hasLastNameInputted(lastName)) throw new LastNameNotInputException();
        // 表記チェック
        if (!isLastNameFormatValid(lastName)) throw new LastNameInputFormatException();

        this.lastName = lastName;
    }

    /**
     * 名前入力チェック機能
     * @param lastName
     * @return true = 入力されている / false = 未入力
     */
    private boolean hasLastNameInputted(String lastName) {
        return  StringUtils.hasLength(lastName);
    }

    /**
     * ユーザー名表記チェック機能
     * @param lastName
     * @return true = 漢字及びカタカナ / false = それ以外(アルファベット・ひらがな・カタカナ)
     */
    private boolean isLastNameFormatValid(String lastName) {
        return lastName.matches("[\\p{IsHan}\\p{IsKatakana}]*");
    }

    public String getLastNameValue() {
        return this.lastName;
    }
}
