package com.example.domain.value_object.user.name;

import org.springframework.util.StringUtils;

// import com.example.domain.shared.ValidationDomainException;

public class FirstName {
    private final String firstName;

    public FirstName(String firstName) {
        // 入力チェック
        if (!hasFirstNameInputted(firstName)) throw new FirstNameNotInputException("苗字が未入力です。");
        // 表記チェック
        if (!isFirstNameFormatValid(firstName)) throw new FirstNameInputFormatException("苗字は漢字またはカタカナで入力したください。");


        this.firstName = firstName;
    }

    /**
     * 苗字入力チェック機能
     * @param firstName
     * @return true = 入力されている / false = 未入力
     */
    private boolean hasFirstNameInputted(String firstName) {
        return  StringUtils.hasLength(firstName);
    }


    /**
     * ユーザー名表記チェック機能
     * @param firstName
     * @return true = 漢字及びカタカナ / false = それ以外(アルファベット・ひらがな・カタカナ)
     */
    private boolean isFirstNameFormatValid(String firstName) {
        return firstName.matches("[\\p{IsHan}\\p{IsKatakana}]*");
    }

    public String getFirstNameValue() {
        return this.firstName;
    }
}
