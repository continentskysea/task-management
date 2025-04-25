package com.example.domain.value_object.user;

import com.example.domain.shared.ValidationDomainException;

public class PasswordCharacterLimitException extends ValidationDomainException {
    public PasswordCharacterLimitException() {
        super("パスワードは1文字以上12文字以内で入力してください");
    }
}
