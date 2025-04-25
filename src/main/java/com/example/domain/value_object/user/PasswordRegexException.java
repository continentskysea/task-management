package com.example.domain.value_object.user;

import com.example.domain.shared.ValidationDomainException;

public class PasswordRegexException extends ValidationDomainException {
    public PasswordRegexException() {
        super("パスワードは英小文字と数字のみを含む必要があります");
    }
}
