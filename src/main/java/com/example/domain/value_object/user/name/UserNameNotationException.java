package com.example.domain.value_object.user.name;

import com.example.domain.shared.ValidationDomainException;

/**
 * ユーザー名の表記例外クラス
 */
public class UserNameNotationException extends ValidationDomainException {
    public UserNameNotationException() {
        super("ユーザー名は漢字で入力してください。");
    }
}
