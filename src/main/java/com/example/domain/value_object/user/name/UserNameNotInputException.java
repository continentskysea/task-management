package com.example.domain.value_object.user.name;

import com.example.domain.shared.ValidationDomainException;

/**
 * 氏名未入力例外クラス
 */
public class UserNameNotInputException extends ValidationDomainException {
    public UserNameNotInputException() {
        super("ユーザー名が未入力です。");
    }
}
